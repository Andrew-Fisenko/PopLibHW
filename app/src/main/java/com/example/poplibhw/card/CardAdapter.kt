//package com.example.poplibhw.card
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.poplibhw.R
//import com.example.poplibhw.model.GitHubUser
//
//class CardAdapter() :
//    RecyclerView.Adapter<GithubUserViewHolder>() {
//
//    var user: GitHubUser = emptyList()
//        @SuppressLint("NotifyDataSetChanged")
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
//        return GithubUserViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
//        holder.bind(users[position])
//    }
//
//    override fun getItemCount() = users.size
//}
//
//class GithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    private val tvLogin by lazy { itemView.findViewById<TextView>(R.id.tvUserLogin) }
//
//    fun bind(item: GitHubUser) = with(item) {
//        tvLogin.text = login
//    }
//
//}
