package com.example.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.PushBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: zyn
 * @Description:
 * @Date: Created in 2019-05-05 13:13
 * @Modified By:
 */
@Slf4j
@RestController
public class HelloController {

  private final TaskExecutor taskExecutor;

  public HelloController(TaskExecutor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }

  @GetMapping("hello")
  public Callable<String> hello(HttpServletRequest request) {

    PushBuilder builder = request.newPushBuilder();
    builder.path("images/k8s.png").push();

    HttpServletMapping httpServletMapping = request.getHttpServletMapping();




    log.info("当前线程1：{}", Thread.currentThread().getName());

    return () -> {

      log.info("当前线程2：{}", Thread.currentThread().getName());
      Thread.sleep(ThreadLocalRandom.current().nextLong(5000));
      log.info("当前线程3：{}", Thread.currentThread().getName());
      return "Hello World, from Spring Boot 2!";
    };
  }

  @GetMapping("hello1")
  public CompletableFuture<String> hello1() {

    log.info("当前线程1：{}", Thread.currentThread().getName());

    return CompletableFuture.supplyAsync(() -> {
      randomDelay();
      log.info("当前线程2：{}", Thread.currentThread().getName());
      return "Hello World, from Spring Boot 2!";
    }, taskExecutor);
  }

  private void randomDelay() {
    try {
      Thread.sleep(ThreadLocalRandom.current().nextLong(5000));

    } catch (InterruptedException e) {
      log.error("", e);
    }
  }

  @GetMapping("hello2")
  public Mono<String> hello2() {

    return Mono.just("hello world");
  }

}
