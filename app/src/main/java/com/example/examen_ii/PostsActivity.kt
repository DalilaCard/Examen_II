package com.example.examen_ii

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_ii.entities.PostEntity
import com.example.examen_ii.repositories.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostsActivity : AppCompatActivity() {

    private val postRepository = PostRepository()

    companion object {
        var adaptadorRecyler: PostAdapter = PostAdapter(emptyList())
    }

    lateinit var recyclerViewPosts: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_posts)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerViewPosts = findViewById(R.id.rvPosts)
        recyclerViewPosts.layoutManager = LinearLayoutManager(this)
        recyclerViewPosts.adapter = PostsActivity.adaptadorRecyler

        val id = intent.getLongExtra("id_user", -1L)

        if(id != -1L){
            obtenerPosts(id.toLong())
        }else{

        }

        PostsActivity.adaptadorRecyler.setOnItemClickListener(object :
            PostAdapter.onItemClickListener {
            override fun onItemLongClick(post: PostEntity) {
                Log.d("Users", "Clic largo: Users ${post.title}")
            }

            override fun onItemClick(post: PostEntity) {
                Log.d("Posts", "Clic corto, Users ${post.title}}")
                val intent = Intent(this@PostsActivity, CommentsActivity::class.java)
                intent.putExtra("id_post", post.id)
                startActivity(intent)
            }
        })

    }

    private fun obtenerPosts(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val posts = postRepository.getAllPosts(id)
                withContext(Dispatchers.Main) {
                    PostsActivity.adaptadorRecyler.updatePostList(posts)
                    Toast.makeText(this@PostsActivity, "Datos de la API", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@PostsActivity, "ERROR", Toast.LENGTH_SHORT).show()
                manejarError(e)
            }
        }
    }

    private suspend fun manejarError(e: Exception) {
        Log.d("Users", "Error al obtener users ${e.message}")
        if (e is retrofit2.HttpException && e.code() == 404) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@PostsActivity,
                    "Aún no hay Posts",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@PostsActivity,
                    "Error al obtener datos: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


}