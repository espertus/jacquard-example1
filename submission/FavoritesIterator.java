// placeholder for student submission
package student;

import java.util.*;

/**
 * Creates an iterator over a group of favorite items. The first item is
 * returned from the iterator 1 time, the second item is returned 2 times, etc.
 * with the nth item returned n times. The items may be returned in any order.
 *
 * @param <T> the type of item
 */
public class FavoritesIterator<T> implements Iterator<T> {
    /**
     * Constructs an iterator over favorite items.
     *
     * @param items the items to iterate over
     */
    public FavoritesIterator(List<T> items) {
    }

    // Deliberate PMD violation (MissingOverride) on next line
    // @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }

    // Deliberate checkstyle violation on next line
    private void helper_method() { }
}
