package org.apache.dubbo.demo;

import java.util.concurrent.CompletableFuture;

public interface AsyncDemoService {
    CompletableFuture<String> sayHello(String name);
}
