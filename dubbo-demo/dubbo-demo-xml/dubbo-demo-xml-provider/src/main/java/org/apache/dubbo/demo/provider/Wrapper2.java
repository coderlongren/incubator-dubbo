package org.apache.dubbo.demo.provider;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wrapper2 implements DemoService {
    private static final Logger logger = LoggerFactory.getLogger(Wrapper2.class);

    private DemoService demoService;

    public Wrapper2(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name, URL url) {
        logger.info("我是 wrapper2 ");
        return demoService.sayHello(name, url);
    }
}
