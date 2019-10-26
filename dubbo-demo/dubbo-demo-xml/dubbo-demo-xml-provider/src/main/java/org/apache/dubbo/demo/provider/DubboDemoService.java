package org.apache.dubbo.demo.provider;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DubboDemoService implements DemoService {
    private static final Logger logger = LoggerFactory.getLogger(DubboDemoService.class);

    @Override
    public String sayHello(String name, URL url) {
        logger.info("这是通过SPI扩展加载的实现类");
        return "这是通过SPI扩展加载的实现类";
    }
}
