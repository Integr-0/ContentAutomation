/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

package execution.settings

/**
 * **Author: Integr**
 * - **What:** Exception that a value is not set
 * - **How:** gets thrown when a Value is Null
 * - **Why:** Debugging
 */
class NotSetException(message: String?) : Exception(message)