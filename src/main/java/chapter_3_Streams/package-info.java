/**
 * The third chapter of the book
 *
 * <br><br>
 * A stream is a sequence of elements that does not save the elements or modify the original source
 *
 * <br>
 * <b>Functional programming in Java</b>: generate a stream from some source of data, pass the elements through a series
 * of intermediate operations (<b><i>pipeline</i></b>), and complete the process with a <b><i>terminal expression</i></b>
 *
 * <br>
 * Streams can only be used once - after a stream has passed through zero or more intermediate operations and reached a
 * terminal operation, it is finished and you need to make a new stream to process the values again
 *
 * <br>
 * Streams are also lazy - a stream will only process as much data as it is necessary to reach the termianl condition
 *
 * <ul>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_1_Creating_Streams}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_2_Boxed_Streams}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_3_Reduction_Operations_Using_Reduce}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_4_Check_Sorting_using_Reduce}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_5_Debugging_Streams_with_peek}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_6_Converting_Strings_to_Streams_and_Back}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_7_Counting_Elements}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_8_Summary_Statistics}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_9_Finding_the_First_Element_in_a_Stream}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_10_Using_anyMatch_allMatch_and_noneMatch}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_11_Stream_flatMap_Versus_map}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_12_Concatenating_Streams}</li>
 *     <li><br>{@link chapter_3_Streams.Recipe_3_13_Lazy_Streams}</li>
 * </ul>
 */
package chapter_3_Streams;