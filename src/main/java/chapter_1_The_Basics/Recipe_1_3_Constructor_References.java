package chapter_1_The_Basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>(1.3.) Constructor references</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - instantiate an object using a method reference as part of a stream pipeline
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use {@code new} keyword as part of a method reference
 *     </li>
 *
 *     <li><h2>EQ. that use {@link Person}</h2>
 *         <ul>
 *             <li>
 *                 {@link #convertAListOfPeopleToAListOfNames()}
 *                 - convert a list of people to a list of names
 *             </li>
 *             <li>
 *                 {@link #constructAListOfPeopleFromAListOfNames()}
 *                 - construct a list of people from a list of names
 *             </li>
 *             <li>
 *                 {@link #convertingAListToAStreamAndBack()}
 *                 - converting a list to a stream and back
 *             </li>
 *             <li>
 *                 {@link #usingVarargsConstructor()}
 *                 - using varargs constructor
 *             </li>
 *             <li>
 *                 {@link #usingArrays()}
 *                 - using arrays
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 */
public class Recipe_1_3_Constructor_References {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        Recipe_1_3_Constructor_References cr = new Recipe_1_3_Constructor_References();

        System.out.println("Chapter 1 - 1.3. Constructor References");


        System.out.println();
        // *** Convert a List of People to a List of Names ***
        cr.convertAListOfPeopleToAListOfNames();
        // *** Construct a List of People from a List of Names ***
        cr.constructAListOfPeopleFromAListOfNames();
        // *** Converting a List to a Stream and back ***
        cr.convertingAListToAStreamAndBack();
        // *** Using Varargs Constructor ***
        cr.usingVarargsConstructor();

        System.out.println();
        // *** Using Arrays ***
        cr.usingArrays();

    }

    /**
     * &#9674;&#9674;&#9674; <b>Convert a List of People to a List of Names</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     // lambda expression
     List&#60;String&#62; names = people.stream().map(person -&#62; person.getName()).collect(Collectors.toList());
     // method reference
     names = people.stream().map(Person::getName).collect(Collectors.toList());
     * </pre>
     *
     * The syntax {@code Person::new} refers to the constructor in the {@code Person} class.
     *
     * <br>
     * The context determines which constructor is executed. Because the context supplies a string, the one-arg
     * {@code Person} constructor is used.
     */
    protected void convertAListOfPeopleToAListOfNames() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Name1"));
        people.add(new Person("Name2"));

        // lambda expression
        List<String> names = people.stream().map(person -> person.getName()).collect(Collectors.toList());
        // method reference
        names = people.stream().map(Person::getName).collect(Collectors.toList());
    }

    /**
     * &#9674;&#9674;&#9674; <b>Construct a List of People from a List of Names</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     // lambda expression
     List&#60;Person&#62; people = names.stream().map(name -&#62; new Person(name)).collect(Collectors.toList());
     // constructor reference
     people = names.stream().map(Person::new).collect(Collectors.toList());
     * </pre>
     */
    protected void constructAListOfPeopleFromAListOfNames() {
        List<String> names = Arrays.asList("Name 1", "Name 2");

        // lambda expression
        List<Person> people = names.stream().map(name -> new Person(name)).collect(Collectors.toList());
        // constructor reference
        people = names.stream().map(Person::new).collect(Collectors.toList());
    }

    /**
     * &#9674;&#9674;&#9674; <b>Converting a List to a Stream and back</b> &#9674;&#9674;&#9674;
     *
     * <br>
     * <i>Copy constructor</i> - takes a {@code Person} argument and returns a new {@code Person} with the same attribute(s)
     *
     * <br>
     * This is useful if you want to isolate streaming code from the original instances. For eq., if you already have a list
     * of people, convert the list into a stream, and then back into a list, the references are the same.
     *
     * <pre>
     // without the copy constructor you have the same object
     Person before = new Person("Name 1");
     List&#60;Person&#62; people = Stream.of(before).collect(Collectors.toList());
     Person after = people.get(0);

     // same object
     assertTrue(before == after);
     // change name using the before reference
     before.setName("Name 2");
     // name has been changed in the after reference
     //assertEquals("Name 2", after.getName());


     // using a copy constructor, you can break that connection
     people = Stream.of(before.getName()).map(Person::new).collect(Collectors.toList());
     after = people.get(0);

     // different objects
     assertFalse(before == after);
     // but equivalent
     assertEquals(before, after);
     * </pre>
     */
    protected void convertingAListToAStreamAndBack() {
        // without the copy constructor you have the same object
        Person before = new Person("Name 1");
        List<Person> people = Stream.of(before).collect(Collectors.toList());
        Person after = people.get(0);

        // same object
        //assertTrue(before == after);
        // change name using the before reference
        before.setName("Name 2");
        // name has been changed in the after reference
        //assertEquals("Name 2", after.getName());


        // using a copy constructor, you can break that connection
        people = Stream.of(before).map(Person::new).collect(Collectors.toList());
        after = people.get(0);

        // different objects
        //assertFalse(before == after);
        // but equivalents
        //assertEquals(before, after);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Using Varargs Constructor</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; names = Arrays.asList("aa aa aaa", "bb bb bbb", "cc cc ccc");
     List&#60;Person&#62; people =
     names.stream().map(name -&#62; name.split(" "))
                   .map(Person::new)
                   .collect(Collectors.toList());
     * </pre>
     */
    protected void usingVarargsConstructor() {
        System.out.println("*** Using Varargs Constructor ***");
        List<String> names = Arrays.asList("aa aa aaa", "bb bb bbb", "cc cc ccc");
        List<Person> people =
                names.stream().map(name -> name.split(" "))
                              .map(Person::new)
                              .collect(Collectors.toList());
        System.out.println(people);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Using Arrays</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     List&#60;String&#62; names = Arrays.asList("aa aa aaa", "bb bb bbb", "cc cc ccc");
     Person[] people = names.stream()
            .map(Person::new) // constructor reference for Person
            .toArray(Person[]::new); // constructor reference for an array of Person
     * </pre>
     *
     * The method used is {@code <A> A[] toArray(InitFunction<A[]> generator)}
     *
     */
    protected void usingArrays() {
        System.out.println("*** Using Arrays ***");
        List<String> names = Arrays.asList("aa aa aaa", "bb bb bbb", "cc cc ccc");
        Person[] people = names.stream()
                .map(Person::new) // constructor reference for Person
                .toArray(Person[]::new); // constructor reference for an array of Person

        Stream.of(people).forEach(System.out::println);

    }

    /**
     * Class used in constructor references examples.
     *
     */
    protected class Person {
        protected String name;

        /**
         * Default Constructor. Empty.
         */
        public Person() {
        }

        /**
         * Constructor used for copy constructor example.
         *
         * <pre>
         this.name = p.getName();
         * </pre>
         *
         * @param p person instance
         */
        public Person(Person p) {
            this.name = p.getName();
        }

        /**
         * Constructor used for varargs constructor example.
         *
         * <pre>
         this.name = Arrays.stream(names).collect(Collectors.joining(" "));
         * </pre>
         *
         * @param names zero or more Strings which will be concatenated into one name
         */
        public Person(String... names) {
            this.name = Arrays.stream(names).collect(Collectors.joining(" "));
        }

        /**
         * Constructor with single name
         *
         * <pre>
         this.name = name;
         * </pre>
         *
         * @param name name of the new Person
         */
        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
