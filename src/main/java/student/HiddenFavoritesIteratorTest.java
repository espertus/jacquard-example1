package student;

import com.spertus.jacquard.common.Visibility;
import com.spertus.jacquard.junittester.GradedTest;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HiddenFavoritesIteratorTest {
    private static final List<String> favoriteSiblings = List.of("Michael", "Debby", "Andrea");
    private static final List<Integer> favoriteBases = List.of(2, 8, 10, 16);
    private static final List<Integer> favoriteDecimalDigits = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private <T> void testList(List<T> list) {
        FavoritesIterator<T> iterator = new FavoritesIterator<>(list);

        // Build up a hash table mapping from strings to the number of times
        // they were returned by the iterator.
        HashMap<T, Integer> counts = new HashMap<>();
        while (iterator.hasNext()) {
            T favorite = iterator.next();
            if (counts.containsKey(favorite)) {
                counts.put(favorite, counts.get(favorite) + 1);
            } else {
                counts.put(favorite, 1);
            }
        }

        // Check that each element appears the correct number of times.
        for (int i = 0; i < list.size(); i++) {
            T key = list.get(  i);
            assertTrue(counts.containsKey(key));
            assertEquals(i + 1, counts.get(key));
        }
    }

    @Test
    @GradedTest(name = "works for three-element list", visibility = Visibility.AFTER_PUBLISHED, points = 10.0)
    public void iteratorOverThreeElementListRobust() {
        testList(favoriteSiblings);
    }

    @Test
    @GradedTest(name = "works for four-element list", visibility = Visibility.AFTER_PUBLISHED, points = 15.0)
    public void iteratorOverFourElementListRobust() {
        testList(favoriteBases);
    }

    @Test
    @GradedTest(name = "works for ten-element list", visibility = Visibility.AFTER_PUBLISHED, points = 15.0)
    public void iteratorOverTenElementListRobust() {
        testList(favoriteDecimalDigits);
    }
}
