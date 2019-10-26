package org.apache.dubbo.demo.provider;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeDemoServiceWrapper implements DemoService {
    private static final Logger logger = LoggerFactory.getLogger(TypeDemoServiceWrapper.class);

    private DemoService demoService;

    public TypeDemoServiceWrapper(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name, URL url) {
        logger.info("wrapper class 包裹之前的");
        return demoService.sayHello(name, url);
    }
}
