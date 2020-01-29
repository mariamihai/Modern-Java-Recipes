package chapter_2_The_java_util_function_Package;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>(2.1.) Recipe_2_1_Consumers</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - write lambda expressions that implement {@link java.util.function.Consumer}
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - implement the {@code void accept(T t)} method using a lambda expression or a method reference
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #printTheElementsOfACollection}
 *                 - print the elements of a collection
 *             </li>
 *             <li>
 *                 {@link #usePeek}
 *                 - use peek method
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * An implementation of a {@link java.util.function.Consumer} is the {@link Iterable#forEach(Consumer)} method which
 * passes each element of an iterable collection to the consumer argument.
 *
 * <br>
 * Primitive variations and two-argument version:
 * <ul>
 *     <li>
 *         {@link java.util.function.IntConsumer}
 *         - {@code void accept (int x)}
 *     </li>
 *     <li>
 *         {@link java.util.function.DoubleConsumer}
 *         - {@code void accept (double x)}
 *     </li>
 *     <li>
 *         {@link java.util.function.LongConsumer}
 *         - {@code void accept (long x)}
 *     </li>
 *     <li>
 *         {@link java.util.function.BiConsumer}
 *         - {@code void accept (T t, U u)}
 *         <br>
 *         Variations of this are {@link java.util.function.ObjIntConsumer} (takes a generic and an int),
 *         {@link java.util.function.ObjDoubleConsumer} and {@link java.util.function.ObjLongConsumer}.
 *     </li>
 * </ul>
 *
 * <br>
 * Recipe_2_1_Consumers are expected to operate via side effects.
 *
 * <br>
 * Other uses of the Consumer interface:
 * <ul>
 *     <li>
 *         {@code Optional.ifPresent(Consumer<? super T> consumer)}
 *         - if a value is present, invoke the specified consumer. Otherwise do nothing.
 *     </li>
 *     <li>
 *         {@code Stream.forEach(Consumer<? super T> action)}
 *         - performs an action for each element of the stream. Similar to this is
 *         {@link java.util.stream.Stream#forEachOrdered(Consumer)}, accessing elements in encounter order
 *     </li>
 *     <li>
 *         {@code Stream.peek(Consumer<? super T> action)}
 *         - returns a stream with the same elements as the existing stream, first performing the given action.
 *     </li>
 * </ul>
 *
 * @see chapter_3_Streams.Recipe_3_5_Debugging_Streams_with_peek
 */
public class Recipe_2_1_Consumers {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 2 - 2.1. Recipe_2_1_Consumers");


        System.out.println();
        // *** Print the Elements of a Collection***
        printTheElementsOfACollection();

        System.out.println();
        // *** Use peek() ***
        usePeek();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Print the Elements of a Collection</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; strings = Arrays.asList("this", "is", "a", "list", "of", "Strings");

     // anonymous inner class implementation
     strings.forEach(new Consumer&#60;String&#62;() {
        &#64;Override
        public void accept(String s) {
            System.out.println(s);
        }
     });

     // expression lambda
     strings.forEach(s -&#62; System.out.println(s));

     // method reference
     strings.forEach(System.out::println);
     * </pre>
     */
    protected static void printTheElementsOfACollection() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "Strings");

        // anonymous inner class implementation
        strings.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        // expression lambda
        strings.forEach(s -> System.out.println(s));

        // method reference
        strings.forEach(System.out::println);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Use peek method</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; collected = Stream.of("aa", "bb", "cc")
                .peek(System.out::println).collect(Collectors.toList());
     * </pre>
     */
    protected static void usePeek() {
        List<String> collected = Stream.of("aa", "bb", "cc").peek(System.out::println).collect(Collectors.toList());
        System.out.println(collected);
    }
}
