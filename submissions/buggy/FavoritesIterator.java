
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

    private int index = 0;

    private List<T> list;

    /**
     * Constructs an iterator over the provided items. The iterator is not
     * affected by changes to the list after the constructor call so never
     * throws {@link ConcurrentModificationException}.
     *
     * @param items the items to iterate over
     */
    public FavoritesIterator(List<T> items) {
        this.list = new ArrayList<>(items);
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }


    //if index 1 (second item), add 1 copy.
    @Override
    public T next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }


        if (index == 1) {
            list.add(list.get(index));
        }
        if (index == 2) {
            list.add(list.get(index));
            list.add(list.get(index));
        }
        if (index == 3) {
            list.add(list.get(index));
            list.add(list.get(index));
            list.add(list.get(index));
        }


        return list.get(index++);
    }
}
