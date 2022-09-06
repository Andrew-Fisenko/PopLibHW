package com.example.poplibhw.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.poplibhw.PopLibHW
import com.example.poplibhw.core.OnBackPressedListener
import com.example.poplibhw.databinding.FragmentCardRepoBinding
import com.example.poplibhw.model.Repo
import com.example.poplibhw.network.NetworkProvider
import com.example.poplibhw.repository.RepoRepositoryImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(), RepoView, OnBackPressedListener {

    companion object {

        private const val ARG_LOGIN = "ARG_LOGIN"
        private const val ARG_REPO_NAME = "ARG_REPO_NAME"

        fun getInstance(login: String, name: String): RepoFragment {
            return RepoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LOGIN, login)
                    putString(ARG_REPO_NAME, name)
                }
            }
        }
    }

    private var viewBinding: FragmentCardRepoBinding? = null

    private val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(
            RepoRepositoryImpl(NetworkProvider.reposApi),
            PopLibHW.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCardRepoBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ARG_LOGIN)?.let { login ->
            arguments?.getString(ARG_REPO_NAME)?.let { name ->
                presenter.loadRepo(login, name)
                Toast.makeText(requireContext(), "Репозиторий загружен", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun initRepo(repo: Repo) {
        viewBinding?.apply {
            tvName.text = repo.name
            tvLang.text = repo.language
            tvUrl.text = repo.htmlUrl.toString()
            tvDesc.text = repo.description
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
        Toast.makeText(context, "Error! Coming back...", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}
