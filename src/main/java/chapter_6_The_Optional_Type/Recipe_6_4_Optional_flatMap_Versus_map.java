package chapter_6_The_Optional_Type;

import java.util.Optional;
import java.util.function.Function;

/**
 * <h1>(6.4.) Optional flatMap Versus map</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - avoid wrapping an {@link java.util.Optional} inside another {@link java.util.Optional}.
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use the {@link java.util.Optional#flatMap(Function)} method
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #extractANameFromAnOptionalManager()}
 *                 - extract a name from an Optional manager
 *             </li>
 *             <li>
 *                 {@link #anOptionalWrappedInsideAnOptional()}
 *                 - an Optional wrapped inside an Optional
 *             </li>
 *             <li>
 *                 {@link #anOptionalWrappedInsideAnOptionalWrappedInsideAnOptional()}
 *                 - an Optional wrapped inside an Optional wrapped inside an Optional
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * The concept of {@code flatMap} is a general one and can be also applied to {@link java.util.Optional}. The
 * {@link Function} argument is applied to each element and produces a single result, in this case of type
 * {@code Optional<U>}. If the argument {@code T} exists, {@code flatMap} applies the function to it and returns an
 * {@link java.util.Optional} wrapping the contained value. if the argument is not present, the method returns an empty
 * {@link java.util.Optional}.
 *
 * <pre>
 * {@code
 * <U> Optional<U> flatMap (Function<? super T, Optional<U>> mapper)
 * }
 * </pre>
 *
 * @see chapter_3_Streams.Recipe_3_11_Stream_flatMap_Versus_map map and flatMap methods in Stream
 * @see Recipe_6_1_Creating_An_Optional wrapping a value inside an Optional
 * @see Recipe_6_3_Optional_in_Getters_and_Setters using Optional in a DAO layer
 * @see Recipe_6_5_Mapping_Optionals using the map method
 */
public class Recipe_6_4_Optional_flatMap_Versus_map {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 6 - 6.4. Optional flatMap Versus map");


        System.out.println();
        // *** Extract a Name from an Optional manager ***
        new Recipe_6_4_Optional_flatMap_Versus_map().extractANameFromAnOptionalManager();

        System.out.println();
        // *** An Optional Wrapped Inside an Optional ***
        new Recipe_6_4_Optional_flatMap_Versus_map().anOptionalWrappedInsideAnOptional();

