package async.annotations

/**
 * **Author: Integr**
 * - **What:** Annotation for Async execution
 * - **How:** Separate thread
 * - **Why:** To make running async easier
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Async