package com.example.poplibhw.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poplibhw.core.OnBackPressedListener
import com.example.poplibhw.PopLibHW
import com.example.poplibhw.databinding.FragmentUserCardBinding
import com.example.poplibhw.databinding.FragmentUserListBinding
import com.example.poplibhw.main.UserAdapter
import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.repositiry.GitHubRepositoryImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CardUserFragment : MvpAppCompatFragment(), CardUserView, OnBackPressedListener {

    companion object {
        fun getInstance(): CardUserFragment {
            return  CardUserFragment()
        }
    }

    private lateinit var viewBingding : FragmentUserCardBinding

    private val adapter = CardAdapter()
    private val presenter: CardUserPresenter by moxyPresenter {
        CardUserPresenter(GitHubRepositoryImpl(), PopLibHW.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUserCardBinding.inflate(inflater, container,false).also {
            viewBingding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBingding){

            tvUserCard.text = adapter.user.login
        }
    }

    override fun initUser(user: GitHubUser) {
        adapter.user = user
    }

    override fun onBackPressed() = presenter.onBackPressed()
}
