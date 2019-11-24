package org.apache.dubbo.demo.provider;

import org.apache.dubbo.demo.AsyncDemoService;

import java.util.concurrent.CompletableFuture;

public class AsyncService implements AsyncDemoService {
    @Override
    public CompletableFuture<String> sayHello(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello" + name);
            return "hello" + name;
        });
    }
}
