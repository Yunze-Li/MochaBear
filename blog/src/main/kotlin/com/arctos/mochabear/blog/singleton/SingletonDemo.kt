package com.arctos.mochabear.blog.singleton

// 1.饿汉式
object SingletonDemo


// 2. 懒汉式
class SingletonDemo2 private constructor() {

    companion object {
        private var instance: SingletonDemo2? = null
            get() {
                if (field == null) {
                    field = SingletonDemo2()
                }
                return field
            }

        fun get(): SingletonDemo2 {
            return instance!!
        }
    }
}

// 3. 线程安全的懒汉式
class SingletonDemo3 private constructor() {

    companion object {
        private var instance: SingletonDemo3? = null
            get() {
                if (field == null) {
                    field = SingletonDemo3()
                }
                return field
            }

        @Synchronized
        fun get(): SingletonDemo3 {
            return instance!!
        }
    }
}

// 4. 双重校验锁
class SingletonDemo4 private constructor() {
    companion object {
        val instance: SingletonDemo4 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { SingletonDemo4() }
    }
}

// 5. 静态内部类
class SingletonDemo5 private constructor() {
    companion object {
        fun get(): SingletonDemo5 {
            return SingletonHolder.instance
        }
    }

    private object SingletonHolder {
        val instance = SingletonDemo5()
    }
}
