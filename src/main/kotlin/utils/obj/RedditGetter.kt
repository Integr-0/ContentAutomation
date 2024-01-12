package utils.obj
///////////////////////////////////////////////////////////////
//              Author: Julian                               //
//  What: Get the top post and responses from r/ask          //
//  How: By getting the page data json and reading its data  //
//  Why: To generate scripts for videos                      //
///////////////////////////////////////////////////////////////

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun main() {
    val posts = getPosts(URL("https://www.reddit.com/r/askreddit/.json"), 3, 3)
    for (post in posts) {
        println("Question:\n${post.question}")
        println("Responses:\n${post.responses.joinToString("\n")}")
    }
}

fun getPosts(url: URL, postCount: Int, responseCount: Int): ArrayList<Post> {
    val posts: ArrayList<Post> = arrayListOf()

    val json = getJson(url)
    val childrenPosts = getChildrenPosts(json)

    for (i in 0..Math.min(postCount, childrenPosts.count() - 1)) {
        val childPost = childrenPosts[i]
        val childPostData = childPost["data"] as Map<*, *>

        // Setup variables to build post
        val question: String = "${childPostData["title"].toString()}\n${childPostData["selftext"].toString()}"
        val responseTexts: ArrayList<String> = arrayListOf()

        // grab responses by getting the url of the post and getting its data by adding .json suffix to link
        val responses = getChildrenComments(getJson(URL(childPostData["url"].toString() + ".json")))

        for (j in 0..Math.min(responseCount, responses.count() - 1)) {
            val response = responses[j]
            val data = response["data"] as Map<*, String?>
            val bodyHtml: String? = data["body_html"]
            if (!(bodyHtml.isNullOrBlank() || bodyHtml.contains("https://www.reddit.com/r/ask/about/rules"))) { // empty or bot
                // remove all the html shit from the message
                val text = bodyHtml
                    .replace("&lt;div class=\"md\"&gt;&lt;p&gt;", "")
                    .replace("&lt;/p&gt;\n&lt;/div&gt;", "")
                    .replace("&lt;p&gt;", "")
                    .replace("&lt;/p&gt;", "")
                    .replace("&amp;#39;", "")
                    .replace("&lt;p&gt;", "")
                    .replace(";br/&gt;", "")
                    .replace("&lt;/p&gt;;", "")
                    .replace("&amp;#x200B;", "")
                    .replace("&lt;/ol&gt;", "")
                    .replace("&lt;/ol&gt;", "")
                    .replace("&lt;li&gt;", "")
                    .replace("&amp;", "")
                    .replace("\n", " ")
                responseTexts.add(text)
            }
        }
        posts.add(Post(question.replace("\n", " "), responseTexts))
    }

    return posts
}

// Returns the urls content as json String
fun getJson(url: URL): String {
    val conn = url.openConnection() as HttpURLConnection
    conn.requestMethod = "GET"
    conn.setRequestProperty("User-Agent", "YourAppName/1.0") // Replace with your app name

    val reader = BufferedReader(InputStreamReader(conn.inputStream))
    var json: String? = ""
    var line: String?

    while (reader.readLine().also { line = it } != null) {
        json += line
    }

    return json!!
}

// use to get the children ArrayList of Posts, used on front Pages
fun getChildrenPosts(json: String): ArrayList<Map<*, *>> {
    val mapAdapter = Gson().getAdapter(object : TypeToken<Map<String, Any?>>() {})

    val model: Map<String, Any?> = mapAdapter.fromJson(json)
    val data = model["data"] as Map<*, *>
    val children = data["children"] as ArrayList<Map<*, *>>
    return children
}

// use to get the children ArrayList of Comments, used on Posts
fun getChildrenComments(json: String): ArrayList<Map<*, *>> {
    val arrayListAdapter = Gson().getAdapter(object : TypeToken<ArrayList<Map<String, Any?>>>() {})

    val model: ArrayList<Map<String, Any?>>? = arrayListAdapter.fromJson(json)
    val body = model?.get(1) as Map<*, *>
    val data = body["data"] as Map<*, *>
    val children = data["children"] as ArrayList<Map<*, *>>
    return children
}

data class Post(
    val question: String = "",
    val responses: ArrayList<String> = arrayListOf(),
)