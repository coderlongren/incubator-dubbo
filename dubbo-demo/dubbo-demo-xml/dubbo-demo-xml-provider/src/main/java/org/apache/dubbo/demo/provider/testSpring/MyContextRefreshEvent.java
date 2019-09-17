package org.apache.dubbo.demo.provider.testSpring;

import org.apache.dubbo.demo.provider.DemoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class MyContextRefreshEvent implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(MyContextRefreshEvent.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("received Spring refreshEvent", event);
    }
}
