package com.example.poplibhw.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poplibhw.databinding.ItemUserBinding
import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.utils.loadImage

typealias OnUserClickListener = (login: String) -> Unit

class UserAdapter(
    private val onUserClickListener: OnUserClickListener
) : RecyclerView.Adapter<UserAdapter.GitHubUserViewHolder>() {

    var users: List<GitHubUser> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubUserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GitHubUserViewHolder(binding, onUserClickListener)
    }

    override fun onBindViewHolder(holder: GitHubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    inner class GitHubUserViewHolder(
        private val binding: ItemUserBinding,
        private val onUserClickListener: OnUserClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GitHubUser) = with(binding) {
            tvUserLogin.text = item.login
            ivUserAvatar.loadImage(item.avatarUrl)
            root.setOnClickListener {
                onUserClickListener.invoke(item.login)
            }
        }
    }
}
