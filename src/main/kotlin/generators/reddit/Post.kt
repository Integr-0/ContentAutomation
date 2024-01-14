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