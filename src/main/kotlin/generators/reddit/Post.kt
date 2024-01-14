/*
 *    This file is part of the Content automation project by Integr
 *    Copyright (c) Integr.
 *
 *    Full copyright found in LICENSE.md
 */

package generators.reddit

/**
 * **Author: Julian**
 * - **What:** Track post data
 * - **How:** Using the args
 * - **Why:** Simplicity
 */
data class Post(
    val question: String = "",
    val responses: ArrayList<String> = arrayListOf(),
)