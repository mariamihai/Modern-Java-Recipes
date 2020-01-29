package chapter_2_The_java_util_function_Package;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * <h1>(2.2.) Recipe_2_2_Suppliers</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - implement {@link java.util.function.Supplier} interface
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use a lambda expression or method reference to implement {@code T get()} method of the
 *         {@link java.util.function.Supplier} interface
 *
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #useMathRandomAsASupplier()}
 *                 - use Math.random as a Supplier
 *             </li>
 *             <li>
 *                 {@link #findingANameFromACollection()}
 *                 - finding a name from a Collection
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * The implementation consists in providing a method that takes no arguments and returns the generic type.
 *
 * <br>
 * There is no requirement that a new or distinct result be returned each time the Supplier is returned. An eq. is
 * {@code Math.random} which takes no argument and returns a {@code double}. This can be assigned to a Supplier reference
 * and invoked at any time.
 *
 * <br>
 * One of the primary use cases for Recipe_2_2_Suppliers is to support the concept of <i>deferred execution</i> - to ensure that
 * a value is retrieved from a Supplier only when appropriate.
 *
 * <br>
 * Another example of this is {@link java.util.Optional#orElseGet(Supplier)} method. {@code Optional} is a nonnull object
 * that either wraps a value or is empty. It is typically returned by methods that may reasonably expect to have no result,
 * like finding a value in an empty collection.
 *
 * @see chapter_3_Streams.Recipe_3_9_Finding_the_First_Element_in_a_Stream Recipe 3.9. for order
 * @see chapter_5_Issues_with_Stream_Lambdas_and_Method_References.Recipe_5_7_LoggingWithASupplier Recipe 5.7. for Logging with a Supplier
 * @see chapter_6_The_Optional_Type.Recipe_6_1_Creating_An_Optional Chapter 6 for Optional
 * @see chapter_9_Parallelism_and_Concurrency.Recipe_9_4_The_Future_Interface Recipes 9.4. - 9.7. for CompletableFuture
 */
public class Recipe_2_2_Suppliers {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 2 - 2.2. Recipe_2_2_Suppliers");


        System.out.println();
        // *** Use Math.random as a Supplier ***
        useMathRandomAsASupplier();

        // *** Finding a Name from a Collection ***
        System.out.println();
        findingANameFromACollection();

    }

    /**
     * &#9674;&#9674;&#9674; <b>Use Math.random as a Supplier</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     // anonymous inner class implementation
     DoubleSupplier randomSupplier = new DoubleSupplier() {
        &#64;Override
        public double getAsDouble() {
            return Math.random();
        }
     };

     // expression lambda
     randomSupplier = () -&#62; Math.random();

     // method reference
     randomSupplier = Math::random;

     System.out.println(randomSupplier.getAsDouble());
     * </pre>
     */
    protected static void useMathRandomAsASupplier() {
        // anonymous inner class implementation
        DoubleSupplier randomSupplier = new DoubleSupplier() {
            @Override
            public double getAsDouble() {
                return Math.random();
            }
        };

        // expression lambda
        randomSupplier = () -> Math.random();

        // method reference
        randomSupplier = Math::random;

        System.out.println(randomSupplier.getAsDouble());
    }

    /**
     * &#9674;&#9674;&#9674; <b>Finding a Name from a Collection</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; names = Arrays.asList("Baaa", "Bbb", "Ccccc", "Cddd", "Eeee");
     Optional&#60;String&#62; first = names.stream().filter(name -&#62; name.startsWith("A")).findFirst();

     System.out.println(first);
     System.out.println(first.orElse("None"));

     // Forms the comma-separated collection, even when name is found
     System.out.println(first.orElse(String.format("No result found in %s",
                                                   names.stream().collect(Collectors.joining(", ")))));

     // Forms the comma-separated collection only if the Optional is empty
     System.out.println(first.orElseGet(() -&#62; String.format("No result found in %s",
                                                            names.stream().collect(Collectors.joining(", ")))));
     * </pre>
     *
     * It is possible to apply a filter so there are no elements remaining in the stream, so the method returns an
     * {@link Optional}. That {@link Optional} contains the desired element, or is empty.
     *
     * <br><br>
     * The {@link Optional#orElse(Object)} method is processed even if the value is found in the {@link Optional}. It is
     * ok to use it if the default is a simple string, but can be wasteful if processing is necessary to return a value.
     *
     * <br>
     * The {@link Optional#orElseGet(Supplier)} takes a {@link Supplier} as an argument. The advantage is that the
     * {@link Supplier#get()} method will only be invoked when the {@link Optional} is empty.
     *
     * <br><br>
     * Other examples:
     * <ul>
     *     <li>
     *         {@link Optional#orElseThrow(Supplier)} takes a {@code Supplier<X extends Exception>}
     *         - only executed if an exception occurs
     *     </li>
     *     <li>
     *         {@link java.util.Objects#requireNonNull(Object, Supplier)}
     *         - it customizes its response only if the first argument is null
     *     </li>
     *     <li>
     *         {@link java.util.concurrent.CompletableFuture#supplyAsync(Supplier)}
     *         - returns a {@link java.util.concurrent.CompletableFuture} that is asynchronously completed by a
     *         task running with the value obtained by calling the given {@link Supplier}
     *     </li>
     *     <li>
     *         {@link java.util.logging.Logger} class has overloads for all its logging methods that take a
     *         {@code Supplier<String>} rather than just a string
     *     </li>
     * </ul>
     *
     * @see chapter_3_Streams.Recipe_3_9_Finding_the_First_Element_in_a_Stream Recipe 3.9. for order
     * @see chapter_6_The_Optional_Type.Recipe_6_1_Creating_An_Optional Chapter 6 for Optional
     * @see chapter_9_Parallelism_and_Concurrency.Recipe_9_4_The_Future_Interface Recipes 9.4. - 9.7. for CompletableFuture
     */
    protected static void findingANameFromACollection() {
        List<String> names = Arrays.asList("Baaa", "Bbb", "Ccccc", "Cddd", "Eeee");

        Optional<String> first = names.stream().filter(name -> name.startsWith("A")).findFirst();

        System.out.println("*** Print Optional: ");
        System.out.println(first);
        System.out.println("*** Print none if nothing there: ");
        System.out.println(first.orElse("None"));

        // Forms the comma-separated collection, even when name is found
        System.out.println("*** Print orElse: ");
        System.out.println(first.orElse(
                String.format("No result found in %s", names.stream().collect(Collectors.joining(", ")))));

        // Forms the comma-separated collection only if the Optional is empty
        System.out.println("*** Print orElseGet: ");
        System.out.println(first.orElseGet(() ->
                String.format("No result found in %s", names.stream().collect(Collectors.joining(", ")))));
    }
}
