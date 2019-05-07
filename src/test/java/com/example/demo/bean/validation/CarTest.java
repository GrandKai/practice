package com.example.demo.bean.validation;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

}
