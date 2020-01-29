package chapter_3_Streams;

import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * <h1>(3.6.) Converting Strings to Streams and back</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - use the idiomatic {@link java.util.stream.Stream} processing techniques instead of looping over individual
 *         characters of a String
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use <b>{@link CharSequence#chars()}</b> or <b>{@link CharSequence#codePoints()}</b> to convert a String to an
 *         {@link java.util.stream.IntStream}. Convert back to a String using the
 *         <b>{@link java.util.stream.IntStream#collect(Supplier, ObjIntConsumer, BiConsumer)}</b> method.
 *
 *         <br>
 *         (The difference between the two methods has to do with how Java handles UTF-16-encoded characters as opposed
 *         to the full Unicode set of code points)
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         {@link #checkPalindromes()}
 *          - run the two implementations of checking if a String is a palindrome
 *         <ul>
 *             <li>
 *                 {@link #isPalindromeBeforeJava8(String)}
 *                 - the implementation before Java 8 (before Streams)
 *             </li>
 *             <li>
 *                 {@link #isPalindromeAfterJava8(String)}
 *                 - the implementationm using Streams
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * {@code String} is not part of the {@code Collection} framework and it doesn't implement {@code Iterable} so there is
 * no {@code stream} factory method to convert one to a {@code stream}.
 * <br>
 * At the same time, in {@code Arrays.stream} there isn't an overloading for {@code char[]}.
 *
 * <br><br>
 * Converting a stream of charcters back to a String can be done with {@link java.util.stream.Stream#collect(Collector)}
 * method, but there isn't a pre-defined {@link Collector} that will take a stream of characters and assemble it into
 * a String.
 *
 * @see chapter_4_Comparators_and_Collectors.Recipe_4_9_Implementing_the_Collector_Interface
 */
public class Recipe_3_6_Converting_Strings_to_Streams_and_Back {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 3 - 3.6. Converting Strings to Streams and back");


        System.out.println();
        // *** Check Palindromes ***
        checkPalindromes();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Check Palindromes</b> &#9674;&#9674;&#9674;
     *
     * <br>
     * Runs the 2 implementations:
     * <ul>
     *     <li>
     *         {@link #isPalindromeBeforeJava8(String)}
     *         - the implementation before Java 8 (before Streams)
     *     </li>
     *     <li>
     *         {@link #isPalindromeAfterJava8(String)}
     *         - the implementationm using Streams
     *     </li>
     * </ul>
     *
     */
    protected static void checkPalindromes() {
        System.out.println("\n*** Check Palindromes *** ");

        // Before Java 8
        System.out.println("Before Java 8:");
        System.out.println("abcba : " + isPalindromeBeforeJava8("abcba"));
        System.out.println("abcde : " + isPalindromeBeforeJava8("abcde"));
        System.out.println("a@c!a : " + isPalindromeBeforeJava8("a@c!a"));

        // Java 8
        System.out.println("       Java 8:");
        System.out.println("abcba : " + isPalindromeAfterJava8("abcba"));
        System.out.println("abcde : " + isPalindromeAfterJava8("abcde"));
        System.out.println("a@c!a : " + isPalindromeAfterJava8("a@c!a"));
    }

    /**
     * &#9674;&#9674;&#9674; <b>Check Palindroms before Java 8</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     StringBuilder sb = new StringBuilder();
     for(char c : string.toCharArray()) {
        if(Character.isLetterOrDigit(c)) {
            sb.append(c);
        }
     }

     String forward = sb.toString().toLowerCase();
     String backward = sb.reverse().toString().toLowerCase();

     return forward.equals(backward);
     * </pre>
     *
     * @param string the String to be checked
     * @return the String si a palindrome or not
     */
    protected static boolean isPalindromeBeforeJava8(String string) {
        StringBuilder sb = new StringBuilder();
        for(char c : string.toCharArray()) {
            if(Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }

        String forward = sb.toString().toLowerCase();
        String backward = sb.reverse().toString().toLowerCase();

        return forward.equals(backward);
    }


    /**
     * &#9674;&#9674;&#9674; <b>Check Palindroms after Java 8</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     String forward = string.toLowerCase().codePoints()
            .filter(Character::isLetterOrDigit)
            .collect(StringBuilder::new,
                     StringBuilder::appendCodePoint,
                     StringBuilder::append)
            .toString();

     String backward = new StringBuilder(forward).reverse().toString();

     return forward.equals(backward);
     * </pre>
     *
     * @param string the String to be checked
     * @return the String si a palindrome or not
     */
    protected static boolean isPalindromeAfterJava8(String string) {
        String forward = string.toLowerCase().codePoints()
                .filter(Character::isLetterOrDigit)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();

        String backward = new StringBuilder(forward).reverse().toString();

        return forward.equals(backward);
    }
}
