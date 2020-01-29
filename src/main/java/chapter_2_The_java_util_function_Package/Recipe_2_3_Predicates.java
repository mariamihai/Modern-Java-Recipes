package chapter_2_The_java_util_function_Package;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <h1>(2.3.) Recipe_2_3_Predicates</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - filter data using {@link java.util.function.Predicate}
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - implement {@link java.util.function.Predicate#test(Object)} using lambda expression or a method reference
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #findStringsOfAGivenLength()}
 *                 - find Strings of a given length
 *             </li>
 *             <li>
 *                 {@link #filterStringsOfLength5()}
 *                 - filter Strings of length 5
 *             </li>
 *             <li>
 *                 {@link #useCompositePredicate()}
 *                 - use composite Predicate
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * Predicates are used primary to filter streams.
 *
 * <br><br>
 * Methods in {@link java.util.function.Predicate}:
 * <pre>
 * {@code
 * default    Predicate<T> and(Predicate<? super T> other)
 * static <T> Predicate<T> isEquals(Object targetRef)
 * default    Predicate<T> negate()
 * default    Predicate<T> or(Predicate<? super T> other)
 * boolean                 test(T t) // SAM
 * }
 * </pre>
 *
 * @see chapter_5_Issues_with_Stream_Lambdas_and_Method_References.Recipe_5_8_Closure_Composition Recipe 5.8. for closure composition
 * @see chapter_3_Streams.Recipe_3_10_Using_anyMatch_allMatch_and_noneMatch Recipe 3.10 for anyMatch, allMatch and noneMatch methods
 * @see chapter_4_Comparators_and_Collectors.Recipe_4_5_Partitioning_and_Collectors Recipe 4.5. for partitioning and group by operations
 */
public class Recipe_2_3_Predicates {

    /** Add Predicate for particular case - filter the strings with length 5. */
    public static final Predicate<String> LENGTH_FIVE = s -> s.length() == 5;
    /** Add Predicate for particular case - filter the strings which start with letter 'S'. */
    public static final Predicate<String> STARTS_WITH_S = s -> s.toLowerCase().startsWith("S");


    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 2 - 2.3. Recipe_2_3_Predicates");


        System.out.println();
        // *** Find Strings of a Given Length ***
        findStringsOfAGivenLength();

        System.out.println();
        // *** Filter Strings of Length 5 ***
        filterStringsOfLength5();

        System.out.println();
        // *** Use Composite Predicate ***
        useCompositePredicate();

        System.out.println();
        // *** All Matches in a Stream ***
        allMatchesInAStream();

        System.out.println();
        // *** partitioningBy() in a Stream ***
        partitioningByInAStream();
    }


    /**
     * &#9674;&#9674;&#9674; <b>Find Strings of a Given Length</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");
     int chosenLength = 6;

     String filteredStrings = strings.stream().filter(string -&#62; string.length() == chosenLength)
                                              .collect(Collectors.joining(", "));
     * </pre>
     */
    protected static void findStringsOfAGivenLength() {
        System.out.println("\n*** Find Strings of a Given Length ***");
        List<String> strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");
        int chosenLength = 6;

        String filteredStrings = strings.stream().filter(string -> string.length() == chosenLength).collect(Collectors.joining(", "));
        System.out.println(filteredStrings);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Filter Strings of Length 5</b> &#9674;&#9674;&#9674;
     *
     * <br>
     * Use a {@link Predicate} previously defined as a constant in the class ({@link #LENGTH_FIVE} {@link Predicate}).
     *
     * <pre>
     List&#60;String&#62; strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");
     String collectedStrings = strings.stream().filter(LENGTH_FIVE).collect(Collectors.joining(", "));
     * </pre>
     *
     */
    protected static void filterStringsOfLength5() {
        System.out.println("\n*** Filter Strings of Length 5 ***");

        List<String> strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");
        String collectedStrings = strings.stream().filter(LENGTH_FIVE).collect(Collectors.joining(", "));

        System.out.println(collectedStrings);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Use Composite Predicate</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");

     long countNotLengthFive = strings.stream().filter(LENGTH_FIVE.negate()).count();
     Assert.assertEquals(5, countNotLengthFive);

     long countLengthFiveOrStartsWithS = strings.stream().filter(LENGTH_FIVE.or(STARTS_WITH_S)).count();
     Assert.assertEquals(1, countLengthFiveOrStartsWithS);

     long countLengthFiveAndDoNotStartWithS = strings.stream().filter(LENGTH_FIVE.and(STARTS_WITH_S.negate())).count();
     Assert.assertEquals(1, countLengthFiveAndDoNotStartWithS);
     * </pre>
     *
     * @see chapter_5_Issues_with_Stream_Lambdas_and_Method_References.Recipe_5_8_Closure_Composition Recipe 5.8. for closure composition
     */
    protected static void useCompositePredicate() {
        System.out.println("\n*** Use Composite Predicate ***");

        List<String> strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");

        long count = strings.stream().filter(LENGTH_FIVE.negate()).count();
        Assert.assertEquals(5, count);

        count = strings.stream().filter(LENGTH_FIVE.or(STARTS_WITH_S)).count();
        Assert.assertEquals(1, count);

        count = strings.stream().filter(LENGTH_FIVE.and(STARTS_WITH_S.negate())).count();
        Assert.assertEquals(1, count);
    }

    /**
     * &#9674;&#9674;&#9674; <b>All Matches in a Stream</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");

     boolean hasOnlyStartingWithSWords = strings.stream().allMatch(STARTS_WITH_S);
     String evaluation = hasOnlyStartingWithSWords == true ? "The list contains only strings starting with s / S" :
                                                             "The list doesn't contain only strings starting with s / S";
     System.out.println(evaluation);
     * </pre>
     *
     * @see chapter_3_Streams.Recipe_3_10_Using_anyMatch_allMatch_and_noneMatch Recipe 3.10 for anyMatch, allMatch and noneMatch methods
     */
    protected static void allMatchesInAStream() {
        System.out.println("\n*** All Matches in a Stream ***");

        List<String> strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");

        boolean hasOnlyStartingWithSWords = strings.stream().allMatch(STARTS_WITH_S);
        String evaluation =
                hasOnlyStartingWithSWords == true ? "The list contains only strings starting with s / S" :
                                                "The list doesn't contain only strings starting with s / S";
        System.out.println(evaluation);
    }

    /**
     * &#9674;&#9674;&#9674; <b>partitioningBy() in a Stream</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");

     Map&#60;Boolean, List&#60;String&#62;&#62; partitionedStrings = strings.stream().collect(Collectors.partitioningBy(LENGTH_FIVE));

     String evaluation;
     for(Map.Entry&#60;Boolean, List&#60;String&#62;&#62; partition : partitionedStrings.entrySet()) {
         if(partition.getKey() == true) evaluation = "There is/are " + partition.getValue().size() + " string(s) of length 5";
         else evaluation = "There is/are " + partition.getValue().size() + " string(s) of different length than 5";

         System.out.println(evaluation);
     }
     * </pre>
     *
     * @see chapter_4_Comparators_and_Collectors.Recipe_4_5_Partitioning_and_Collectors Recipe 4.5. for partitioning and group by operations
     */
    protected static void partitioningByInAStream() {
        System.out.println("\n*** partitioningBy() in a Stream ***");

        List<String> strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");

        Map<Boolean, List<String>> partitionedStrings = strings.stream().collect(Collectors.partitioningBy(LENGTH_FIVE));

        String evaluation;
        for(Map.Entry<Boolean, List<String>> partition : partitionedStrings.entrySet()) {
            if(partition.getKey() == true) evaluation = "There is/are " + partition.getValue().size() + " string(s) of length 5";
            else evaluation = "There is/are " + partition.getValue().size() + " string(s) of different length than 5";

            System.out.println(evaluation);
        }

    }
}
