package chapter_3_Streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <h1>(3.3.) Reduction Operations Using Reduce</h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - produce a single value from stre4am operations
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #reductionOperationsOnIntStream()}
 *                 - reduction operations on IntStream
 *             </li>
 *             <li>
 *                 {@link #summingNumbersUsingReduce()}
 *                 - summing numbers using reduce()
 *             </li>
 *             <li>
 *                 {@link #performReduceWithABinaryOperator()}
 *                 - perform reduce with a {@link java.util.function.BinaryOperator}
 *             </li>
 *             <li>
 *                 {@link #usingACollector()}
 *                 - using a {@link java.util.stream.Collector}
 *             </li>
 *             <li>
 *                 {@link #accumulatingBooksIntoAMap()}
 *                 - accumulating books into a map
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * <br>
 * The primitive streams ({@link java.util.stream.IntStream}, {@link java.util.stream.LongStream} and
 * {@link java.util.stream.DoubleStream}) have several reduction operation build into the API:
 * <pre>
 * {@code
 * OptionalDouble       average
 * long                 count
 * Optionalint          max
 * Optionalint          min
 * int                  sum
 * IntSummaryStatistics summaryStatistics
 * R                    collect(Suppier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R,R> combiner)
 * int, OptionalInt     reduce
 * }
 * </pre>
 *
 * Basic reduce implementations:
 * <pre>
 * {@code
 * OptionalInt reduce (IntBinaryOperator op)
 * int         reduce (int identity, IntBinaryOperator op)
 * }
 * </pre>
 *
 * ❗ <b>{@link java.util.function.BiFunction}</b> takes two arguments and returns a single value, all of which can be of
 * different types.
 * <br>
 * If both input types and the return type are all the same, the function is a <b>{@link java.util.function.BinaryOperator}</b>.
 * <br>
 * An <b>{@link java.util.function.IntBinaryOperator}</b> is a {@link java.util.function.BinaryOperator} where both inputs and
 * the output are all ints.
 *
 * <br><br>
 * The reduce method is called {@code inject} in Groovy and {@code fold} in Scala.
 *
 * @see Recipe_3_7_Counting_Elements
 * @see Recipe_3_8_Summary_Statistics
 * @see chapter_4_Comparators_and_Collectors.Recipe_4_2_Converting_a_Stream_into_a_Collection
 * @see chapter_4_Comparators_and_Collectors.Recipe_4_3_Adding_a_Linear_Collection_to_a_Map
 */
public class Recipe_3_3_Reduction_Operations_Using_Reduce {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 3 - 3.3. Reduction Operations Using Reduce");


        System.out.println();
        // *** Reduction Operations on IntStream ***
        reductionOperationsOnIntStream();

        System.out.println();
        // *** Summing Numbers Using reduce() ***
        summingNumbersUsingReduce();

        System.out.println();
        // *** Perform reduce with a BinaryOperator ***
        performReduceWithABinaryOperator();

        System.out.println();
        // *** Using a Collector ***
        usingACollector();

        System.out.println();
        // *** Accumulating Books into a Map ***
        new Recipe_3_3_Reduction_Operations_Using_Reduce().accumulatingBooksIntoAMap();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     String[] strings = {"aaa", "bbb", "cccc"};

     // map() returns a Stream&#60;R&#62;
     long count = Arrays.stream(strings).map(String::length).count();

     // mapToInt() returns an IntStream&#60;R&#62;
     int sum = Arrays.stream(strings).mapToInt(String::length).sum();

     OptionalDouble average = Arrays.stream(strings).mapToInt(String::length).average();
     OptionalInt min = Arrays.stream(strings).mapToInt(String::length).min();
     OptionalInt max = Arrays.stream(strings).mapToInt(String::length).max();
     * </pre>
     *
     * {@code count} is a method on {@code Stream} while {@code sum} and {@code average} are only on primitive streams.
     *
     * <br>
     * {@code min} and {@code max} have an overloading without a {@code Comparator} only on primitive streams.
     *
     * <br>
     * {@code average}, {@code min} and {@code max} return {@code Optionals} as the streams can be empty when you call these
     * methods (you might've applied a filter to the Stream previously).
     *
     * @see Recipe_3_7_Counting_Elements
     * @see Recipe_3_8_Summary_Statistics
     */
    protected static void reductionOperationsOnIntStream() {
        System.out.println("\n*** Reduction Operations on IntStream ***");
        String[] strings = {"aaa", "bbb", "cccc"};

        // map() returns a Stream<R>
        long count = Arrays.stream(strings).map(String::length).count();

        // mapToInt() returns an IntStream<R>
        int sum = Arrays.stream(strings).mapToInt(String::length).sum();

        OptionalDouble average = Arrays.stream(strings).mapToInt(String::length).average();
        OptionalInt min = Arrays.stream(strings).mapToInt(String::length).min();
        OptionalInt max = Arrays.stream(strings).mapToInt(String::length).max();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Summing Numbers Using reduce()</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     int sum = IntStream.rangeClosed(1, 10).reduce((x, y) -&#62; x + y).orElse(0);

     sum = IntStream.rangeClosed(1, 10)
                    .reduce((x, y) -&#62; {
                        // x is thw accumulator while y is each value from the stream, added to thw accumulator
                        System.out.format("%2s - %2s\n", x, y);
                        return x + y;
                    }).orElse(0); // Because of the orElse method the value is an int instead of an Optional

     int sumWithInitialAccumulatorGiven = IntStream.rangeClosed(1, 10)
                    // x accumulator, y every value from the stream; starting from 0 (the identity)
                    // returns an int instead of an Optional because of the initial value set
                    .reduce(0, (x, y) -&#62; x + y);
     * </pre>
     */
    protected static void summingNumbersUsingReduce() {
        System.out.println("\n*** Summing Numbers Using reduce() ***");

        int sum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> x + y).orElse(0);

        sum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> {
                    // x is thw accumulator while y is each value from the stream, added to thw accumulator
                    System.out.format("%2s - %2s\n", x, y);
                    return x + y;
                }).orElse(0); // Because of the orElse method the value is an int instead of an Optional
        System.out.println(sum);

        int sumWithInitialAccumulatorGiven = IntStream.rangeClosed(1, 10)
                // x accumulator, y every value from the stream; starting from 0 (the identity)
                // returns an int instead of an Optional because of the initial value set
                .reduce(0, (x, y) -> x + 2 * y);
        System.out.println(sumWithInitialAccumulatorGiven);

    }

    /**
     * &#9674;&#9674;&#9674; <b>Perform reduce with a BinaryOperator</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     Integer sum = Stream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum);

     Integer minValue = Stream.of(5, 4, 9, 2, 1).reduce(Integer.MIN_VALUE, Integer::max);

     String concatenation = Stream.of("str ", "= ", "alt ", "string")
         // the first parameter becomes the target of the concat method and the second one is the argument to concat
         // the target, the parameter and the result are of the same type and this can be considered a binary
         // operator for the reduce method
         .reduce("", String::concat);
     * </pre>
     */
    protected static void performReduceWithABinaryOperator() {
        Integer sum = Stream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum);

        Integer minValue = Stream.of(5, 4, 9, 2, 1).reduce(Integer.MIN_VALUE, Integer::max);

        String concatenation = Stream.of("str ", "= ", "alt ", "string")
                // the first parameter becomes the target of the concat method and the second one is the argument to concat
                // the target, the parameter and the result are of the same type and this can be considered a binary
                // operator for the reduce method
                .reduce("", String::concat);
        System.out.println(concatenation);
    }

    /**
     * &#9674;&#9674;&#9674; <b>Using a Collector</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     String string = Stream.of("this", "is", "a", "list")
             .collect(() -&#62; new StringBuilder(),    // result Supplier
                     (sb, str) -&#62; sb.append(str),   // add a single value to the result
                     (sb1, sb2) -&#62; sb1.append(sb2)) // combine two results
             .toString();

     // Using method reference
     string = Stream.of("this", "is", "a", "list")
             .collect(StringBuilder::new,    // result Supplier
                      StringBuilder::append,   // add a single value to the result
                      StringBuilder::append) // combine two results
             .toString();

     // Using joining method
     string = Stream.of("this", "is", "a", "list").collect(Collectors.joining());
     * </pre>
     *
     * @see chapter_4_Comparators_and_Collectors.Recipe_4_2_Converting_a_Stream_into_a_Collection
     */
    protected static void usingACollector() {
        String string = Stream.of("this", "is", "a", "list")
                .collect(() -> new StringBuilder(),    // result Supplier
                        (sb, str) -> sb.append(str),   // add a single value to the result
                        (sb1, sb2) -> sb1.append(sb2)) // combine two results
                .toString();

        // Using method reference
        string = Stream.of("this", "is", "a", "list")
                .collect(StringBuilder::new,    // result Supplier
                        StringBuilder::append,   // add a single value to the result
                        StringBuilder::append) // combine two results
                .toString();

        // Using joining method
        string = Stream.of("this", "is", "a", "list").collect(Collectors.joining());
    }

    /**
     * &#9674;&#9674;&#9674; <b>Accumulating Books into a Map</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     * {@code
     * List<Book> books = new ArrayList<>();
     * books.add(new Book(1, "Title 1"));
     * books.add(new Book(2, "Title 2"));
     * books.add(new Book(3, "Title 3"));
     *
     * HashMap<Integer, Book> mapOfBooks = books.stream()
     *      .reduce(new HashMap<Integer, Book>(),
     *              (map, book) -> {
     *                  map.put(book.getId(), book);
     *                  return map;
     *              },
     *              (map1, map2) -> {
     *                  map1.putAll(map2);
     *                  return map1;
     *              });
     * }
     * </pre>
     */
    protected void accumulatingBooksIntoAMap() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Title 1"));
        books.add(new Book(2, "Title 2"));
        books.add(new Book(3, "Title 3"));

        HashMap<Integer, Book> mapOfBooks = books.stream()
                .reduce(new HashMap<Integer, Book>(),
                        (map, book) -> {
                            map.put(book.getId(), book);
                            return map;
                        },
                        (map1, map2) -> {
                            map1.putAll(map2);
                            return map1;
                        });
        mapOfBooks.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    /**
     * Class used to show how to map the objects of type Book to objects with key = id of a book and value = the book itself
     *
     */
    protected class Book {
        private Integer id;
        private String title;

        public Book(Integer id, String title) {
            this.id = id;
            this.title = title;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Book)) return false;
            Book book = (Book) o;
            return Objects.equals(id, book.id) &&
                    Objects.equals(title, book.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title);
        }
    }
}
