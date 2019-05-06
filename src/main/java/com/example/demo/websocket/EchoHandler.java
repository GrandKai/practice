package com.example.demo.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/**
 * @Author: zyn
 * @Description:
 * @Date: Created in 2019-05-05 17:17
 * @Modified By:
 */
@Component
public class EchoHandler implements WebSocketHandler {

  @Override
  public Mono<Void> handle(WebSocketSession webSocketSession) {
    return webSocketSession.send(webSocketSession.receive().map((msg) -> webSocketSession.textMessage("echo ->" + msg.getPayloadAsText())));
  }
}
