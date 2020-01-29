package chapter_3_Streams;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * <h1>(3.8.) Summary Statistics </h1>
 * <br>
 *
 * <ol>
 *     <li><h2>TO DO</h2>
 *         - you want the count, sum, min, max, and average of a stream of numerical values
 *     </li>
 *
 *     <li><h2>HOW</h2>
 *         - use the {@code summaryStatistics} method in {@link java.util.stream.IntStream},
 *         {@link java.util.stream.LongStream} and {@link java.util.stream.DoubleStream}
 *     </li>
 *
 *     <li><h2>EQ.</h2>
 *         <ul>
 *             <li>
 *                 {@link #useSummaryStatistics()}
 *                 - use summary statistics
 *             </li>
 *             <li>
 *                 {@link #useTheCollectMethod()}
 *                 - use the collect() method
 *             </li>
 *        </ul>
 *     </li>
 * </ol>
 *
 * Summary statistics is a special form of a reduction operation.
 *
 * @see chapter_4_Comparators_and_Collectors.Recipe_4_6_Downstream_Collectors
 * @see chapter_4_Comparators_and_Collectors.Recipe_4_9_Implementing_the_Collector_Interface
 */
public class Recipe_3_8_Summary_Statistics {

    /**
     * &#9674;&#9674;&#9674; <b>Running methods for this specific sub-chapter</b> &#9674;&#9674;&#9674;
     *
     * @param args not important
     */
    public static void main(String[] args) {
        System.out.println("Chapter 3 - 3.8. Summary Statistics");


        System.out.println();
        // *** Use Summary Statistics ***
        useSummaryStatistics();

        System.out.println();
        // *** Use the collect() Method ***
        new Recipe_3_8_Summary_Statistics().useTheCollectMethod();
    }

    /**
     * &#9674;&#9674;&#9674; <b>Use Summary Statistics</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     * {@code
     * DoubleSummaryStatistics stats = DoubleStream.generate(Math::random).limit(1_000_000).summaryStatistics();
     *
     * System.out.println(stats);
     * System.out.println("count:   " + stats.getCount());
     * System.out.println("min:     " + stats.getMin());
     * System.out.println("max:     " + stats.getMax());
     * System.out.println("sum:     " + stats.getSum());
     * System.out.println("average: " + stats.getAverage());
     * }
     * </pre>
     */
    protected static void useSummaryStatistics() {
        System.out.println("\n*** Use Summary Statistics ***");

        DoubleSummaryStatistics stats =
                DoubleStream.generate(Math::random).limit(1_000_000).summaryStatistics();

        System.out.println(stats);
        System.out.println("count:   " + stats.getCount());
        System.out.println("min:     " + stats.getMin());
        System.out.println("max:     " + stats.getMax());
        System.out.println("sum:     " + stats.getSum());
        System.out.println("average: " + stats.getAverage());
    }


    /**
     * &#9674;&#9674;&#9674; <b>Use the collect() Method</b> &#9674;&#9674;&#9674;
     *
     * <pre>
     * {@code
     * List<Team> teams = new ArrayList<>();
     * teams.add(new Team(1, "Team 1", 12345));
     * teams.add(new Team(2, "Team 2", 23456));
     * teams.add(new Team(3, "Team 3", 13579));
     * teams.add(new Team(4, "Team 4", 24680));
     * teams.add(new Team(5, "Team 5", 11111));
     *
     * // Using summary statistics
     * DoubleSummaryStatistics summaryStatistics =
     *      teams.stream().mapToDouble(Team::getSalary)
     *                    .collect(DoubleSummaryStatistics::new,
     *                             DoubleSummaryStatistics::accept,
     *                             DoubleSummaryStatistics::combine);
     *
     * // Downstream collectors
     * DoubleSummaryStatistics summarizingDouble = teams.stream().collect(Collectors.summarizingDouble(Team::getSalary));
     * }
     * </pre>
     *
     * @see chapter_4_Comparators_and_Collectors.Recipe_4_9_Implementing_the_Collector_Interface
     */
    protected void useTheCollectMethod() {
        System.out.println("\n*** Use the collect() Method ***");

        List<Team> teams = new ArrayList<>();
        teams.add(new Team(1, "Team 1", 12345));
        teams.add(new Team(2, "Team 2", 23456));
        teams.add(new Team(3, "Team 3", 13579));
        teams.add(new Team(4, "Team 4", 24680));
        teams.add(new Team(5, "Team 5", 11111));

        // Using summary statistics
        DoubleSummaryStatistics summaryStatistics =
                teams.stream().mapToDouble(Team::getSalary)
                              .collect(DoubleSummaryStatistics::new,
                                       DoubleSummaryStatistics::accept,
                                       DoubleSummaryStatistics::combine);
        System.out.println(summaryStatistics);

        // Downstream collectors
        DoubleSummaryStatistics summarizingDouble = teams.stream().collect(Collectors.summarizingDouble(Team::getSalary));
        System.out.println(summarizingDouble);
    }

    /**
     * Class used to get summary statistics based on salaries
     *
     */
    class Team {
        private final NumberFormat nf = NumberFormat.getCurrencyInstance();

        private int id;
        private String name;
        private double salary;

        public Team(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }
}
