/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

package execution.settings

/**
 * **Author: Integr**
 * - **What:** Exception that the SettingsBuilder is not Built
 * - **How:** gets thrown when not built
 * - **Why:** Debugging
 */
class NotBuiltException(message: String?) : Exception(message)