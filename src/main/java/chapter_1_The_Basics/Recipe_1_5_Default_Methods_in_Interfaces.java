package chapter_1_The_Basics;

/**
 * <h1>(1.5.) Default Methods in Interfaces</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *        - provide an implementation of a method inside an interface
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - add the {@code default} keyword on the interface method and add the implementation for it
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link Employee}
 *                 - interface containing a default method with an implementation in terms of the other methods of
 *                 the interface
 *             </li>
 *             <li>
 *                 {@link EmployeeImp}
 *                 - implementation of the {@link Employee} interface which implements the
 *                 {@link Employee#getFirstName()} and {@link Employee#getLastName()} methods
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 */
public class Recipe_1_5_Default_Methods_in_Interfaces {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 1 - 1.5. Default Methods in Interfaces");

        System.out.println();

        Recipe_1_5_Default_Methods_in_Interfaces dmi = new Recipe_1_5_Default_Methods_in_Interfaces();
        Employee employee = dmi.new EmployeeImp("first", "last");
        System.out.println(employee.getCompleteName());
    }


    /**
     * Interface containing a default method with an implementation in terms of the other methods of
     * the interface
     *
     */
    protected interface Employee {

        String getFirstName();

        String getLastName();

        /**
         * &#9674;&#9674;&#9674; <b>Get the complete name of the employee based on the first and last name</b> &#9674;&#9674;&#9674;
         *
         * <pre>
         return String.format("Employee - %s %s", getFirstName(), getLastName());
         * </pre>
         *
         * @return the complete name of the {@link Employee}
         */
        default String getCompleteName() {
            return String.format("Employee - %s %s", getFirstName(), getLastName());
        }
    }

    /**
     * Implementation of the {@link Employee} interface which implements the
     * {@link Employee#getFirstName()} and {@link Employee#getLastName()} methods
     *
     */
    protected class EmployeeImp implements Employee {
        protected String firstName, lastName;

        public EmployeeImp(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String getFirstName() {
            return firstName;
        }

        @Override
        public String getLastName() {
            return lastName;
        }
    }
}
