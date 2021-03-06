package io.dummymaker.bundle;

/**
 * Provides default bundles of values for generators
 *
 * @author GoodforGod
 * @since 31.05.2017
 */
public interface IBundle<T> {

    /**
     * Get bundle values by index
     * @param index index in bundle collection
     * @return bundle value
     */
    T get(int index);

    /**
     * Get random bundle value
     * @return bundle value
     */
    T getRandom();

    /**
     * Get bundle capacity
     * @return bundle collections capacity
     */
    int size();
}
