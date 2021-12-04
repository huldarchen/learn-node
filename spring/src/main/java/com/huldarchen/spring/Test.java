package com.huldarchen.spring;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/3 18:35
 */
@RestController
public class Test {

  @PostMapping("Hello")
  private String helloWorld() {
    return "Hello World";
  }
}
