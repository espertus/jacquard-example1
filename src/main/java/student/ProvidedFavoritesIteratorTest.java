package student;

import com.spertus.jacquard.common.Visibility;
import com.spertus.jacquard.junittester.GradedTest;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProvidedFavoritesIteratorTest {
    List<String> favoriteHotSauces0 = new ArrayList<>(); // I don't like hot sauce.
    List<Integer> favoriteYears1 = List.of(2010); // my daughter's birth
    List<String> favoriteCaliforniaCities2 =
            List.of("Oakland", "San Francisco");

    @Test
    @GradedTest(name = "works for empty list", visibility = Visibility.AFTER_PUBLISHED, points = 5.0)
    public void iteratorOverEmptyList() {
        FavoritesIterator<String> iterator = new FavoritesIterator<>(favoriteHotSauces0);

        // No items should be returned.
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    @GradedTest(name = "works for one-element list", visibility = Visibility.AFTER_PUBLISHED, points = 5.0)
    public void iteratorOverOneElementList() {
        FavoritesIterator<Integer> iterator = new FavoritesIterator<>(favoriteYears1);

        // The one item in the list should be returned once.
        assertTrue(iterator.hasNext());
        assertEquals(2010, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    @GradedTest(name = "works for two-element list", visibility = Visibility.AFTER_PUBLISHED, points = 10.0)
    public void iteratorOverTwoElementList() {
        FavoritesIterator<String> iterator = new FavoritesIterator<>(favoriteCaliforniaCities2);
        List<String> results = new LinkedList<>();
        while (iterator.hasNext()) {
            results.add(iterator.next());
        }

        // Three items should have been returned:
        // Oakland, San Francisco, San Francisco (in any order).
        assertEquals(3, results.size());

        // This tries to remove "Oakland" and returns true if successful.
        assertTrue(results.remove("Oakland"));
        assertTrue(results.remove("San Francisco"));
        assertTrue(results.remove("San Francisco"));
        assertEquals(0, results.size()); // redundant but harmless
    }
}
