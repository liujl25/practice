package com.liujunlong.practice.basis.thread;

import java.util.concurrent.*;

/**
 * <p>CompletableFuture
 *
 * @author liujunlong
 * @date 2023/3/23 00:37
 */
public class CompletableFutureTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        testSupplyAsync();

    }

    public static void testSupplyAsync() {
        // supplyAsync 获取有结果的
        CompletableFuture<String> res = CompletableFuture.supplyAsync(() -> {
            return "result from supplyAsync";
        }, ThreadPoolExecutorDemo.executor);
        System.out.println(res.join());

        //CompletableFuture.runAsync 不能返回结果
        CompletableFuture<Void> res1 = CompletableFuture.runAsync(() -> {
            System.out.println("CompletableFuture.runAsync, no return");
        }, ThreadPoolExecutorDemo.executor);
    }

    public static void test() throws ExecutionException, InterruptedException, TimeoutException {
        //        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("1123");
//        System.out.println(completableFuture.join());
//        String s = completableFuture.get();
//        System.out.println(s);
//        AsyncResult<String> asyncResult = new AsyncResult<>("");
//        asyncResult.get();

        CompletableFuture<String> constant = CompletableFuture.completedFuture("I am constant futute");
        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> {
            System.out.println("I am c1");
        });
        CompletableFuture<Void> c2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am c2");
        });
        CompletableFuture<Void> c3 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am c3");
        });

        CompletableFuture.allOf(c3, c1, c2).get(2, TimeUnit.SECONDS);
        CompletableFuture.allOf(c3, c1, c2).join();
//        c1.join();
//        c2.join();
//        c3.join();

        System.out.println("done");
        System.out.println(constant.join());

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
