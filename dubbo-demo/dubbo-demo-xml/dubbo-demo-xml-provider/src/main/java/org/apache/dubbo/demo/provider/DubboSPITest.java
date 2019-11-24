package org.apache.dubbo.demo.provider;

import com.qunar.flight.utils.DateUtils;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.demo.DemoService;

public class DubboSPITest {
    public static void main(String[] args) {
        ExtensionLoader<DemoService> extensionLoader =
                ExtensionLoader.getExtensionLoader(DemoService.class);
        DemoService demoService = extensionLoader.getAdaptiveExtension();
        URL url = URL.valueOf("sdsds");
//        DemoService demoService = extensionLoader.getExtension("dubboDemoService");
        demoService.sayHello("", url);
//        defaultService.sayHello("sss");
//        DateUtils dateUtils = new DateUtils();
//        System.out.println(dateUtils.nowTime());

    }
}
