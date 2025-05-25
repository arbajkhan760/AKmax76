// Placeholder for AffiliateFragment.ktpackage com.example.akmax76

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

// Data model for a social media post
data class Post(val username: String, val content: String)

// Adapter for displaying posts in a RecyclerView
class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val username: TextView = view.findViewById(R.id.usernameText)
        val content: TextView = view.findViewById(R.id.contentText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.username.text = posts[position].username
        holder.content.text = posts[position].content
    }

    override fun getItemCount() = posts.size
}

class AffiliateFragment : Fragment() {

    private val posts = listOf(
        Post("alice", "Hello, world!"),
        Post("bob", "Check out my new project!"),
        Post("carol", "Excited to join this network!")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_affiliate, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PostAdapter(posts)
        return view
    }
}
