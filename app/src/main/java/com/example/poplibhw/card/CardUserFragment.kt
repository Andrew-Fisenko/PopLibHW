package com.example.poplibhw.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poplibhw.PopLibHW
import com.example.poplibhw.R
import com.example.poplibhw.core.OnBackPressedListener
import com.example.poplibhw.databinding.FragmentCardUserBinding
import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.model.Repo
import com.example.poplibhw.network.NetworkProvider
import com.example.poplibhw.repo.RepoAdapter
import com.example.poplibhw.repository.GitHubRepositoryImpl
import com.example.poplibhw.repository.RepoRepositoryImpl
import com.example.poplibhw.utils.loadImage
import com.example.poplibhw.utils.makeGone
import com.example.poplibhw.utils.makeVisible
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CardUserFragment : MvpAppCompatFragment(), CardUserView, OnBackPressedListener {

    companion object {
        private const val ARG_LOGIN = "ARG_LOGIN"

        fun getInstance(login: String): CardUserFragment {
            return CardUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LOGIN, login)
                }
            }
        }
    }

    private var viewBinding: FragmentCardUserBinding? = null

    private val presenter: CardUserPresenter by moxyPresenter {
        CardUserPresenter(
            GitHubRepositoryImpl(NetworkProvider.usersApi),
            RepoRepositoryImpl(NetworkProvider.reposApi),
            PopLibHW.instance.router
        )
    }

    private val repoAdapter: RepoAdapter by lazy {
        RepoAdapter(presenter::openRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCardUserBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ARG_LOGIN)?.let {
            presenter.loadUser(it)
        }

        viewBinding?.apply {
            rvRepositories.layoutManager = LinearLayoutManager(requireContext())
            rvRepositories.addItemDecoration(
                DividerItemDecoration(
                    this.root.context,
                    RecyclerView.VERTICAL
                ).apply {
                    setDrawable(resources.getDrawable(R.drawable.divider_line))
                }
            )
            rvRepositories.adapter = repoAdapter
        }
    }

    override fun initUser(user: GitHubUser) {
        viewBinding?.apply {
            tvUserCard.text = user.login
            ivUserCard.loadImage(user.avatarUrl)
        }
    }

    override fun initRepos(list: List<Repo>) {
        repoAdapter.repos = list
        arguments?.getString(ARG_LOGIN)?.let {
            repoAdapter.login = it
        }
    }

    override fun showLoading() {
        viewBinding?.apply {
            tvUserCard.makeGone()
            ivUserCard.makeGone()
            progress.makeVisible()
        }
    }

    override fun hideLoading() {
        viewBinding?.apply {
            tvUserCard.makeVisible()
            ivUserCard.makeVisible()
            progress.makeGone()
        }
    }

    override fun showError() {
        Toast.makeText(context, "Error! Coming back...", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onBackPressed() = presenter.onBackPressed()
}
