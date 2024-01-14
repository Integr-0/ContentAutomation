package async.annotations

/**
 * **Author: Integr**
 * - **What:** Annotation for Async ticker
 * - **How:** Loop in a separate thread with delay
 * - **Why:** To make running async loops easier
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class AsyncTick(val delayMS: Int)