package chapter_6_The_Optional_Type;

import java.util.Optional;

/**
 * <h1>(6.3. Optional in Getters and Setters) </h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - use {@link java.util.Optional} in accessors and mutators
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - wrap the result of getter methods in {@code Optionals}, but not do the same for setters, and especially
 *         not for attributes
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link Manager} and {@link Department}
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * The {@link java.util.Optional} class was deliberately designed <i><b>not</b></i> to be serialized, so you don't want
 * to use it to wrap fields in a class.
 * <br>
 * The preferred mechanism for adding {@code Optionals} in getters and setters is to wrap nullable attributes in them
 * when returned from getter methods, but not do the same in setters.
 *
 * @see Recipe_6_1_Creating_An_Optional wrapping values in an Optional
 * @see Recipe_6_5_Mapping_Optionals converting a collection of IDs into a collection of employees
 */
public class Recipe_6_3_Optional_in_Getters_and_Setters {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 6 - 6.3. Optional in Getters and Setters");
    }

    /**
     * Manager.
     */
    protected class Manager{

    }

    /**
     * Department with a {@link Manager}.
     */
    protected class Department {
        private Manager boss;

        /**
         * &#9674;&#9674;&#9674; <b>getBoss()</b> &#9674;&#9674;&#9674;
         *
         * <br>
         * Might be null, so wrap the getter return in an {@link Optional}, but not the setter.
         *
         * <pre>
         * return Optional.ofNullable(boss);
         * </pre>
         *
         * @return {@link Optional} - null or the {@link Manager}
         */
        public Optional<Manager> getBoss() {
            return Optional.ofNullable(boss);
        }

        /**
         * &#9674;&#9674;&#9674; <b>setBoss()</b> &#9674;&#9674;&#9674;
         *
         * <pre>
         * this.boss = boss;
         * </pre>
         *
         * @param boss the {@link Manager}; might be null
         */
        public void setBoss(Manager boss) {
            this.boss = boss;
        }
    }
}
