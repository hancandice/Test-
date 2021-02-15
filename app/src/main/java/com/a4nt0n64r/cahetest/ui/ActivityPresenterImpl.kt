package com.a4nt0n64r.cahetest.ui

import com.a4nt0n64r.cahetest.ui.base.AbstractActivityPresenter
import moxy.InjectViewState


@InjectViewState
class ActivityPresenterImpl : AbstractActivityPresenter() {

    override fun loadFragment(fragmentId: Int) {
        viewState.showFragment(fragmentId)
    }
}