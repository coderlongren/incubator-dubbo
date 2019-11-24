package org.apache.dubbo.demo.provider;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.demo.DemoService;

@Adaptive
public class AdaptiveDemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name, URL url) {
        System.out.println("Adaptive Class");
        return "Adaptive";
    }
}
