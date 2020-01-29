package chapter_6_The_Optional_Type;

import java.util.Optional;

/**
 * <h1>(6.1.) Creating an Optional</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - return an {@link java.util.Optional} from sn existing value
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use {@link java.util.Optional#of(Object)}, {@link java.util.Optional#ofNullable(Object)} or
 *         {@link Optional#empty()}
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
 * Optional is a <i>value-based class</i>, meaning its instances are final and immutable (though they may contain
 * references to mutable objects); have no public constructors (they must be instantiated by factory methods) and have
 * implementations of {@code equals}, {@code hashCode}, and {@code toString} that are based only on their state.
 *
 * <br>
 * You can modify the contained value either with the original reference, or one retrieved by calling {@code get} on
 * the {@link Optional}. You can reassign the reference itself, which basically says that immutable is not the same
 * thing as {@code final}. You can't modify the {@link Optional} instance itself, because there are no methods
 * available to do so.
 *
 * <br><br>
 * The static factory methods to create an {@link Optional} are:
 * <pre>
 * {@code
 * static <T> Optional<T> empty()
 * static <T> Optional<T> of(T value)
 * static <T> Optional<T> ofNullable(T value)
 * }
 * </pre>
 *
 * Primitives cannot be null, so the {@link java.util.OptionalInt}, {@link java.util.OptionalLong} and
 * {@link java.util.OptionalDouble} classes have only an {@code of} method:
 * <pre>
 * {@code
 * static OptionalInt    of(int value)
 * static OptionalLong   of(long value)
 * static OptionalDouble of(double value)
 * }
 * </pre>
 * Instead of the {@code get} method, we have {@code getAsInt}, {@code getAsLOng} and {@code getAsDouble} methods.
 */
public class Recipe_6_1_Creating_An_Optional {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 6 - 6.1. Creating an Optional");


        System.out.println();
        // *** Creating an Optional with of() Method ***
        creatingAnOptionalWithOfMethod();

        System.out.println();
        // *** Creating an Optional with ofNullable() Method ***
        creatingAnOptionalWithOfNullableMethod();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Creating an Optional with of() Method</b> &#9674;&#9674;&#9674;
     */
    protected static void creatingAnOptionalWithOfMethod() {
        try {
            // Exception in thread "main" java.lang.NullPointerException
            //	at java.util.Objects.requireNonNull
            Optional.of(null);
        } catch (NullPointerException e) {}

        Integer i = 1;
        Optional<?> optional = i == null ? Optional.empty() : Optional.of(i);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Creating an Optional with ofNullable() Method</b> &#9674;&#9674;&#9674;
     */
    protected static void creatingAnOptionalWithOfNullableMethod() {
        Integer i = 1;
        Optional<?> optional = Optional.ofNullable(i);
        System.out.println(optional);

        i = null;
        optional = Optional.ofNullable(i);
        System.out.println(optional);
    }


}
