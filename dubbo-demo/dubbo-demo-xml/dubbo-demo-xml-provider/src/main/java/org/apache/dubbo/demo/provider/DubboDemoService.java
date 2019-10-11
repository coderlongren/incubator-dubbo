package org.apache.dubbo.demo.provider;

import org.apache.dubbo.demo.DemoService;

public class DubboDemoService implements DemoService {
    @Override
    public String sayHello(String name) {
        System.out.println("这是通过SPI扩展加载的实现类");
        return "这是通过SPI扩展加载的实现类";
    }
}
