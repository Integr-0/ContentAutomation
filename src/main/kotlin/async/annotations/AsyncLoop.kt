/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

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