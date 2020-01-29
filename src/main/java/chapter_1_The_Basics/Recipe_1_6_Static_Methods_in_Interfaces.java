package chapter_1_The_Basics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>(1.6.) Static Methods in Interfaces</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - add a class-level utility method to an interface, along with an implementation
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - add the {@code static} keyword on the interface method and add the implementation for it
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link Recipe_1_6_Static_Methods_in_Interfaces#sortingStringsNaturalOrder(List)}
 *                 - sorting Strings by natural order (lexicographically)
 *             </li>
 *             <li>
 *                 {@link Recipe_1_6_Static_Methods_in_Interfaces#sortingStringsReverseOrder(List)}
 *                 - sorting Strings by reverse order
 *             </li>
 *             <li>
 *                 {@link Recipe_1_6_Static_Methods_in_Interfaces#sortingStringsByLowerCase(List)}
 *                 - sorting Strings by lower case
 *             </li>
 *             <li>
 *                 {@link Recipe_1_6_Static_Methods_in_Interfaces#sortingStringsByNameLength(List)}
 *                 - sorting Strings by name length
 *             </li>
 *             <li>
 *                 {@link Recipe_1_6_Static_Methods_in_Interfaces#sortingStringsByNameLengthThenLexicographically(List)}
 *                 - sorting Strings by name length and then lexicographically
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * ❗ <b>The implementation of the class-level method cannot be overridden.</b>
 *
 * <br>
 * Access the method using the interface name. Classes do not need to implement an interface to use its static methods.
 *
 * <br>
 * Static methods in interfaces remove the need to create separate utility classes, but the option is still available
 * if a design calls for it.
 *
 * <br><br>
 * ❗ <b>Important:</b>
 * <ul>
 *     <li>Static methods must have an implementation</li>
 *     <li>You cannot override a static method</li>
 *     <li>Call static methods from an interface name</li>
 *     <li>You do not need to implement an interface to use the static methods</li>
 * </ul>
 */
public class Recipe_1_6_Static_Methods_in_Interfaces {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * <br>
     * Having a list of Bond actors
     * <b>({@code List<String> bonds = Arrays.asList("Connery", "Lazenby", "Moore", "Dalton", "Brosnan", "Craig");})</b>
     * sort the list.
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 1 - 1.6. Static Methods in Interfaces");

        System.out.println();
        List<String> bonds = Arrays.asList("Connery", "Lazenby", "Moore", "Dalton", "Brosnan", "Craig");

        sortingStringsNaturalOrder(bonds);
        sortingStringsReverseOrder(bonds);
        sortingStringsByLowerCase(bonds);
        sortingStringsByNameLength(bonds);
        sortingStringsByNameLengthThenLexicographically(bonds);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Sorting Strings by Natural Order</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; sorted = bonds.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
     * </pre>
     *
     * @param bonds list of Bond actors
     */
    protected static void sortingStringsNaturalOrder(List<String> bonds) {
        System.out.println("Natural order (lexicographical):");
        List<String> sorted = bonds.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.println(sorted);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Sorting Strings by Reverse Order</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; sorted = bonds.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
     * </pre>
     *
     * @param bonds list of Bond actors
     */
    protected static void sortingStringsReverseOrder(List<String> bonds) {
        System.out.println("Reverse lexicographical:");
        List<String> sorted = bonds.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(sorted);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Sorting Strings by Lower Case</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; sorted = bonds.stream().sorted(Comparator.comparing(String::toLowerCase)).collect(Collectors.toList());
     * </pre>
     *
     * @param bonds list of Bond actors
     */
    protected static void sortingStringsByLowerCase(List<String> bonds) {
        System.out.println("Sort by lower case:");
        List<String> sorted = bonds.stream().sorted(Comparator.comparing(String::toLowerCase)).collect(Collectors.toList());
        System.out.println(sorted);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Sorting Strings by Name Length</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; sorted = bonds.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
     * </pre>
     *
     * @param bonds list of Bond actors
     */
    protected static void sortingStringsByNameLength(List<String> bonds) {
        System.out.println("Sort by lower case:");
        List<String> sorted = bonds.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
        System.out.println(sorted);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Sorting Strings by Name Length Then Lexicographically</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; sorted = bonds.stream()
            .sorted(Comparator.comparing(String::length)
                    .thenComparing(Comparator.naturalOrder()))
            .collect(Collectors.toList());
     * </pre>
     *
     * @param bonds list of Bond actors
     */
    protected static void sortingStringsByNameLengthThenLexicographically(List<String> bonds) {
        System.out.println("Sort by name length, then equal lengths lexicographically:");
        List<String> sorted = bonds.stream()
                .sorted(Comparator.comparing(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
        System.out.println(sorted);
    }
}
