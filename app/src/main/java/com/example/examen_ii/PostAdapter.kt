package com.example.examen_ii

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_ii.entities.PostEntity

class PostAdapter(var listadoPosts: List<PostEntity>):
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private lateinit var miListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(post: PostEntity)
        fun onItemLongClick(post: PostEntity)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        miListener = listener
    }

    inner class ViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val titlePost : TextView = itemView.findViewById(R.id.tvTitlePost)
        var bodyPost: TextView = itemView.findViewById(R.id.tvBodyPost)

        init {
            itemView.setOnClickListener {
                miListener.onItemClick(listadoPosts[adapterPosition])
            }
            itemView.setOnLongClickListener {
                miListener.onItemLongClick(listadoPosts[adapterPosition])
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista: View =
            LayoutInflater.from(parent.context).inflate(R.layout.posts_custom, parent, false)
        return ViewHolder(vista, miListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = listadoPosts[position]
        holder.titlePost.text = post.title
        holder.bodyPost.text = post.body
    }

    override fun getItemCount() = listadoPosts.size

    fun updatePostList(newPostList: List<PostEntity>) {
        newPostList.forEach {
            Log.d("Cat", "Adaptador Post: ${it}.")
        }
        listadoPosts = newPostList
        notifyDataSetChanged()
    }
}