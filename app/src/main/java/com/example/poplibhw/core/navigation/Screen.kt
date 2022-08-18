package com.example.poplibhw.core.navigation

import GITHUB_USER
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.poplibhw.card.CardUserFragment
import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.user.UserFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserFragment.getInstance()
    }
}

data class CardUserScreen(val user: GitHubUser) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return CardUserFragment.getInstance(Bundle().apply { putParcelable(GITHUB_USER, user) })
    }
}


