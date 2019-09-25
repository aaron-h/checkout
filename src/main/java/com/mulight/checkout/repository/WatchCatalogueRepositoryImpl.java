package com.mulight.checkout.repository;

import com.google.common.collect.Lists;
import com.mulight.checkout.model.WatchCatalogue;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * * JPA implementation of the {@link WatchCatalogueRepository} interface.
 *
 * @author Aaron
 */

@Repository
public class WatchCatalogueRepositoryImpl implements WatchCatalogueRepository {


    @Override
    public List<WatchCatalogue> findByWatchIds(List<String> watchIds) throws DataAccessException {

        List<WatchCatalogue> watchCatalogues = getWatchCatalogues().stream().filter(item -> watchIds.contains(item.getWatchId())).collect(Collectors.toList());
        return watchCatalogues;
    }


    //Mock database data
    private List<WatchCatalogue> getWatchCatalogues() {

        WatchCatalogue rolex = new WatchCatalogue("001", "Rolex", 100d, "3 for 200");
        WatchCatalogue michaelKors = new WatchCatalogue("002", "Michael Kors", 80d, "2 for 120");
        WatchCatalogue swatch = new WatchCatalogue("003", "Swatch", 50d, "");
        WatchCatalogue casio = new WatchCatalogue("004", "Casio", 30d, "");

        return Lists.newArrayList(rolex, michaelKors, swatch, casio);
    }


}