        System.out.println();
        // *** An Optional Wrapped Inside an Optional Wrapped Inside an Optional ***
        new Recipe_6_4_Optional_flatMap_Versus_map().anOptionalWrappedInsideAnOptionalWrappedInsideAnOptional();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Extract a Name from an Optional Manager</b> &#9674;&#9674;&#9674;
     *
     * Get the name of the manager - you either have to get the contained value out of the {@link Optional}, or use the
     * {@code map} method.
     *
     * <br>
     * The {@code map} method applies the given function only if the {@link Optional} it's called on is not empty.
     *
     * <pre>
     * {@code
     * public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
     *      Objects.requireNonNull(mapper);
     *      if (!isPresent())
     *          // Returns Optional.empty but not the value from getBoss(). Doesn't call getBoss().
     *          return empty();
     *      else {
     *          // Gets the value, the name of the Manager and wraps it in an Optional.
     *          return Optional.ofNullable(mapper.apply(value));
     *      }
     * }
     * }
     * </pre>
     *
     * <pre>
     * {@code
     * // No boss set for this department
     * System.out.println("\tNo manager set for the department:");
     * Department d = new Department();
     * // Returns "Unknown"
     * System.out.println(d.getBoss().orElse(new Manager("Unknown")).getName());
     * // Returns Optional.empty
     * System.out.println(d.getBoss().map(Manager::getName));
     *
     * // Boss set for this department
     * System.out.println("\tManager set for the department:");
     * Manager m = new Manager("La la Manager");
     * d.setBoss(m);
     * // Returns "La la Manager"
     * System.out.println(d.getBoss().orElse(new Manager("Unknown")).getName());
     * // Returns Optional['La la Manager']
     * System.out.println(d.getBoss().map(Manager::getName));
     * }
     * </pre>
     */
    protected void extractANameFromAnOptionalManager() {
        System.out.println("\n*** Extract a Name from an Optional Manage ***");

        // No boss set for this department
        System.out.println("\tNo manager set for the department:");
        Department d = new Department();
        // Returns "Unknown"
        System.out.println(d.getBoss().orElse(new Manager("Unknown")).getName());
        // Returns Optional.empty
        System.out.println(d.getBoss().map(Manager::getName));


        // Boss set for this department
        System.out.println("\tManager set for the department:");
        Manager m = new Manager("La la Manager");
        d.setBoss(m);
        // Returns "La la Manager"
        System.out.println(d.getBoss().orElse(new Manager("Unknown")).getName());
        // Returns Optional['La la Manager']
        System.out.println(d.getBoss().map(Manager::getName));
    }

    /**
     * &#9674;&#9674;&#9674; <b>An Optional Wrapped Inside an Optional</b> &#9674;&#9674;&#9674;
     *
     * <br>
     * Using {@code flatMap} flattens the structure, so that you only get a single {@link Optional}.
     * <br>
     * flatMap doesn't wrap the result in an {@link Optional}.
     *
     * <pre>
     * {@code
     * public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
     *      Objects.requireNonNull(mapper);
     *      if (!isPresent())
     *          return empty();
     *      else {
     *          // Doesn't wrap the result in an Optional.
     *          return Objects.requireNonNull(mapper.apply(value));
     *      }
     * }
     * }
     * </pre>
     *
     * <pre>
     * {@code
     * Company c = new Company();
     * // Returns Optional.empty
     * System.out.println(c.getDepartment().flatMap(Department::getBoss).map(Manager::getName));
     *
     * Department d = new Department();
     * c.setDepartment(d);
     *
     * Manager m = new Manager("La la Manager");
     * // Returns Optional.empty
     * System.out.println(c.getDepartment().flatMap(Department::getBoss).map(Manager::getName));
     *
     * d.setBoss(m);
     * System.out.println(c.getDepartment()
     *                     // Optional[Optional[Manager]]
     *                     .map(Department::getBoss));
     * System.out.println(c.getDepartment()
     *                     // Optional[Manager]
     *                     .flatMap(Department::getBoss));
     *
     * //Optional[La la Manager]
     * System.out.println(c.getDepartment() // Optional<Department>
     *                     .flatMap(Department::getBoss) // Optional<Manager>
     *                     .map(Manager::getName)); // Optional<String>
     * }
     * </pre>
     *
     * {@code flatMap} gets a function with the return type {@link Optional} as argument and doesn't wrap the result
     * in an {@link Optional} as the result is already an {@link Optional}.
     * <br>
     * {@code map} gets a function with return type U as an argument and wraps the result in an {@code Optional<U>}.
     * In this case {@code map(Manager::getName)} wraps the {@code String} returned by the {@code getName} in an
     * {@code Optional<String>}
     *
     */
    protected void anOptionalWrappedInsideAnOptional() {
        System.out.println("\n*** An Optional Wrapped Inside an Optional ***");

        Company c = new Company();
        // Returns Optional.empty
        System.out.println(c.getDepartment().flatMap(Department::getBoss).map(Manager::getName));

        Department d = new Department();
        c.setDepartment(d);

        Manager m = new Manager("La la Manager");
        // Returns Optional.empty
        System.out.println(c.getDepartment().flatMap(Department::getBoss).map(Manager::getName));

        d.setBoss(m);
        System.out.println(c.getDepartment()
                // Optional[Optional[Manager]]
                .map(Department::getBoss));
        System.out.println(c.getDepartment()
                // Optional[Manager]
                .flatMap(Department::getBoss));

        //Optional[La la Manager]
        System.out.println(c.getDepartment() // Optional<Department>
                            .flatMap(Department::getBoss) // Optional<Manager>
                            .map(Manager::getName)); // Optional<String>
    }

    /**
     * &#9674;&#9674;&#9674; <b>An Optional Wrapped Inside an Optional Wrapped Inside an Optional</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     * {@code
     * Company c = new Company();
     * Department d = new Department();
     * c.setDepartment(d);
     * Manager m = new Manager("La la Manager");
     * d.setBoss(m);
     *
     * Optional<Company> company = Optional.of(c);
     * System.out.println(company // Optional<Company>
     *                      .flatMap(Company::getDepartment) // Optional<Department>
     *                      .flatMap(Department::getBoss) // Optional<Manager>
     *                      .map(Manager::getName)); // Optional<String>
     * }
     * </pre>
     */
    protected void anOptionalWrappedInsideAnOptionalWrappedInsideAnOptional() {
        System.out.println("\n*** An Optional Wrapped Inside an Optional Wrapped Inside an Optional ***");

        Company c = new Company();
        Department d = new Department();
        //;c.setDepartment(d);
        Manager m = new Manager("La la Manager");
        d.setBoss(m);

        Optional<Company> company = Optional.of(c);
        System.out.println(company // Optional<Company>
                            .flatMap(Company::getDepartment) // Optional<Department>
                            .flatMap(Department::getBoss) // Optional<Manager>
                            .map(Manager::getName)); // Optional<String>
    }

    /**
     * Manager.
     */
    protected class Manager {
        private String name;

        public Manager(String name) {
            this.name = name;
        }

        /**
         * &#9674;&#9674;&#9674; <b>getName()</b> &#9674;&#9674;&#9674;
         *
         * <br>
         * Assumed not null, no need for {@link Optional}.
         *
         * @return a String - the name of the {@link Manager}
         */
        public String getName() {
            return name;
        }
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
         * @param boss the {@link Manager}; might be null
         */
        public void setBoss(Manager boss) {
            this.boss = boss;
        }
    }

    /**
     * Company with a {@link Department}.
     */
    protected class Company {
        private Department department;

        /**
         * &#9674;&#9674;&#9674; <b>getDepartment()</b> &#9674;&#9674;&#9674;
         *
         * <br>
         * Might be null, so wrap the getter return in an {@link Optional}, but not the setter.
         *
         * <pre>
         * return Optional.ofNullable(department);
         * </pre>
         *
         *
         * @return {@link Optional} - null or the {@link Department}
         */
        public Optional<Department> getDepartment() {
            return Optional.ofNullable(department);
        }

        /**
         * &#9674;&#9674;&#9674; <b>setDepartment()</b> &#9674;&#9674;&#9674;
         *
         * <pre>
         * this.department = department;
         * </pre>
         *
         * @param department the {@link Department}; might be null
         */
        public void setDepartment(Department department) {
            this.department = department;
        }
    }
}
