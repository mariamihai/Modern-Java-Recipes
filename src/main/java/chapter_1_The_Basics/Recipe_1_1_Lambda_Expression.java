package chapter_1_The_Basics;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * <h1>(1.1.) Lambda Expression</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - use lambda expression
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use lambda expression syntax and assign the result to a reference of functional interface type
 *         <br>
 *         - <b>functional interface</b> - interface with a single abstract method (SAM)
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 Methods for <b>{@link Runnable}</b> examples (the <b>{@link Runnable#run()}</b> method doesn't take any arguments)
 *                  <ul>
 *                      <li>
 *                          {@link #anonymousInnerClassForRunnable()}
 *                          - anonymous inner class implementation of {@link Runnable}
 *                      </li>
 *                      <li>
 *                          {@link #lambdaExpressionForRunnable()}
 *                          - lambda expression implementation for {@link Runnable}
 *                      </li>
 *                      <li>
 *                          {@link #assigningLambdaExpressionToAVariableForRunnable}
 *                          - assigning a lambda expression implementation to a variable for {@link Runnable}
 *                      </li>
 *                  </ul>
 *             </li>
 *             <li>
 *                 Methods for <b>{@link FilenameFilter}</b> examples (the <b>{@link java.io.FilenameFilter#accept(File, String)}</b> method takes arguments)
 *                  <ul>
 *                      <li>
 *                          {@link #anonymousInnerClassForFilenameFilter()}
 *                          - anonymous inner class implementation of {@link FilenameFilter}
 *                      </li>
 *                      <li>
 *                          {@link #lambdaExpressionForFilenameFilter()}
 *                          - lambda expression implementation for {@link FilenameFilter}
 *                      </li>
 *                      <li>
 *                          {@link #assigningLambdaExpressionToAVariableForFilenameFilter()}
 *                          - assigning a lambda expression implementation to a variable for {@link FilenameFilter}
 *                      </li>
 *                  </ul>
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * Lambda expressions never exist alone. There is always <i>context</i> for the expression, which indicates the functional
 * interface to which the expression is assgined.
 *
 * <br>
 * A lambda can be an argument to a method, a return type from a method, or assigned to a reference.
 * In each case, the type of the assignment must be a functional interface.
 */
public class Recipe_1_1_Lambda_Expression {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 1 - 1.1. Lambda Expression");


        System.out.println();
        // *** Anonymous Inner Class Implementation for Runnable ***
        anonymousInnerClassForRunnable();

        // *** Lambda Expression Implementation for Runnable ***
        lambdaExpressionForRunnable();

        // *** Assigning a Lambda Expression Implementation to a Variable for Runnable ***
        assigningLambdaExpressionToAVariableForRunnable();


        System.out.println();
        // *** Anonymous Inner Class Implementation for FilenameFilter ***
        anonymousInnerClassForFilenameFilter();

        // *** Lambda Expression Implementation for FilenameFilter ***
        lambdaExpressionForFilenameFilter();

        // *** Assigning a Lambda Expression Implementation to a Variable For FilenameFilter ***
        assigningLambdaExpressionToAVariableForFilenameFilter();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Anonymous Inner Class Implementation for Runnable</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     new Thread(new Runnable() {
        &#64;Override
        public void run() {
            System.out.println("Inside runnable using an anonymous class.");
        }
     }).start();
     * </pre>
     */
    protected static void anonymousInnerClassForRunnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("\n*** Anonymous Inner Class Implementation For Runnable ***");
                System.out.println("Inside runnable using an anonymous class.");
            }
        }).start();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Lambda Expression Implementation for Runnable</b> &#9674;&#9674;&#9674;
     *
     * <br>
     * - whatever value the expression evaluates to is returned automatically
     * <br>
     * - a lambda expression must match the argument types and return type in the signature of the SAM in the interface -&#62;
     * (being compatible with the method signature)
     *
     * <pre>
     new Thread(() -&#62; {
        System.out.println("Inside thread constructor using lambda.");
     }).start();
     * </pre>
     */
    protected static void lambdaExpressionForRunnable() {
        new Thread(() -> {
            System.out.println("\n*** Lambda Expression Implementation For Runnable ***");
            System.out.println("Inside thread constructor using lambda.");
        }).start();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Assigning a Lambda Expression Implementation to a Variable for Runnable</b> &#9674;&#9674;&#9674;
     *
     * <br>
     * - the lambda expression is the implementation of the method and can also be assigned to a reference of that interface type
     * <br>
     * - the lambda expression is the implementation of the SAM inside the functional interface
     * <br>
     * - the name of the method being implemented is not important. It does not appear anywhere as part of the lambda expression
     *
     * <pre>
     Runnable r = () -&#62; System.out.println("Lambda expression implementing the run method.");
     new Thread(r).start();
     * </pre>
     */
    protected static void assigningLambdaExpressionToAVariableForRunnable() {
        Runnable r = () -> {
            System.out.println("\n*** Assigning a Lambda Expression Implementation to a Variable for Runnable ***");
            System.out.println("Lambda expression implementing the run method.");
        };

        new Thread(r).start();
    }


    /**
     * &#9674;&#9674;&#9674; <b>Anonymous Inner Class Implementation for FilenameFilter</b> &#9674;&#9674;&#9674;
     *
     * <br>
     * - {@link java.io.FilenameFilter} instances are used as arguments to {@link File#list(FilenameFilter)} method to
     * restrict the returned files to only those that satisfy the method.
     *
     * <pre>
     File directory = new File("./src/chapter_1_The_Basics");

     String[] names = directory.list(new FilenameFilter() {
        &#64;Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".java");
        }
     });

     Arrays.asList(names).stream().forEach(System.out::println);
     * </pre>
     */
    protected static void anonymousInnerClassForFilenameFilter() {
        File directory = new File("./src/chapter_1_The_Basics");

        String[] names = directory.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });

        System.out.println("\n*** Anonymous Inner Class Implementation For FilenameFilter ***");
        Arrays.asList(names).stream().forEach(System.out::println);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Lambda Expression Implementation for Runnable</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     File directory = new File("./src/chapter_1_The_Basics");

     // with implicit data type
     String[] names = directory.list((dir, name) -&#62; name.endsWith(".java"));
     // with explicit data type
     // String[] names = directory.list((dir, name) -&#62; name.endsWith(".java"));

     Arrays.asList(names).stream().forEach(System.out::println);
     * </pre>
     */
    protected static void lambdaExpressionForFilenameFilter() {
        File directory = new File("./src/chapter_1_The_Basics");

        // with implicit data type
        String[] names = directory.list((dir, name) -> name.endsWith(".java"));
        // with explicit data type
        // String[] names = directory.list((dir, name) -> name.endsWith(".java"));

        System.out.println("\n*** Lambda Expression Implementation For FilenameFilter ***");
        Arrays.asList(names).stream().forEach(System.out::println);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Assigning a Lambda Expression Implementation to a Variable for FilenameFilter</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     FilenameFilter ff = (dir, name) -&#62; name.endsWith(".java");

     File directory = new File("./src/chapter_1_The_Basics");
     String[] names = directory.list(ff);

     Arrays.asList(names).stream().forEach(System.out::println);
     * </pre>
     */
    protected static void assigningLambdaExpressionToAVariableForFilenameFilter() {
        FilenameFilter ff = (dir, name) -> name.endsWith(".java");

        File directory = new File("./src/chapter_1_The_Basics");
        String[] names = directory.list(ff);

        System.out.println("\n*** Assigning a Lambda Expression Implementation to a Variable for FilenameFilter ***");
        Arrays.asList(names).stream().forEach(System.out::println);
    }
}
