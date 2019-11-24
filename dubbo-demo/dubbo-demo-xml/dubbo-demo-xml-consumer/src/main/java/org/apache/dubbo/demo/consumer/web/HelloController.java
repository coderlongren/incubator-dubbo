package org.apache.dubbo.demo.consumer.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class HelloController {
    @RequestMapping("/fca")
    private Object hello() {
        return "Hello world";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int a = 3;
        long start = System.currentTimeMillis();
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                return getSomeThing(a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        });
        CompletableFuture task2 = CompletableFuture.runAsync(() -> {
            try {
                getSomeThing(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture allTask = CompletableFuture.allOf(task1, task2);
        allTask.get();
        System.out.println(System.currentTimeMillis() - start);

    }
    private static String getSomeThing(int a) throws InterruptedException {
        Thread.sleep(3000);
        return new Random().nextInt(100) + "";
    }
}
