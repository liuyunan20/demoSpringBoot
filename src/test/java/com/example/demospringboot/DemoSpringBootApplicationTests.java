package com.example.demospringboot;

import com.example.demospringboot.jason.Greeting;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoSpringBootApplicationTests {
    @Autowired
    private ApplicationContext context;
    @Test
    void contextLoads() {
        assertNotNull(context);
        int count = context.getBeanDefinitionCount();
        System.out.println("There are " + count + " beans in the application context");
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Test @Disabled
    void noGreetingInAppCtx() {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> context.getBean(Greeting.class));
    }
    @Test
    void  getBeanTwice() {
        Greeting greeting1 = context.getBean(Greeting.class);
        Greeting greeting2 = context.getBean(Greeting.class);

        greeting1.setMessage("What up?");
        System.out.println(greeting2.getMessage());
        assertSame(greeting1, greeting2);
    }
}
