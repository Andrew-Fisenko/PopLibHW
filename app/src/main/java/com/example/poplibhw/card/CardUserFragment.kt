package com.example.poplibhw.card

import GITHUB_USER
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.poplibhw.PopLibHW
import com.example.poplibhw.core.OnBackPressedListener
import com.example.poplibhw.databinding.FragmentUserCardBinding
import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.network.NetworkProvider
import com.example.poplibhw.repositiry.GitHubRepositoryImpl
import com.example.poplibhw.repositiry.GitHubRepositoryImplOld
import com.example.poplibhw.utils.loadImage
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

    private var viewBinding: FragmentUserCardBinding? = null


    private val presenter: CardUserPresenter by moxyPresenter {
        CardUserPresenter(
            GitHubRepositoryImpl(NetworkProvider.usersApi),
            PopLibHW.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserCardBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            arguments?.getString(ARG_LOGIN)?.let {
                presenter.loadUser(it)
            }

    }

    override fun initUser(user: GitHubUser) {
        viewBinding?.apply {
            tvUserCard.text = user.login
            ivUserCard.loadImage(user.avatarUrl)
        }
    }

    override fun showLoading() {
        viewBinding?.apply {
            progress.visibility = View.VISIBLE
            ivUserCard.visibility = View.GONE
            frame.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        viewBinding?.apply {
            ivUserCard.visibility = View.VISIBLE
            progress.visibility = View.GONE
            frame.visibility = View.GONE
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
