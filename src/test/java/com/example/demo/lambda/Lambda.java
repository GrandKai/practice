package com.example.demo.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * @Author: zyn
 * @Description:
 * @Date: Created in 2019-05-06 15:15
 * @Modified By:
 */
public class Lambda {


  @Test
  public void testLimitAndSkip() {
    List<Person> persons = new ArrayList();
    for (int i = 1; i <= 10000; i++) {
      Person person = new Person(i, "name" + i);
      persons.add(person);
    }
    List<String> personList2 = persons.stream().
        map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
    System.out.println(personList2);
  }

  @Test
  public void testLimitAndSkipWithSorted() {
    List<Person> persons = new ArrayList();
    for (int i = 1; i <= 5; i++) {
      Person person = new Person(i, "name" + i, i);
      persons.add(person);
    }
    List<Person> personList2 = persons.stream().sorted(Comparator.comparing(Person::getName)).limit(2).collect(Collectors.toList());
    System.out.println(personList2);

  }

  @Test
  public void testLimitAndSkipEndWithSorted() {

    List<Person> persons = new ArrayList();
    for (int i = 1; i <= 5; i++) {
      Person person = new Person(i, "name" + i);
      persons.add(person);
    }
    List<Person> personList2 = persons.stream().limit(2).sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
    System.out.println(personList2);
  }



  /*@Test
  public void testCompareDatetime() {
    List<Person> persons = new ArrayList();
    for (int i = 1; i <= 5; i++) {
      Person person = new Person(i, "name" + i, new Date());
      persons.add(person);
    }

    persons.forEach(o -> System.out.println(o.getDate()));
    Person person = persons.stream().max(Comparator.comparing(Person::getDate)).get();
    System.out.println("max value: " + person.getDate());

  }*/


  /**
   *
   * 进阶：自己生成流, 1. Stream.generate, 2. Stream.iterate
   * 生成 10 个随机整数
   */
  @Test
  public void getnerateNumbers() {
    Random seed = new Random();
    Supplier<Integer> random = seed::nextInt;
    Stream.generate(random).limit(10).forEach(System.out::println);


    //Another way

    System.out.println("--------------");
    IntStream.generate(() -> (int) (System.nanoTime() % 100)).limit(10).forEach(System.out::println);
  }

  @Test
  public void personSupplier() {
    Stream.generate(new PersonSupplier()).limit(10).forEach(p -> System.out.println(p.getName() + " " + p.getAge()));
  }


  /**
   * 清单 24. 生成一个等差数列
   */
  @Test
  public void generateList() {
    Stream.iterate(0, i -> i + 3).limit(10).forEach(System.out::println);
  }


  @Test
  public void group() {

    Map<Integer, List<Person>> personGroups = Stream.generate(new PersonSupplier()).limit(100).collect(Collectors.groupingBy(Person::getAge));

    Iterator it = personGroups.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<Integer, List<Person>> persons = (Map.Entry) it.next();
      System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size() );
    }
  }

  @Test
  public void partition() {

    Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier()).limit(100).collect(Collectors.partitioningBy(p -> p.getAge() > 18));

    System.out.println("Children number: " + children.get(true).size());
    System.out.println("Adult number: " + children.get(false).size());
  }


  private class Person {

    public int no;
    private String name;
    private int age;

    public Person(int no, String name) {
      this.no = no;
      this.name = name;
    }
    public Person(int no, String name, int age) {
      /*try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }*/
      this.no = no;
      this.name = name;
      this.age = age;
    }

    public String getName() {
//      System.out.println(name);
      return name;
    }

    public int getAge() {
      return age;
    }
  }


  private class PersonSupplier implements Supplier<Person> {
    private int index = 0;
    private Random random = new Random();

    @Override
    public Person get() {
      return new Person(index++, "StormTestUser" + index, random.nextInt(100));
    }
  }
}
