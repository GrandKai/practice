package com.example.demo.bean.validation;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @Author: zyn
 * @Description:
 * @Date: Created in 2019-05-06 12:12
 * @Modified By:
 */
public class CarTest {

  private static Validator validator;


  @BeforeClass
  public static void setUpValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }


  @Test
  public void manufaturerIsNull() {
    Car car = new Car(null, "DD-AB-123", 4);
    Set<ConstraintViolation<Car>> constraintViolation = validator.validate(car);

    assertEquals(1, constraintViolation.size());
    assertEquals("制造商不能为空", constraintViolation.iterator().next().getMessage());

    List<String> result = constraintViolation.parallelStream().map(o -> {
      return o.getPropertyPath() + " " + o.getMessage() + " " + o.getInvalidValue();
    }).collect(Collectors.toList());

    result.forEach(System.out::println);
  }


  public static void main(String[] args) {
//    IntStream.range(1, 3).forEach(System.out::println);
//    IntStream.rangeClosed(1, 3).forEach(System.out::println);

    /*Stream.of("one", "two", "three", "four")
        .filter(e -> e.length() > 3)
        .peek(e -> System.out.println("Filtered value: " + e))
        .map(String::toUpperCase)
        .peek(e -> System.out.println("Mapped value: " + e))
        .collect(Collectors.toList());*/

    /*String strA = " abcd ", strB = null;
    print(strA);
    print("");
    print(strB);
    getLength(strA);
    getLength("");
    getLength(strB);*/

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

  public static void print(String text) {
    // Java 8
    Optional.ofNullable(text).ifPresent(System.out::println);
    // Pre-Java 8
    /*if (text != null) {
      System.out.println(text);
    }*/
  }

  public static int getLength(String text) {

    // Java 8
    return Optional.ofNullable(text).map(String::length).orElse(-1);
    // Pre-Java 8
// return if (text != null) ? text.length() : -1;
  }

  ;
}
