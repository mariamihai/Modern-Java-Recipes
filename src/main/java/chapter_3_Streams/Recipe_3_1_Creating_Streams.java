package chapter_3_Streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.*;

/**
 * <h1>(3.1.) Creating Streams</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - create a stream from a source of data
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use the static factory methods in the Stream interface, or the {@code stream} methods on {@link Iterable} or
 *         {@link java.util.Arrays}
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #creatingAStreamUsingTheStreamOfMethod()}
 *                 - creating a Stream using the Stream.of() method
 *             </li>
 *             <li>
 *                 {@link #creatingAStreamUsingTheStreamIterateMethod()}
 *                 -  creating a Stream using the Stream.iterate() method
 *         d();
 *             </li>
 *             <li>
 *                 {@link #creatingAStreamUsingTheStreamGenerateMethod()}
 *                 - creating a Stream using the Stream.generate() method
 *             </li>
 *             <li>
 *                 {@link #creatingStreamsWithRangeAndRangeClosedMethods()}
 *                 - creating a Stream using the  the range() and rangeClosed() methods
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * A stream method has been added to the {@link java.util.Collection} interface and you can use {@code list.stream()}
 * (etc.) to create a stream of the elements in that collection.
 *
 * <br><br>
 * There are child interfaces of {@link Stream} to deal with primitives - {@link java.util.stream.IntStream},
 * {@link java.util.stream.LongStream} and {@link java.util.stream.DoubleStream}. The first two have additional methods
 * for creating the stream:
 * <pre>
 * {@code
 * static IntStream  range       (int  startInclusive, int  endExclusive)
 * static intStream  rangeClosed (int  startInclusive, int  endInclusive)
 * static LongStream range       (long startInclusive, long endExclusive)
 * static LongStream rangeClosed (long startInclusive, long endInclusive)
 * }
 * </pre>
 *
 * Each of the previous methods returns a sequential, ordered streamthat starts at the first argument and increments by
 * one after that.
 *
 * @see Recipe_3_2_Boxed_Streams Recipe 3.2. for converting streams of primitives to wrapper instances
 */
public class Recipe_3_1_Creating_Streams {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 3 - 3.1. Creating Streams");


        System.out.println();
        // *** Creating a Stream Using the Stream.of() Method ***
        creatingAStreamUsingTheStreamOfMethod();

        System.out.println();
        // *** Creating a Stream Using the Stream.iterate() Method ***
        creatingAStreamUsingTheStreamIterateMethod();

        System.out.println();
        // *** Creating a Stream Using the Stream.generate() Method ***
        creatingAStreamUsingTheStreamGenerateMethod();

        System.out.println();
        // *** Creating a Stream Using the range() and rangeClosed() Methods ***
        creatingStreamsWithRangeAndRangeClosedMethods();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Creating a Stream Using the Stream.of() Method</b> &#9674;&#9674;&#9674;
     *
     * <br><br>
     * {@code static <T> Stream<T> of(T... values)}
     *
     * <br><br>
     * The implementation of this method actually delegates to the {@link java.util.Arrays#stream(Object[])} method.
     *
     * <br>
     * <pre>
     &#64;SafeVarargs
     public static&#60;T&#62; Stream&#60;T&#62; of(T... values) {
        returns Arrays.stream(values);
     }
     * </pre>
     *
     * Eq.:
     *
     * <pre>
     String collectedWords = Stream.of("string", "word", "character", "chocolate").collect(Collectors.joining(", "));
     System.out.println(collectedWords);

     String singleString = Stream.of("all by myself as a single String").collect(Collectors.joining(", "));
     System.out.println(singleString);
     * </pre>
     */
    protected static void creatingAStreamUsingTheStreamOfMethod() {
        System.out.println("\n*** Creating a Stream Using the Stream.of() Method ***");

        String collectedWords = Stream.of("string", "word", "character", "chocolate")
                                      .collect(Collectors.joining(", "));
        System.out.println(collectedWords);

        String singleString = Stream.of("all by myself as a single String").collect(Collectors.joining(", "));
        System.out.println(singleString);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Creating a Stream Using the Stream.iterate() Method</b> &#9674;&#9674;&#9674;
     *
     * <br><br>
     * {@code static <T> Stream<T> iterate(T seed, UnaryOperator<T>)}
     *
     * <br><br>
     * The method returns an <i>infinite</i> sequencial ordered {@link Stream} produced by iterative application of a
     * function {@code f} to an initial element seed.
     *
     * <pre>
     List&#60;BigDecimal&#62; listOfBigDecimals =
            Stream.iterate(BigDecimal.ONE, n -&#62; n.add(BigDecimal.ONE)).limit(10).collect(Collectors.toList());
     System.out.println(listOfBigDecimals);

     Stream.iterate(LocalDate.now(), ld -&#62; ld.plusDays(1)).limit(5).forEach(System.out::println);
     * </pre>
     *
     */
    protected static void creatingAStreamUsingTheStreamIterateMethod() {
        System.out.println("\n*** Creating a Stream Using the Stream.iterate() Method ***");

        List<BigDecimal> listOfBigDecimals = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                                                   .limit(10)
                                                   .collect(Collectors.toList());
        System.out.println(listOfBigDecimals);

        Stream.iterate(LocalDate.now(), ld -> ld.plusDays(1)).limit(5).forEach(System.out::println);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Creating a Stream Using the Stream.generate() Method</b> &#9674;&#9674;&#9674;
     *
     * <br><br>
     * {@code static <T> Stream<T> generate(Supplier<T> s)}
     *
     * <br><br>
     * The method produces a sequential, unordered stream by repeatedly invoking the {@link java.util.function.Supplier}.
     *
     * <pre>
     List&#60;Double&#62; collectedRandoms = Stream.generate(Math::random).limit(5).collect(Collectors.toList());
     System.out.println(collectedRandoms);
     * </pre>
     *
     */
    protected static void creatingAStreamUsingTheStreamGenerateMethod() {
        System.out.println("\n*** Creating a Stream Using the Stream.iterate() Method ***");

        List<Double> collectedRandoms = Stream.generate(Math::random).limit(5).collect(Collectors.toList());
        System.out.println(collectedRandoms);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Creating a Stream Using the range() and rangeClosed() Methods</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;Integer&#62; collectedIntegers = IntStream.range(10, 15)
                     .boxed() // necessary for Collectors to convert primitives to List&#60;Integer&#62;
                     .collect(Collectors.toList());
     System.out.println(collectedIntegers);

     List&#60;Long&#62; collectedLongs = LongStream.rangeClosed(10, 15)
                      .boxed() // necessary for Collectors to convert primitives to List&#60;Long&#62;
                      .collect(Collectors.toList());
     System.out.println(collectedLongs);
     * </pre>
     *
     * @see Recipe_3_2_Boxed_Streams Recipe 3.2. for converting streams of primitives to wrapper instances
     */
    protected static void creatingStreamsWithRangeAndRangeClosedMethods() {
        System.out.println("\n*** Creating a Stream Using the range() and rangeClosed() Methods ***");

        List<Integer> collectedIntegers = IntStream.range(10, 15)
                                                   .boxed() // necessary for Collectors to convert primitives to List<Integer>
                                                   .collect(Collectors.toList());
        System.out.println(collectedIntegers);

        List<Long> collectedLongs = LongStream.rangeClosed(10, 15)
                                              .boxed() // necessary for Collectors to convert primitives to List<Long>
                                              .collect(Collectors.toList());
        System.out.println(collectedLongs);
    }
}
