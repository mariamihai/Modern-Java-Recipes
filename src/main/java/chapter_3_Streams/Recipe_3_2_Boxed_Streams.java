package chapter_3_Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <h1>(3.2.) Boxed Streams</h1>
 *  * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - create a collection from a primitive stream
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         <ul style="list-style: circle;">
 *             <li>use the {@code boxed} method on {@link java.util.stream.Stream} to wrap the elements</li>
 *             <li>map the values using the appropriate wrapper class</li>
 *             <li>or use the three-argument form of the {@link java.util.stream.Stream#collect(Supplier, BiConsumer, BiConsumer)}
 *                 method</li>
 *         </ul>
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #usingTheMapToObjectMethod()}
 *                 - using the mapToObject() method
 *             </li>
 *             <li>
 *                 {@link #usingTheCollectMethod()}
 *                 - using the collect() method
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * @see Recipe_3_1_Creating_Streams Recipe 3.1. for examples of Streams
 */
public class Recipe_3_2_Boxed_Streams {


    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 3 - 3.2. Boxed Streams");


        System.out.println();
        // *** Using the mapToObject() Method ***
        usingTheMapToObjectMethod();

        System.out.println();
        // *** Using the collect() Method ***
        usingTheCollectMethod();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Using the mapToObject() Method</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;Integer&#62; collectedIntegers = IntStream.of(1, 3, 5, 7, 9).mapToObj(Integer::valueOf).collect(Collectors.toList());
     System.out.println(collectedIntegers);

     // Get the primitive
     IntStream intStream = Stream.of(new Integer(1), new Integer(2)).mapToInt(Integer::intValue);
     * </pre>
     */
    protected static void usingTheMapToObjectMethod() {
        System.out.println("\n*** Using the mapToObject() Method ***");

        List<Integer> collectedIntegers = IntStream.of(1, 3, 5, 7, 9).mapToObj(Integer::valueOf).collect(Collectors.toList());
        System.out.println(collectedIntegers);

        // Get the primitive
        IntStream intStream = Stream.of(new Integer(1), new Integer(2)).mapToInt(Integer::intValue);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Using the collect() Method</b> &#9674;&#9674;&#9674;
     *
     * <br>
     * The signature of the method is
     * <b>{@code <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R,R> combiner)}</b>.
     *
     * <br><br>
     * (Using this version of the {@code collect} method is not very common)
     *
     * <pre>
     ArrayList&#60;Integer&#62; collectedIntegers = IntStream.of(1, 3, 5, 7, 9)
             .collect(ArrayList&#60;Integer&#62;::new, // the Supplier - the constructor for ArrayList&#60;Integer&#62;
                      ArrayList::add,          // the accumulator - the add method - how to add a single element to the list
                      ArrayList::addAll);      // the combiner (used only during parallel operations) - addAll method - combines two lists into one
     System.out.println(collectedIntegers);
     * </pre>
     */
    protected static void usingTheCollectMethod() {
        System.out.println("\n*** Using the collect() Method ***");

        ArrayList<Integer> collectedIntegers = IntStream.of(1, 3, 5, 7, 9)
                .collect(ArrayList<Integer>::new, // the Supplier - the constructor for ArrayList<Integer>
                         ArrayList::add,          // the accumulator - the add method - how to add a single element to the list
                         ArrayList::addAll);      // the combiner (used only during parallel operations) - addAll method - combines two lists into one
        System.out.println(collectedIntegers);

    }

    /**
     * &#9674;&#9674;&#9674; <b>Using toArray Method</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     int[] ints = IntStream.of(1, 3, 5, 7, 9).toArray();
     Integer[] integers = Stream.of(new Integer(1)).toArray(Integer[]::new);
     * </pre>
     *
     */
    protected static void usingToArrayMethod() {
        System.out.println("\n*** Using the toArray() Method ***");

        int[] ints = IntStream.of(1, 3, 5, 7, 9).toArray();

        Integer[] integers = Stream.of(new Integer(1)).toArray(Integer[]::new);
    }
}
