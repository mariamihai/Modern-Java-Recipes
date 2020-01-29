/**
 * The second chapter of the book <b>[COMPLETED]</b>
 *
 * <br><br>
 * Lambda expressions and method references are always assigned to functional interfaces, which provide information about
 * the SAM being implemented
 *
 * <br>
 * The <b>{@code java.util.function}</b> package contains only functional interfaces that are reused in the rest of the library
 *
 * <ul>
 *     <li>
 *         <br>
 *         {@link chapter_2_The_java_util_function_Package.Recipe_2_1_Consumers}
 *         - take a generic argument and return nothing
 *     </li>
 *     <li>
 *         <br>
 *         {@link chapter_2_The_java_util_function_Package.Recipe_2_2_Suppliers}
 *         - don't take any argument and return a value
 *     </li>
 *     <li>
 *         <br>
 *         {@link chapter_2_The_java_util_function_Package.Recipe_2_3_Predicates}
 *         - take an argument and return a value
 *     </li>
 *     <li>
 *         <br>
 *         {@link chapter_2_The_java_util_function_Package.Recipe_2_4_Functions}
 *         - take an argument and return  a value
 *     </li>
 * </ul>
 *
 * <br><br>
 * For each of the basic interfaces, there are different related ones
 * <br>
 * For eq, for Consumer we have variations for primitive types (IntConsumer, LongConsumer, etc) and a variation that
 * takes two arguments and returns void (BiConsumer)
 */
package chapter_2_The_java_util_function_Package;