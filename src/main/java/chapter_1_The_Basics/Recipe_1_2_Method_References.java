package chapter_1_The_Basics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>(1.2.) Method References</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - use a method reference to access an existing method and treat it like a lambda expression
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *        - use {@code "::" } to separate an instance reference or class name from the method
 *     </li>
 *
 *     <li><h2>Syntax</h2>
 *         <ul>
 *             <li>
 *                 <b>{@code object::instanceMethod}</b>
 *                 - an instace method using a reference to the supplied object (eq., {@code System.out::println})
 *             </li>
 *             <li>
 *                 <b>{@code Class::staticMethod}</b>
 *                 - static method (eq., {@code Math::max})
 *             </li>
 *             <li>
 *                 <b>{@code Class:instanceMethod}</b>
 *                 - invoke the instance method on a reference to an object supplied by the context (eq., {@code String::length})
 *             </li>
 *         </ul>
 *
 *         <br>
 *         Lambda expressions and method references never exist in a vacuum - there's always a context.
 *
 *         <br><br>
 *         In the case of an <i>object reference</i>, the context will supply the argument(s).
 *         <br>
 *         &emsp;&emsp;{@code System.out::println} is the equivalent of {@code x -> System.out.println(x)}
 *         <br>
 *         &emsp;&emsp;&emsp;&emsp;The context provides the value of x, which is used as the method argument.
 *         <br>
 *         &emsp;&emsp;{@code Math::max} is the equivalent of {@code (x,y) -> Math.max(x,y)}
 *
 *         <br><br>
 *         In the case of <i>instance methods through class name</i>, when the context provides x, it is used as the target of
 *         the method, rather than as an argument.
 *         <br>
 *         &emsp;&emsp;{@code String::length} is the equivalent of {@code x -> x.length()}
 *
 *         <br><br>
 *         If you refer to a method that takes multiple arguments via the class name, the first element supplied by the context
 *         becomes the target and the remaining elements are arguments to the method.
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #usingAMethodReferenceToAccessPrintln()}
 *                 - using a method reference to access {@code println}
 *             </li>
 *             <li>
 *                 {@link #invokingAMultipleInstanceMethodFromAClassReference()}
 *                 - invoking a multiple instance method from a class reference
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * If a lambda expression is essentially treating a method as though it was an object, then a method reference treats
 * an existing method as though it was a lambda.
 *
 * <br>
 * A method reference ios shorter than a lambda expression and it makes code easier to read by including the name of
 * the class containing the method.
 *
 * <br>
 * A method reference is essentially an abbreviated syntax for a lambda. Lambda expressions are more general, in that
 * each reference has an equivalent lambda expression but not vice versa.
 *
 * <br>
 * You can use {@code this} or {@code super} as the left side of a method reference if there is any ambiguity.
 */
public class Recipe_1_2_Method_References {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 1 - 1.2. Method References");


        System.out.println();
        // *** Using a Method Reference to access Println ***
        usingAMethodReferenceToAccessPrintln();

        // *** Invoking a Multiple Instance Method from a Class Reference ***
        invokingAMultipleInstanceMethodFromAClassReference();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Using a Method Reference to access Println</b> &#9674;&#9674;&#9674;
     *
     * <br>
     * The "::" notation provides the reference to the println method on {@link System#out} instance (reference to {@link java.io.PrintStream}).
     * No parentheses are placed at the end of the method reference.
     *
     * <pre>
     // Using lambda expression
     Stream.of(1, 2).forEach(x -&#62; System.out.println(x));

     // Using a method reference
     Stream.of(1, 2).forEach(System.out::println);

     // Assigning the method reference to a functional interface
     Consumer&#60;Integer&#62; printer = System.out::println;
     Stream.of(1, 2).forEach(printer);

     // Using a method reference to a static method
     Stream.generate(Math::random) // static method
           .limit(10)
           .forEach(System.out::println); // instance method
     * </pre>
     *
     * Math::random is a <i>Supplier</i> - functional interface whose single abstract method (SAM) takes no arguments
     * and produces a single result.
     *
     * <br>
     * System.out::println is a <i>Consumer</i> - a functional interface whose single abstratc method (SAM) takes a list
     * of arguments and doesn't produce anything.
     */
    protected static void usingAMethodReferenceToAccessPrintln() {
        System.out.println("\n*** Using a Method Reference to access Println ***");
        // Using lambda expression
        Stream.of(1, 2).forEach(x -> System.out.println(x));

        // Using a method reference
        Stream.of(1, 2).forEach(System.out::println);

        // Assigning the method reference to a functional interface
        Consumer<Integer> printer = System.out::println;
        Stream.of(1, 2).forEach(printer);

        // Using a method reference to a static method
        Stream.generate(Math::random) // static method
              .limit(2)
              .forEach(System.out::println); // instance method
    }

    /**
     * &#9674;&#9674;&#9674; <b>Invoking a Multiple Instance Method from a Class Reference</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; strings = Arrays.asList("b", "c", "a");
     // Lambda expression
     List&#60;String&#62; sortedLE = strings.stream().sorted((s1, s2) -&#62; s1.compareTo(s2)).collect(Collectors.toList());
     System.out.println(sortedLE);

     // MethodReference
     List&#60;String&#62; sortedMR = strings.stream().sorted(String::compareTo).collect(Collectors.toList());
     System.out.println(sortedMR);
     * </pre>
     *
     * The method reference syntax, using the class name String, invokexs the compareTo method on the first element
     * (s1 in the lambda expression) and uses the second element (s2) as the argument to the method.
     *
     * <br>
     * In stream processing, you frequently access an instance method using the class name in a method reference if you
     * are processing a series of inputs.
     * <pre>
     Stream.of("aa", "asf", "asafsasaf")
           .map(String::length) // instance method via class name
           .forEach(System.out::println); // instance method via object reference
     * </pre>
     */
    protected static void invokingAMultipleInstanceMethodFromAClassReference() {
        System.out.println("\n*** Invoking a Multiple Instance Method from a Class Reference ***");

        List<String> strings = Arrays.asList("b", "c", "a");
        // Lambda expression
        List<String> sortedLE = strings.stream().sorted((s1, s2) -> s1.compareTo(s2))
                .collect(Collectors.toList());
        System.out.println(sortedLE);

        // MethodReference
        List<String> sortedMR = strings.stream().sorted(String::compareTo)
                .collect(Collectors.toList());
        System.out.println(sortedMR);

        // Using the class name in a method reference
        Stream.of("aa", "asf", "asafsasaf")
              .map(String::length) // instance method via class name
              .forEach(System.out::println); // instance method via object reference
    }
}
