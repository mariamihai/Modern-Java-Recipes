package chapter_3_Streams;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>(3.7.) Counting Elements </h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - you want to know how many elements are in a stream
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use the {@link Stream#count()} or {@link Collectors#counting()} methods
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #countingElementsInAStream()}
 *                 - counting elements in a stream
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * @see chapter_4_Comparators_and_Collectors.Recipe_4_6_Downstream_Collectors
 */
public class Recipe_3_7_Counting_Elements {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 3 - 3.7. Counting Elements");


        System.out.println();
        // *** Counting Elements in a Stream ***
        countingElementsInAStream();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Counting Elements in a Stream</b> &#9674;&#9674;&#9674;
     *
     * <br>
     * <b>Using the {@code count} method on {@link Stream}:</b>
     * <br><br>
     * For this method, all the elements in the stream are mapped to 1 as a long, then the {@code mapToLong} method
     * produces a {@link java.util.stream.LongStream}, which has a {@code sum} method. (Map all the elements to one and
     * add them up.)
     *
     * <pre>{@code
     * long count = Stream.of(1, 3, 5, 7, 9).count();
     * // Implementation of count():
     * mapToLong(e -> 1L).sum();
     * }</pre>
     *
     *
     * <b>Using the {@code counting} method on {@link Collectors}:</b>
     * <br>
     * The {@link Collectors} methods are intended for downstream post-processing of a {@code partitioningBy} or
     * {@code groupingBy} operation.
     *
     * <pre>{@code
     * Long counted = Stream.of(1, 3, 5, 7, 9).collect(Collectors.counting());
     * Map<Boolean, Long> collectedValues = Stream.of("aa", "bbbb", "c", "dddd", "ee")
     *          .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0, // Predicate
     *                                             Collectors.counting())); // Downstream collector
     * }</pre>
     *
     */
    protected static void countingElementsInAStream() {
        long count = Stream.of(1, 3, 5, 7, 9).count();

        Long counted = Stream.of(1, 3, 5, 7, 9).collect(Collectors.counting());

        Map<Boolean, Long> collectedValues = Stream.of("aa", "bbbb", "c", "dddd", "ee")
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0, // Predicate
                                                   Collectors.counting())); // Downstream collector
    }
}
