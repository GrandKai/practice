package com.example.demo.lambda;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * IntStream, LongStream, DoubleStream
 * Stream 方法：
 *    Intermediate：
 *      map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
 *
 *    Terminal：
 *      forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
 *
 *    Short-circuiting：
 *      anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
 *
 * @Author: zyn
 * @Description:
 * @Date: Created in 2019-05-07 08:08
 * @Modified By:
 */
public class StreamTest {

  public static void main(String[] args) {

    IntStream.range(1, 3).forEach(System.out::println);
    IntStream.rangeClosed(1, 3).forEach(System.out::println);

    Stream.of("one", "two", "three", "four")
        .filter(e -> e.length() > 3)
        .peek(e -> System.out.println("Filtered value: " + e))
        .map(String::toUpperCase)
        .peek(e -> System.out.println("Mapped value: " + e))
        .collect(Collectors.toList());

    // 字符串连接，concat = "ABCD"
    String concat = Stream.of("A", "B", "C", "D").reduce("11", String::concat);

    System.out.println(concat);

    // 求最小值，minValue = -3.0
    double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MIN_VALUE, Double::min);
    System.out.println(minValue);

    // 求和，sumValue = 10, 有起始值
    int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
    System.out.println(sumValue);

    sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
    System.out.println(sumValue);

    // 过滤，字符串连接，concat = "ace"
    concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
    System.out.println(concat);
  }
}
