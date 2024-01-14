package async.annotations

/**
 * **Author: Integr**
 * - **What:** Annotation for Async loop
 * - **How:** Loop in a separate thread
 * - **Why:** To make running async loops easier
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class AsyncLoop