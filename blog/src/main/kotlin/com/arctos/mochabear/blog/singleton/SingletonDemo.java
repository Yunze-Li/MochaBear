package com.arctos.mochabear.blog.singleton;

// 1.饿汉式 (直接创建)
class SingletonDemo {

    private static SingletonDemo instance = new SingletonDemo();

    private SingletonDemo() {
    }

    public static SingletonDemo getInstance() {
        return instance;
    }
}

// 2.懒汉式（懒加载，需要时创建）
class SingletonDemo2 {

    private static SingletonDemo2 instance;

    private SingletonDemo2() {
    }

    public static SingletonDemo2 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo2();
        }
        return instance;
    }
}

// 3. 线程安全的懒汉式
class SingletonDemo3 {

    private static SingletonDemo3 instance;

    private SingletonDemo3() {
    }

    public static synchronized SingletonDemo3 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo3();
        }
        return instance;
    }
}

// 4. 双重校验锁
class SingletonDemo4 {

    private volatile static SingletonDemo4 instance;

    private SingletonDemo4() {
    }

    public static SingletonDemo4 getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo4.class) {
                if (instance == null) {
                    instance = new SingletonDemo4();
                }
            }
        }
        return instance;
    }
}


// 5.静态内部类
class SingletonDemo5 {
    private SingletonDemo5() {
    }

    public static SingletonDemo5 getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static SingletonDemo5 instance = new SingletonDemo5();
    }
}


