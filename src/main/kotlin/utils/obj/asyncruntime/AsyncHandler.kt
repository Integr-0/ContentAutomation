package utils.obj.asyncruntime

import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

@Suppress("Unused")
class AsyncLoopHandler {
    companion object {
        private var threadPool: MutableMap<KCallable<*>, Thread> = mutableMapOf()

        fun <T : Any> subscribe(clazz: KClass<T>) {
            for (m in clazz.members) {
                if (m.hasAnnotation<AsyncTick>()) {
                    threadPool += Pair(m, Thread {
                        while (true) m.call(clazz.createInstance())
                    })
                    threadPool[m]!!.start()
                }
            }
        }

        fun <T : Any> unsubscribe(clazz: KClass<T>) {
            for (m in clazz.members) {
                if (m.hasAnnotation<AsyncTick>()) {
                    threadPool.remove(m)
                }
            }
        }
    }
}

@Suppress("Unused")
class AsyncTickHandler {
    companion object {
        private var threadPool: MutableMap<KCallable<*>, Thread> = mutableMapOf()

        fun <T : Any> subscribe(clazz: KClass<T>) {
            for (m in clazz.members) {
                if (m.hasAnnotation<AsyncTick>()) {
                    threadPool += Pair(m, Thread {
                        while (true) {
                            m.call(clazz.createInstance())
                            Thread.sleep(m.findAnnotation<AsyncTick>()?.delayMS!!.toLong())
                        }
                    })
                    threadPool[m]!!.start()
                }
            }
        }

        fun <T : Any> unsubscribe(clazz: KClass<T>) {
            for (m in clazz.members) {
                if (m.hasAnnotation<AsyncTick>()) {
                    threadPool.remove(m)
                }
            }
        }
    }
}

@Suppress("Unused")
class AsyncHandler {
    companion object {
        private var threadPool: MutableMap<KCallable<*>, Thread> = mutableMapOf()

        fun <T : Any> subscribe(clazz: KClass<T>) {
            for (m in clazz.members) {
                if (m.hasAnnotation<Async>()) {
                    threadPool += Pair(m, Thread {
                        m.call(clazz.createInstance())
                    })
                    threadPool[m]!!.start()
                }
            }
        }

        fun <T : Any> unsubscribe(clazz: KClass<T>) {
            for (m in clazz.members) {
                if (m.hasAnnotation<Async>()) {
                    threadPool.remove(m)
                }
            }
        }
    }
}