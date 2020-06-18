package com.boot.demo.juc;

/**
 * @author: yhl
 * @DateTime: 2019/12/26 16:23
 * @Description:
 */
public class ThreadLocalDemo extends Thread {

    private ResultData data;

    public ThreadLocalDemo(ResultData data) {
        this.data = data;
    }

    public static void main(String[] args) throws InterruptedException {


        ResultData data = new ResultData();
        ThreadLocalDemo threadLocalDemo1 = new ThreadLocalDemo(data);
        ThreadLocalDemo threadLocalDemo2 = new ThreadLocalDemo(data);
        ThreadLocalDemo threadLocalDemo3 = new ThreadLocalDemo(data);
        threadLocalDemo1.start();
        threadLocalDemo2.start();
        threadLocalDemo3.start();
        Thread.sleep(300);

        System.out.println(ResultData.count);
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + "i---" + i + "--num:" + data.getNum());
        }
    }

    static class ResultData {
        // 生成序列号共享变量
        public static Integer count = 0;
        private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> count);

        public Integer getNum() {
            int count = threadLocal.get() + 1;
            threadLocal.set(count);
            return count;
        }
    }

}


