package student;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Creates an iterator over a group of favorite items. The first item is
 * returned from the iterator 1 time, the second item is returned 2 times, etc.
 * with the nth item returned n times. The items may be returned in any order.
 *
 * @param <T> the type of item
 */
public class FavoritesIterator<T> implements Iterator<T> {
    private final List<T> itemsToReturn = new LinkedList<>();

    /**
     * Constructs an iterator over favorite items.
     *
     * @param items the items to iterate over
     */
    public FavoritesIterator(List<T> items) {
        LinkedList<T> remainingItems = new LinkedList<>(items);
        while (!remainingItems.isEmpty()) {
            itemsToReturn.addAll(remainingItems);
            remainingItems.remove(0);
        }
    }

    @Override
    public boolean hasNext() {
        return !itemsToReturn.isEmpty();
    }

    @Override
    public T next() {
        if (hasNext()) {
            return itemsToReturn.remove(0);
        }
        throw new NoSuchElementException();
    }
}
