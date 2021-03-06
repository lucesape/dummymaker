package io.dummymaker.factory;

import java.util.List;

/**
 * Populates objects via generators included
 *
 * @author GoodforGod
 * @since 30.05.2017
 */
public interface IPopulateFactory<T> {

    /**
     * Populates object via generators
     * @param t object to populate
     * @return Populated Object
     */
    T populate(final T t);

    /**
     * Populates objects via generators
     * @param t list of object to populate
     * @return Populated list of objects
     */
    List<T> populate(final List<T> t);
}
