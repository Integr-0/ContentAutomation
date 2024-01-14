package async.handlers

import async.annotations.Async
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.hasAnnotation

/**
 * **Author: Integr**
 * - **What:** Handler for async execution
 * - **How:** Separate thread
 * - **Why:** To make running async easier
 */
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