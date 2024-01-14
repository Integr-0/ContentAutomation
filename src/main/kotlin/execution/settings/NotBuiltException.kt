package execution.settings

/**
 * **Author: Integr**
 * - **What:** Exception that the SettingsBuilder is not Built
 * - **How:** gets thrown when a Value is Null
 * - **Why:** Debugging
 */
class NotBuiltException(message: String?) : Exception(message)