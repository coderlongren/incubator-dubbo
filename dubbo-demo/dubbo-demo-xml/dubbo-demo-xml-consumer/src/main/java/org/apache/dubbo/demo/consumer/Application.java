/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.demo.consumer;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.demo.AsyncDemoService;
import org.apache.dubbo.demo.DemoService;

import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class Application {
    private static final CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
        context.start();

//        DemoService demoService = context.getBean("demoService", DemoService.class);
        AsyncDemoService asyncService = (AsyncDemoService) context.getBean("asyncService", AsyncDemoService.class);
        Map<String, String> attachments = new HashMap<>();
        attachments.put("tarceId", "12345");
        RpcContext.getContext().setAttachments(attachments);
//        demoService.sayHello("sailong", URL.valueOf("sds:ss"));
        long start = System.currentTimeMillis();
        CompletableFuture<String> completableFuture = asyncService.sayHello("赛龙");
        completableFuture.whenComplete((res, err) -> {
            System.out.println(res);
            c.countDown();
        });

        CompletableFuture<String> completableFuture2 = asyncService.sayHello("赛龙");
        completableFuture2.whenComplete((res, err) -> {
            System.out.println(res);
            c.countDown();
        });
        c.await();
        System.out.println("两次异步调用 花费时间 : " + (System.currentTimeMillis() - start)/1000 + "s");
//         获取缓存中的实例
//        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
//        GenericService genericService = cache.get()
//
//        String hello = demoService.sayHello("world");
//        System.out.println("result: " + hello);
        System.in.read();
    }

}
