package chapter_3_Streams;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>(3.4.) Check Sorting using Reduce</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - check that a sort is correct
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use the {@code reduce} method to check each pair of elements
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #summingBigDecimalsWithReduce()}
 *                 - summing BigDecimals with reduce
 *             </li>
 *             <li>
 *                 {@link #sortingStringsByLength()}
 *                 - sorting Strings by length
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <pre>
 * {@code
 * Optional<T> reduce (BinaryOperator<T> accumulator
 * }
 * </pre>
 *
 * @see chapter_4_Comparators_and_Collectors.Recipe_4_1_Sorting_Using_a_Comparator
 */
public class Recipe_3_4_Check_Sorting_using_Reduce {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 3 - 3.4. Check Sorting using Reduce ");


        System.out.println();
        // *** Summing BigDecimals with Reduce **
        summingBigDecimalsWithReduce();

        System.out.println();
        // *** Sorting Strings by Length ***
        sortingStringsByLength();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Summing BigDecimals with Reduce</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     * {@code
     * BigDecimal total = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE)).limit(10)
     *                 .reduce(BigDecimal.ZERO, (acc, val) -> acc.add(val));
     * }
     * </pre>
     *
     */
    protected static void summingBigDecimalsWithReduce() {
        BigDecimal total = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE)).limit(10)
                .reduce(BigDecimal.ZERO, (acc, val) -> acc.add(val));

        System.out.println(total);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Sorting Strings by Length</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     * {@code
     * List<String> orderedStrings = Arrays.asList("aaa", "bb", "cccc", "d").stream()
     *                 .sorted(Comparator.comparing(String::length))
     *                 .collect(Collectors.toList());
     * }
     * </pre>
     *
     */
    protected static void sortingStringsByLength() {
        List<String> orderedStrings = Arrays.asList("aaa", "bb", "cccc", "d").stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());

        System.out.println(orderedStrings);
    }
}
