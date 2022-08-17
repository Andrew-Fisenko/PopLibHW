package com.example.poplibhw.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poplibhw.core.OnBackPressedListener
import com.example.poplibhw.PopLibHW
import com.example.poplibhw.databinding.FragmentUserListBinding
import com.example.poplibhw.main.UserAdapter
import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.repositiry.GitHubRepositoryImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, OnBackPressedListener {

    companion object {
        fun getInstance(): UserFragment {
            return  UserFragment()
        }
    }

    private lateinit var viewBingding : FragmentUserListBinding

    private val adapter = UserAdapter()
    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GitHubRepositoryImpl(), PopLibHW.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUserListBinding.inflate(inflater, container,false).also {
            viewBingding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBingding){
            rvGitHubUsers.layoutManager = LinearLayoutManager(requireContext())
            rvGitHubUsers.adapter = adapter
        }
    }

    override fun initList(list: List<GitHubUser>) {
        adapter.users = list
    }

    override fun onBackPressed() = presenter.onBackPressed()
}
