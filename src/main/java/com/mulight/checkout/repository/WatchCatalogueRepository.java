package com.mulight.checkout.repository;

import com.mulight.checkout.model.WatchCatalogue;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Repository class for <code>WatchCatalogue</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Aaron
 */
public interface WatchCatalogueRepository {

    /**
     * Retrieve all <code>Watch WatchCatalogue</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>Watch WatchCatalogue</code>s
     */
    List<WatchCatalogue> findByWatchIds(List<String> watchIds) throws DataAccessException;

    ;
}
