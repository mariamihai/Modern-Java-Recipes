package chapter_6_The_Optional_Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>(6.5.) Mapping Optionals</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - you want to apply a function to a collection of {@link java.util.Optional} instances, but only if they
 *         contain a value
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use {@link java.util.Optional#map(Function)}
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #findingEmployeesByIds()}
 *                 - finding Employees by ids
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * Java 9 adds a {@code stream} method to {@link Optional}. If the {@link Optional} is not empty, it returns a one-element
 * stream wrapping the contained value. Otherwise it returns an empty stream.
 *
 * @see chapter_3_Streams.Recipe_3_11_Stream_flatMap_Versus_map flatMap method on streams
 * @see Recipe_6_3_Optional_in_Getters_and_Setters use Optional in a DAO layer
 * @see Recipe_6_4_Optional_flatMap_Versus_map flatMap method on Optionals
 * @see chapter_10_Java_9_Additions.Recipe_10_6_Optional_stream_or_ifPresentOrElse Java 9 recipe for newly added methods tos Optional
 */
public class Recipe_6_5_Mapping_Optionals {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 6 - 6.5. Mapping Optionals");


        System.out.println();
        // *** Finding Employees by Ids***
        new Recipe_6_5_Mapping_Optionals().findingEmployeesByIds();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Finding Employees by Ids</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     * {@code
     * List<Integer> ids = Arrays.asList(1, 2, 3);
     * Employees employeeList = new Employees();
     *
     * Employee employee = new Employee(1);
     * employeeList.addEmployee(employee);
     * employee = new Employee(2);
     * employeeList.addEmployee(employee);
     *
     * System.out.println(employeeList.findingEmployeesByIds(ids));
     * }
     * </pre>
     */
    protected void findingEmployeesByIds() {
        System.out.println("\n*** Finding Employees by Ids ***");
        List<Integer> ids = Arrays.asList(1, 2, 3);
        Employees employeeList = new Employees();

        Employee employee = new Employee(1);
        employeeList.addEmployee(employee);
        employee = new Employee(2);
        employeeList.addEmployee(employee);

        System.out.println("\tWith map():");
        //System.out.println(employeeList.findingEmployeesByIds(ids));
        System.out.println("\tWith flatMap():");
        System.out.println(employeeList.findingEmployeesByIdsWithFlatMap(ids));
    }

    /**
     * List of employees.
     */
    protected class Employees {
        List<Employee> employees;

        public Employees() {
            employees = new ArrayList<>();
        }

        public void addEmployee(Employee employee) {
            employees.add(employee);
        }

        /**
         * Find an Employee by Id.
         *
         * <pre>
         * {@code
         * return employees.stream().filter(e -> id == e.getId()).findFirst();
         * }
         * </pre>
         *
         * @param id {@link Employee}'s id
         * @return {@code Optional<Employee>}
         */
        public Optional<Employee> findEmployeeById(int id) {
            return employees.stream().filter(e -> id == e.getId()).findFirst();
        }

        /**
         * Finding Employees by Ids.
         *
         * <pre>
         * {@code
         * return ids.stream().map(this::findEmployeeById) // Stream<Optional<Employee>>
         *                     .filter(Optional::isPresent) // Remove empty Optionals
         *                     .map(Optional::get) // Retrieve values you know exist
         *                     .collect(Collectors.toList());
         * }
         * </pre>
         *
         * @param ids list of {@link Employee} ids
         * @return list of {@link Employee}
         */
        public List<Employee> findingEmployeesByIds(List<Integer> ids) {
            return ids.stream().map(this::findEmployeeById) // Stream<Optional<Employee>>
                    .filter(Optional::isPresent) // Remove empty Optionals
                    .map(Optional::get) // Retrieve values you know exist
                    .collect(Collectors.toList());
        }

        /**
         * Finding Employees by Ids with flatMap.
         *
         * <pre>
         * {@code
         * return ids.stream()
         *      .map(this::findEmployeeById) // Stream<Optional<Employee>>
         *      .filter(Optional::isPresent) // Remove empty Optionals
         *      .flatMap(optional ->
         *          optional.map(Stream::of) // Turns nonempty Optional<Employee> into <Optional<Stream<Employee>>
         *                  .orElseGet(Stream::empty)) // Extracts the Stream><Employee> from the Optional
         *      .collect(Collectors.toList());
         * }
         * </pre>
         *
         * @param ids list of {@link Employee} ids
         * @return list of {@link Employee}
         */
        public List<Employee> findingEmployeesByIdsWithFlatMap(List<Integer> ids) {
            ids.stream()
                    .map(this::findEmployeeById) // Stream<Optional<Employee>>
                    .filter(Optional::isPresent) // Remove empty Optionals
                    .flatMap(optional ->
                            optional.map(Stream::of)
                                    .orElseGet(Stream::empty));

            return ids.stream()
                    .map(this::findEmployeeById) // Stream<Optional<Employee>>
                    .filter(Optional::isPresent) // Remove empty Optionals
                    .flatMap(optional ->
                            optional.map(Stream::of) // Turns nonempty Optional<Employee> into <Optional<Stream<Employee>>
                                    .orElseGet(Stream::empty)) // Extracts the Stream><Employee> from the Optional
                    .collect(Collectors.toList());
        }

    }

    /**
     * Employee class.
     */
    protected class Employee {
        private Integer id;

        public Employee(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    '}';
        }
    }
}
