package chapter_2_The_java_util_function_Package;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <h1>(2.3.) Recipe_2_4_Functions</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - implement {@link java.util.function.Function} interface to transform an input parameter into an output parameter
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - provide a lambda expression that implements {@code R apply(T t)} method
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #mappingStringsToTheirLengths()}
 *                 - mapping strings to their lengths
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * The methods in the {@link java.util.function.Function} interface:
 * <pre>
 * {@code
 * default <V> Function<T,V> andThen(Function<? super R, ? extends V> after)
 * default     R             apply(T t)
 * default <V> Function<T,V> compose(Function<? super R, ? extends V> before)
 * default <T> Function<T,T> identity()
 * }
 * </pre>
 *
 * The most common usage of {@link java.util.function.Function} is as an argument to the
 * {@link java.util.stream.Stream#map(Function)} method.
 *
 * <br><br>
 * The complete list of primitive variations for both the input and the output generic types:
 * <pre>
 * {@code
 * IntFunction           R      apply         (int value)
 * DoubleFunction        R      apply         (double value)
 * LongFunction          R      apply         (long value)
 * ToIntFunction         int    applyAsInt    (T value)       => used by IntStream    Stream.mapToInt(ToIntFunction)
 * ToDoubleFunction      double applyAsDouble (T value)       => used by DoubleStream Stream.mapToDouble(ToDoubleFunction)
 * ToLongFunction        long   applyAsLong   (T value)       => used by LongStream   Stream.mapToLong(ToLongFunction)
 * DoubleToIntFunction   int    applyAsInt    (double value)
 * DoubleToLongFunction  long   applyLong     (double value)
 * IntToDoubleFunction   double applyAsDouble (int value)
 * IntToLongFunction     long   applyAsLong   (int value)
 * LongToDoubleFunction  double applyAsDouble (Long value)
 * LongToIntFunction     int    applyAsInt    (Long value)
 * BiFunction            void   accept        (T t, U u)
 * ToIntBiFunction       int    applyAsInt    (T t, U u)
 * ToDoubleBiFunction    double applyAsDouble (T t, U u)
 * ToLongBiFunction      long   applyAsLong   (T t, U u)
 * }
 * </pre>
 *
 * If the argument and the return type, the java.util.function package defines {@link java.util.function.UnaryOperator}.
 * There are also interfaces for primitives values - {@link java.util.function.IntUnaryOperator},
 * {@link java.util.function.DoubleUnaryOperator} and {@link java.util.function.LongUnaryOperator} interfaces.
 * <br>
 * An example of {@link java.util.function.UnaryOperator} is {@link StringBuilder#reverse()} method because both
 * the input type and the output type are strings.
 *
 * <br><br>
 * The {@link java.util.function.BiFunction} is defined for two generic input types and one generic output type, all of
 * which are assumed to be different.
 * <br>
 * If all three are the same, the package includes the {@link java.util.function.BinaryOperator} interface.
 * <br>
 * An example of {@link java.util.function.BinaryOperator} is {@link Math#max(int, int)}, because both inputs and outputs
 * are either {@code int}, {@code double}, {@code float} or {@code long}.
 * <br>
 * The interface also defines the interfaces {@link java.util.function.IntBinaryOperator},
 * {@link java.util.function.DoubleBinaryOperator} and {@link java.util.function.LongBinaryOperator} for these situations.
 *
 * <br><br>
 * Other uses of {@link Function}:
 * <ul>
 *     <li>
 *         {@link java.util.Map#computeIfAbsent(Object, Function)}
 *         - if the specified key doesn't have a value, use the provided {@link Function} to compute one and add it to
 *         the {@link java.util.Map}
 *     </li>
 *     <li>
 *         {@link java.util.Comparator#comparing(Function)}
 *         - the method generates a {@link java.util.Comparator} that sorts a collection by the key generated from
 *         the given {@link Function}
 *     </li>
 *     <li>
 *         {@link java.util.Comparator#thenComparing(Comparator)}
 *         - an instance method, also used in sorting, that adds an additional sorting mechanism if the collection has
 *         equal values by the first sort
 * </ul>
 *
 * <br>
 * Functions are also used extensively in the {@link Collectors} utility class for grouping and downstream collectors.
 *
 * @see chapter_3_Streams.Recipe_3_3_Reduction_Operations_Using_Reduce Recipe 3.3. for more on BinaryOperator uses in the standard library
 * @see chapter_4_Comparators_and_Collectors.Recipe_4_3_Adding_a_Linear_Collection_to_a_Map Recipe 4.3. for the identity method
 * @see chapter_4_Comparators_and_Collectors.Recipe_4_6_Downstream_Collectors Recipe 4.6. for using functions as downstream collectors
 * @see chapter_5_Issues_with_Stream_Lambdas_and_Method_References.Recipe_5_8_Closure_Composition Recipe 5.8. for andThen and compose methods uses
 */
public class Recipe_2_4_Functions {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 2 - 2.4. Recipe_2_3_Functions");


        System.out.println();
        // *** Mapping Strings to their Lengths ***
        mappingStringsToTheirLengths();

    }

    /**
     * &#9674;&#9674;&#9674; <b>Mapping Strings to their Lengths</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");

     // Using anonymous inner class
     String collectedStrings = strings.stream().map(new Function&#60;String, String&#62;() {
         &#64;Override
         public String apply(String s) {
            return String.format("%10s has length %2s", s, s.length());
         }
     }).collect(Collectors.joining("\n"));

     // Result:
     //    string has length  6
     //      word has length  4
     // something has length  9
     //    random has length  6
     //    stuffy has length  6
     //     stuff has length  5
     * </pre>
     */
    protected static void mappingStringsToTheirLengths() {
        System.out.println("\n*** Mapping Strings to their Lengths ***");

        List<String> strings = Arrays.asList("string", "word", "something", "random", "stuffy", "stuff");

        // Using anonymous inner class
        String collectedStrings = strings.stream().map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return String.format("%10s has length %2s", s, s.length());
            }
        }).collect(Collectors.joining("\n"));

        System.out.println(collectedStrings);
    }
}
