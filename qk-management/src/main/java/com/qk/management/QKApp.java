package com.qk.management;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author MrZhao
 *  轻客管家的启动类或者叫引导类
 */
@Slf4j
@SpringBootApplication
public class QKApp {

    public static void main(String[] args) {
        SpringApplication.run(QKApp.class,args);
        log.info("----------------轻客管家系统启动成功---------------");
    }
}
