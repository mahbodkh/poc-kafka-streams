package com.engelglobal.status.info;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class StatusServiceApplication
{

  public static void main(String[] args) {
    SpringApplication.run(StatusServiceApplication.class, args);
  }
}
