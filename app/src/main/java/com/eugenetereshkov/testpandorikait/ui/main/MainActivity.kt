package com.eugenetereshkov.testpandorikait.ui.main

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.eugenetereshkov.testpandorikait.R
import com.eugenetereshkov.testpandorikait.ui.search.SearchFragment

class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, SearchFragment.newInstance(), SearchFragment.TAG)
                    .commitNow()
        }
    }
}
