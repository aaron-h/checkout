package com.mulight.checkout.service;

import com.mulight.checkout.model.WatchCatalogue;
import com.mulight.checkout.repository.WatchCatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Mostly used as a facade for all WatchCatalogue controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Aaron
 */
@Service
public class WatchCatalogueServiceImpl implements WatchCatalogueService {

    @Autowired
    private WatchCatalogueRepository watchCatalogueRepository;

    @Override
    public List<WatchCatalogue> findWatchCatalogueByWatchIds(List<String> watchIds) throws DataAccessException {
        return watchCatalogueRepository.findByWatchIds(watchIds);
    }
}
