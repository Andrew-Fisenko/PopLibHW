package com.example.poplibhw.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poplibhw.R
import com.example.poplibhw.model.GitHubUser

class UserAdapter(
    private var onItemViewClick: OnItemViewClick
) : RecyclerView.Adapter<UserAdapter.GitHubUserViewHolder>() {

    interface OnItemViewClick {
        fun onItemViewClick(user: GitHubUser)
    }

    var users: List<GitHubUser> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubUserViewHolder {
        return GitHubUserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GitHubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    inner class GitHubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvLogin by lazy { itemView.findViewById<TextView>(R.id.tvUserLogin) }

        fun bind(item: GitHubUser) = with(item) {
            tvLogin.text = login
            itemView.apply {
                setOnClickListener { onItemViewClick.onItemViewClick(item) }
            }
        }
    }
}

