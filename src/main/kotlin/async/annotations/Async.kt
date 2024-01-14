/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

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