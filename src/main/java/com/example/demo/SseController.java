package com.example.demo;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

/**
 * @Author: zyn
 * @Description:
 * @Date: Created in 2019-05-05 16:16
 * @Modified By:
 */
@RestController
@RequestMapping("sse")
public class SseController {


  @GetMapping
  public Flux<ServerSentEvent<Integer>> random() {
    return Flux.interval(Duration.ofSeconds(2))
        .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
        .map(data -> ServerSentEvent.<Integer>builder()
            .event("random")
            .id(Long.toString(data.getT1()))
            .data(data.getT2())
            .build());
  }

}
