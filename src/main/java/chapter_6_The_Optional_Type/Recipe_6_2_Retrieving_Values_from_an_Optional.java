package chapter_6_The_Optional_Type;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <h1>(6.2.) Retrieving Values from an Optional</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - extract a contained value from an {@link java.util.Optional}
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use {@code get}, {@code orElse}, {@code isPresent} methods
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #useIsPresentMethod()}
 *                 - use isPresent() method
 *             </li>
 *             <li>
 *                 {@link #useOrElseMethod()}
 *                 - use orElse() method
 *             </li>
 *             <li>
 *                 {@link #useOrElseGetMethod()}
 *                 - use orElseGet() method
 *             </li>
 *             <li>
 *                 {@link #useOrElseThrowMethod()}
 *                 - use orElseThrow() method
 *             </li>
 *             <li>
 *                 {@link #useIfPresentMethod()}
 *                 - use ifPresent() method
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * You should never call the {@code get} method on an {@link java.util.Optional} unless you're sure it contains a value
 * or an {@link java.util.NoSuchElementException} exception is thrown.
 *
 * <br><br>
 * ❗ {@code orElse} returns a value which is always created, whether the value exists in the {@link Optional} or not,
 * while {@code olElseGet} uses a {@link java.util.function.Supplier}, which is only executed if the {@link Optional} is empty.
 * <pre>
 optional.orElse(new ComplexObject()); // always creates the new object
 optional.orElseGet(() -&#62; new ComplexObject()); // only creates object if necessary
 * </pre>
 *
 * Using a {@link java.util.function.Supplier} as a method is an example of <i>deferred</i> or <i>lazy execution</i>. It
 * allows you to avoid invoking the {@code get} method on the {@link java.util.function.Supplier} until necessary.
 *
 * @see chapter_1_The_Basics.Recipe_1_3_Constructor_References Constructor references
 * @see chapter_2_The_java_util_function_Package.Recipe_2_2_Suppliers Suppliers
 * @see chapter_3_Streams.Recipe_3_9_Finding_the_First_Element_in_a_Stream findAny and findFirst methods in Stream that return an Optional
 */
public class Recipe_6_2_Retrieving_Values_from_an_Optional {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 6 - 6.2. Retrieving Values from an Optional");


        System.out.println();
        // *** Use isPresent() Method ***
        useIsPresentMethod();

        System.out.println();
        // *** Use orElse() Method ***
        useOrElseMethod();

        System.out.println();
        // *** Use orElseGet() Method ***
        useOrElseGetMethod();

        System.out.println();
        // *** Use orElseThrow() Method ***
        useOrElseThrowMethod();

        System.out.println();
        // *** Use ifPresent() Method ***
        useIfPresentMethod();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Use isPresent() Method</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     Optional&#60;String&#62; firstOdd = Stream.of("aaa", "b", "ccccc").filter(s -&#62; s.length() % 2 == 0).findFirst();

     //System.out.println(firstOdd.get()); // throwns NoSuchElementException
     System.out.println(firstOdd.isPresent() ? firstOdd.get() : "No even length strings");
     * </pre>
     */
    protected static void useIsPresentMethod() {
        System.out.println("\n*** Use isPresent() Method ***");

        Optional<String> firstOdd = Stream.of("aaa", "b", "ccccc").filter(s -> s.length() % 2 == 0).findFirst();

//        System.out.println(firstOdd.get()); // throwns NoSuchElementException
        System.out.println(firstOdd.isPresent() ? firstOdd.get() : "No even length strings");
    }

    /**
     * &#9674;&#9674;&#9674; <b>Use orElse() Method</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     Optional&#60;String&#62; firstOdd = Stream.of("aaa", "b", "ccccc").filter(s -&#62; s.length() % 2 == 0).findFirst();

     System.out.println(firstOdd.orElse("No even length strings"));
     * </pre>
     *
     * Variations of {@code orElse}:
     * <pre>
     * {@code
     * orElse (T other) - returns the value if present, otherwise returns the value other
     * orElseGet (Supplier<? extends T> other) - returns the value if present, otherwise it invokes the Supplier an returns the result
     * orElseThrow (Supplier<? extends X> exceptionSupplier) - returns the value if present, otherwise throws an exception created by the Supplier
     * }
     * </pre>
     */
    protected static void useOrElseMethod() {
        System.out.println("\n*** Use orElse() Method ***");

        Optional<String> firstOdd = Stream.of("aaa", "b", "ccccc").filter(s -> s.length() % 2 == 0).findFirst();

        System.out.println(firstOdd.orElse("No even length strings"));
    }

    /**
     * &#9674;&#9674;&#9674; <b>Use orElseGet() Method</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     Optional&#60;String&#62; findFirst = Stream.of("aaa", "abb", "acc").filter(s -&#62; s.startsWith("b")).findFirst();
     System.out.println(findFirst.orElseGet(() -&#62; "No object"));

     findFirst = Stream.of("aaa", "abb", "acc").filter(s -&#62; s.startsWith("a")).findFirst();
     System.out.println(findFirst.orElseGet(() -&#62; "No object"));
     * </pre>
     *
     * <pre>
     * {@code
     * public T orElseGet (Supplier<? extends T> other) {
     *     // value is a final attribute of type T in Optional
     *     return value != null ? value : other.get();
     * }
     * }
     * </pre>
     */
    protected static void useOrElseGetMethod() {
        System.out.println("\n*** Use orElseGet() Method ***");

        Optional<String> findFirst = Stream.of("aaa", "abb", "acc").filter(s -> s.startsWith("b")).findFirst();
        System.out.println(findFirst.orElseGet(() -> "No object"));

        findFirst = Stream.of("aaa", "abb", "acc").filter(s -> s.startsWith("a")).findFirst();
        System.out.println(findFirst.orElseGet(() -> "No object"));

        // TODO - check the String Pool (?)
    }

    /**
     * &#9674;&#9674;&#9674; <b>Use orElseThrow() Method</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     * {@code
    * <X extends Throwable> T orElseThrow (Supplier<? extends X> exceptionSupplier) throws X {
     *      if (value != null) {
     *          return value;
     *      } else {
     *          throw exceptionSupplier.get();
     *      }
     * }
     * }
     * </pre>
     *
     * The constructor reference used as the {@link java.util.function.Supplier} argument isn't executed when  the
     * {@link Optional} contains a value.
     */
    protected static void useOrElseThrowMethod() {
        System.out.println("\n*** Use orElseThrow() Method ***");

        Optional<String> findFirst = Stream.of("aaa", "abb", "acc").filter(s -> s.startsWith("b")).findFirst();

        try {
            System.out.println(findFirst.orElseThrow(NoSuchElementException::new));
        } catch(NoSuchElementException e) {
            System.out.println("Catched exception.");
        }
    }

    /**
     * &#9674;&#9674;&#9674; <b>Use ifPresent() Method</b> &#9674;&#9674;&#9674;
     * <pre>
     * {@code
     * Optional<String> findFirst = Stream.of("aaa", "abb", "acc").filter(s -> s.startsWith("a")).findFirst();
     * findFirst.ifPresent(val -> System.out.println("Found a value."));
     * }
     * </pre>
     */
    protected static void useIfPresentMethod() {
        System.out.println("\n*** Use ifPresent() Method ***");

        Optional<String> findFirst = Stream.of("aaa", "abb", "acc").filter(s -> s.startsWith("a")).findFirst();
        findFirst.ifPresent(val -> System.out.println("Found a value."));
    }
}
