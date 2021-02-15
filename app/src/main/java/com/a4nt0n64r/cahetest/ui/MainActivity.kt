package com.a4nt0n64r.cahetest.ui

import android.os.Bundle
import com.a4nt0n64r.cahetest.R
import com.a4nt0n64r.cahetest.ui.base.AbstractActivityPresenter
import com.a4nt0n64r.cahetest.ui.base.ActivityView
import com.a4nt0n64r.cahetest.ui.fragments.FirstFragment
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get

const val FIRST_FRAGMENT = 0
const val FRAGMENT_CHANGED = "fragment_changed"


class MainActivity : MvpAppCompatActivity(), ActivityView {

    @InjectPresenter
    lateinit var presenter: AbstractActivityPresenter

    @ProvidePresenter
    fun provide(): AbstractActivityPresenter = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.loadFragment(FIRST_FRAGMENT)

    }

    override fun showFragment(fragmentId: Int) {
        when (fragmentId) {
            FIRST_FRAGMENT -> {
                setUpFirstFragment()
            }

        }
    }

    private fun setUpFirstFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, FirstFragment(), FRAGMENT_CHANGED)
            .commit()
    }

    public override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
