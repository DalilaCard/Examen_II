package com.example.examen_ii

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_ii.entities.CommentEntity

class CommentAdapter(var listadoComments: List<CommentEntity>):
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private lateinit var miListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(comment: CommentEntity)
        fun onItemLongClick(comment: CommentEntity)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        miListener = listener
    }


    inner class ViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val nameComment : TextView = itemView.findViewById(R.id.tvNameComment)
        var emailComment: TextView = itemView.findViewById(R.id.tvEmailComment)
        val bodyComment : TextView = itemView.findViewById(R.id.tvBodyComment)

        init {
            itemView.setOnClickListener {
                miListener.onItemClick(listadoComments[adapterPosition])
            }
            itemView.setOnLongClickListener {
                miListener.onItemLongClick(listadoComments[adapterPosition])
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista: View =
            LayoutInflater.from(parent.context).inflate(R.layout.comment_custom, parent, false)
        return ViewHolder(vista, miListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = listadoComments[position]

        holder.nameComment.text = comment.name
        holder.emailComment.text = comment.email
        holder.bodyComment.text = comment.body
    }

    override fun getItemCount() = listadoComments.size


    fun updateCommentList(newCommentList: List<CommentEntity>) {
        newCommentList.forEach {
            Log.d("Cat", "Adaptador Comment: ${it}.")
        }
        listadoComments = newCommentList
        notifyDataSetChanged()
    }
}