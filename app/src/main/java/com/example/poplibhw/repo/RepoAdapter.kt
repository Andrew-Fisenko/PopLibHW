package com.example.poplibhw.repo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poplibhw.databinding.ItemRepoBinding
import com.example.poplibhw.model.Repo

typealias OnRepoViewClick = (login: String, name: String) -> Unit

class RepoAdapter(
    private val onRepoViewClick: OnRepoViewClick
) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    var repos: List<Repo> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var login: String

    inner class RepoViewHolder(
        private val binding: ItemRepoBinding,
        private val onRepoViewClick: OnRepoViewClick
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo) = with(binding) {
            tvName.text = repo.name
            tvUrl.text = repo.htmlUrl.toString()
            tvDescription.text = repo.description
            root.setOnClickListener {
                onRepoViewClick.invoke(login, repo.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RepoViewHolder(binding, onRepoViewClick)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount() = repos.size
}

