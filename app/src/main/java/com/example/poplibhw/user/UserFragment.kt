package com.example.poplibhw.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poplibhw.PopLibHW
import com.example.poplibhw.core.OnBackPressedListener
import com.example.poplibhw.databinding.FragmentUserListBinding
import com.example.poplibhw.main.UserAdapter
import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.repositiry.GitHubRepositoryImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, OnBackPressedListener {

    companion object {
        fun getInstance(): UserFragment {
            return UserFragment()
        }
    }

    private lateinit var viewBingding: FragmentUserListBinding

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GitHubRepositoryImpl(), PopLibHW.instance.router)
    }

    private val adapter = UserAdapter(object : UserAdapter.OnItemViewClick {
        override fun onItemViewClick(user: GitHubUser) {
            presenter.openCardUser(user)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUserListBinding.inflate(inflater, container, false).also {
            viewBingding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBingding) {
            this.rvGitHubUsers.layoutManager = LinearLayoutManager(requireContext())
            this.rvGitHubUsers.adapter = adapter
        }
    }

    override fun initList(list: List<GitHubUser>) {
           adapter.users = list
    }

    override fun showLoading() = with(viewBingding) {
        progress.visibility = View.VISIBLE
        frame.visibility = View.VISIBLE
    }

    override fun hideLoading() = with(viewBingding) {
        progress.visibility = View.GONE
        frame.visibility = View.GONE
    }

    override fun showError() {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() = presenter.onBackPressed()
}
