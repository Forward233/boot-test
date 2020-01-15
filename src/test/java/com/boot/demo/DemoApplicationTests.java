package com.boot.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private TaskExecutor taskExecutor;

    @Test
    void contextLoads() {
    }

}
