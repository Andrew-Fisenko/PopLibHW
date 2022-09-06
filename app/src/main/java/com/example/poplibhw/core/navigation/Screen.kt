package com.example.poplibhw.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.poplibhw.card.CardUserFragment
import com.example.poplibhw.repo.RepoFragment
import com.example.poplibhw.user.UserFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserFragment.getInstance()
    }
}

data class CardUserScreen(private val login: String) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return CardUserFragment.getInstance(login)
    }
}

data class RepoScreen(private val login: String, private val name: String) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return RepoFragment.getInstance(login, name)
    }
}
