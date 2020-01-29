package chapter_1_The_Basics;

/**
 * <h1>(1.4.) Functional Interfaces</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - use existing functional interface or write your own
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - create an interface with a single abstract method and add the {@code @FunctionalInterface} annotation from
 *         the {@code java.lang} package
 *
 *         <br>
 *         - the interface will be the target for a lambda expresion or method reference
 *
 *         <br>
 *         - the {@code @FunctionalInterface} is not required but should be used as it triggers a compile-time check that
 *         the interface satisfies the requirement. The annotation also generates a statement in the Javadoc.
 *
 *         <br>
 *         <img src="doc-files/MyInterface.png" width="1000" alt="showing the explanation in Javadoc">
 *
 *         <br>
 *         - <i>functional interfaces</i> can have {@code default} or {@code static} methods as well. These methods have
 *         implementation so they don't count against the SAM (single abstract method) requirement.
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link MyInterface}
 *                 - functional interface which uses the {@code @FunctionalInterface} annotation
 *             </li>
 *             <li>
 *                 {@link MyChildInterface}
 *                 - functional interface which uses the {@code @FunctionalInterface} annotation
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 */
public class Recipe_1_4_Functional_Interfaces {
    public static void main(String[] args) {
        System.out.println("Chapter 1 - 1.4. Functional Interfaces");
    }

    /**
     * Functional interface which uses the {@code @FunctionalInterface} annotation
     *
     * <br>
     * If there is no method that satisfy the check of the annotation - <i>No target method found</i>
     *
     * <br>
     * If there are multiple methods that satisfy the check of the annotation - <i>Multiple non-overriding abstract methods found</i>
     *
     *
     */
    @FunctionalInterface
    protected interface MyInterface {
        public int myMethod();

        // public int myOtherMethod();

        default String sayHello() {
            return "Hello, World";
        }

        static void myStaticMethod() {
            System.out.println("I am a static method in an interface.");
        }
    }

    /**
     * Functional interface which uses the {@code @FunctionalInterface} annotation
     *
     * <br>
     * It extends {@link MyInterface} which already has an abstract (single) method.
     *
     * <br>
     * Adding a new abstract method in the child interface will trigger <i>Multiple non-overriding abstract methods found</i>
     * because of the {@code @FunctionalInterface} annotation. If the annotation is removed, the code will compile but the
     * interface cannot be the target of a lambda expression.
     *
     */
    @FunctionalInterface
    protected interface  MyChildInterface extends MyInterface {
        //int anotherMethod();

        /**
         * &#9674;&#9674;&#9674; <b>Overriding equals method</b> &#9674;&#9674;&#9674;
         *
         * <br>
         * The rules for functional interfaces say that methods from {@link Object} don't count against the SAM limit,
         * so interfaces that declare methods from Object as abstract are still functional interfaces.
         *
         * @param obj object
         * @return boolean, checks equality
         */
        boolean equals(Object obj);
    }
}
