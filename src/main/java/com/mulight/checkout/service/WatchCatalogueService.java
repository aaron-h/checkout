package com.mulight.checkout.service;

import com.mulight.checkout.model.WatchCatalogue;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Aaron
 */
public interface WatchCatalogueService {

    List<WatchCatalogue> findWatchCatalogueByWatchIds(List<String> watchIds) throws DataAccessException;
}
