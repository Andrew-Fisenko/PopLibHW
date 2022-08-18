package com.example.poplibhw.card

import GITHUB_USER
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.poplibhw.PopLibHW
import com.example.poplibhw.core.OnBackPressedListener
import com.example.poplibhw.databinding.FragmentUserCardBinding
import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.repositiry.GitHubRepositoryImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CardUserFragment : MvpAppCompatFragment(), CardUserView, OnBackPressedListener {

    companion object {
        fun getInstance(bundle: Bundle): CardUserFragment {
            return CardUserFragment().apply {
                arguments = bundle
            }
        }
    }

    private lateinit var viewBingding: FragmentUserCardBinding
    private lateinit var gitHubUser: GitHubUser

    private val presenter: CardUserPresenter by moxyPresenter {
        CardUserPresenter(GitHubRepositoryImpl(), PopLibHW.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserCardBinding.inflate(inflater, container, false).also {
            viewBingding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gitHubUser = arguments?.getParcelable<GitHubUser>(GITHUB_USER) ?: GitHubUser(1, "Error")

    }

    override fun initUser(user: GitHubUser) {
        with(viewBingding) {
            tvUserCard.text = gitHubUser.login
        }
    }

    override fun onBackPressed() = presenter.onBackPressed()
}
