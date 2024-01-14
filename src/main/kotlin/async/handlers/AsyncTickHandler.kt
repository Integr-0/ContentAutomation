/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

package async.handlers

import async.annotations.AsyncTick
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

/**
 * **Author: Integr**
 * - **What:** Handler for async ticker
 * - **How:** Loop in a separate thread
 * - **Why:** To make running async loops easier
 */
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