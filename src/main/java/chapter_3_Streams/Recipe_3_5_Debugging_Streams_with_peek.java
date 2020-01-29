package chapter_3_Streams;

import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * <h1>(3.5.) Debugging Streams with peek</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - see the individual elements of a stream as they are processed
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - invoke the {@link java.util.stream.Stream#peek(Consumer)} operation
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *
 *             </li>
 *             <li>
 *
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * The declaration of the {@code peek} method is:
 * <pre>
 * {@code
 *      Stream<T> peek (Consumer<? super T> action)
 * }
 * </pre>
 */
public class Recipe_3_5_Debugging_Streams_with_peek {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 3 - 3.5. Debugging Streams with peek");


        System.out.println();
        // *** Using the peek() Method ***
        usingThePeekMethod();
    }

    protected static void usingThePeekMethod() {
        int sum = IntStream.rangeClosed(1, 6)
                .peek(n -> System.out.printf("  original: %d%n", n))
                .map(n -> n * 2)
                .peek(n -> System.out.printf("   doubled: %d%n", n))
                .filter(n -> n % 3 == 0)
                .peek(n -> System.out.printf("<filtered>: %d%n", n))
                .sum();

    }
}
