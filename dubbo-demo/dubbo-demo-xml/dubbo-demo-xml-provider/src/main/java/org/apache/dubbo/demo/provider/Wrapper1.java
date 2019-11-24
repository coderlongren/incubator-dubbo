package org.apache.dubbo.demo.provider;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;
import org.apache.dubbo.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wrapper1 implements DemoService {
    private static final Logger logger = LoggerFactory.getLogger(Wrapper1.class);

    private DemoService demoService;

    public Wrapper1(DemoService demoService) {
        this.demoService = demoService;
        System.out.println("包装类执行 构造函数");
    }

    @Override
    public String sayHello(String name, URL url) {
        logger.info("wrapper1包裹之前的");
        return demoService.sayHello(name, url);
    }
}
