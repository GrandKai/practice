package com.example.demo.bean.validation;

import java.util.stream.IntStream;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @Author: zyn
 * @Description:
 * @Date: Created in 2019-05-06 11:11
 * @Modified By:
 */

@Data
public class Car {

  @NotNull(message = "制造商不能为空")
  private String manufacturer;

  @NotNull
  @Size(min = 2, max = 14, message = "最大2，最大10")
  private String licensePlate;

  @Min(value = 2, message = "座位数量最小是2")
  private int seatCount;

  public Car(String manufacturer, String licencePlate, int seatCount) {
    this.manufacturer = manufacturer;
    this.licensePlate = licencePlate;
    this.seatCount = seatCount;
  }
}
