package org.apache.dubbo.demo.provider;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.demo.DemoService;

public class DubboSPITest {
    public static void main(String[] args) {
        ExtensionLoader<DemoService> extensionLoader =
                ExtensionLoader.getExtensionLoader(DemoService.class);
        DemoService demoService = extensionLoader.getExtension("dubboDemoService");
        demoService.sayHello("");
    }
}
