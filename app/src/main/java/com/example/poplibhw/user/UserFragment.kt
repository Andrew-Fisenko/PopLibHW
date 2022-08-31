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
import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.network.NetworkProvider
import com.example.poplibhw.repositiry.GitHubRepositoryImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, OnBackPressedListener {

    companion object {
        fun getInstance(): UserFragment {
            return UserFragment()
        }
    }

    private var viewBinding: FragmentUserListBinding? = null

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            GitHubRepositoryImpl(NetworkProvider.usersApi),
            PopLibHW.instance.router
        )
    }

    private val adapter: UserAdapter by lazy {
        UserAdapter(presenter::openCardUser)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUserListBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            this?.rvGitHubUsers?.layoutManager = LinearLayoutManager(requireContext())
            this?.rvGitHubUsers?.adapter = adapter
        }
    }

    override fun initList(list: List<GitHubUser>) {
        viewBinding?.apply {
        adapter.users = list
        }
    }

    override fun showLoading() {
        viewBinding?.apply {
            progress.visibility = View.VISIBLE
            frame.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        viewBinding?.apply {
            progress.visibility = View.GONE
            frame.visibility = View.GONE
        }
    }

    override fun showError() {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onBackPressed() = presenter.onBackPressed()
}
