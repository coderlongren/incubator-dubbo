package org.apache.dubbo.demo.provider;

import org.springframework.stereotype.Component;

@Component
public class Dog {
    private String name;
    public void test() {
        System.out.println(name + "sss");
    }
}
