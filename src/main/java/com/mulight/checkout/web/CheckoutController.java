package com.mulight.checkout.web;

import com.google.common.collect.Lists;
import com.mulight.checkout.model.WatchCatalogue;
import com.mulight.checkout.service.WatchCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/** An Api for online shopping checkout process
 *  Path: /checkout
 *  Request body: an array of String ids
 *  Response: the total price of the item list
 *  Sample request i.e. post:/checkout  body: ["001","002","003"] response:{"price":230.0}
 *
 * @author Aaron
 *
 */
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    public final static String TWO_FOR_120_DISCOUNT = "2 for 120";
    public final static String THREE_FOR_200_DISCOUNT = "3 for 200";
    public final static String WITHOUT_DISCOUNT = "";

    @Autowired
    private WatchCatalogueService watchCatalogueService;

    /**
     *
     * @param watchIds a String  array of watch ids
     * @return items total price
     *
     */
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map checkout(@RequestBody String[] watchIds) {
        if (null == watchIds || watchIds.length < 1)
            return Collections.singletonMap("price", 0);

        Map<String, Long> watchIdCountMap = Arrays.asList(watchIds)
                .stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<WatchCatalogue> watches = watchCatalogueService.findWatchCatalogueByWatchIds(Lists.newArrayList(watchIdCountMap.keySet()));


        return Collections.singletonMap("price", calculateTotalPrice(watchIdCountMap, watches));
    }


    private Double calculateTotalPrice(Map<String, Long> watchIdCountMap, List<WatchCatalogue> watches) {
        final Double[] totalPrice = {0d};
        watchIdCountMap.forEach((id, count) -> {
            Optional<WatchCatalogue> watchCatalogue = watches.stream().filter(item -> item.getWatchId().equals(id)).findFirst();
            if (watchCatalogue.isPresent()) {
                if (THREE_FOR_200_DISCOUNT.equals(watchCatalogue.get().getDiscount())) {
                    ThreeFor200Discounter threeFor200Discounter = new ThreeFor200Discounter();
                    totalPrice[0] += threeFor200Discounter.applyDiscount(count, watchCatalogue.get().getUnitPrice());
                    return;
                }

                if (TWO_FOR_120_DISCOUNT.equals(watchCatalogue.get().getDiscount())) {
                    TwoFor120Discounter twoFor120Discounter = new TwoFor120Discounter();
                    totalPrice[0] += twoFor120Discounter.applyDiscount(count, watchCatalogue.get().getUnitPrice());
                    return;
                }

                if (WITHOUT_DISCOUNT.equals(watchCatalogue.get().getDiscount())) {
                    totalPrice[0] += count * watchCatalogue.get().getUnitPrice();
                    return;
                }
            }
        });

        return totalPrice[0];
    }


    /**
     * Discount strategy interface
     */
    public interface Discounter {
        Double applyDiscount(long count, double price);
    }


    public class TwoFor120Discounter implements Discounter {

        @Override
        public Double applyDiscount(long count, double price) {
            if (count == 0 || count < 2)
                return count * price;
            return 120d * (count / 2) + price * (count % 2);
        }
    }

    public class ThreeFor200Discounter implements Discounter {

        @Override
        public Double applyDiscount(long count, double price) {
            if (count == 0 || count < 3)
                return price * count;
            return 200d * (count / 3) + price * (count % 3);
        }
    }

}
