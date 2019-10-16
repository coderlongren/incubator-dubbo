package org.apache.dubbo.demo.consumer.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/fca")
    private Object hello() {
        return "Hello world";
    }
}
