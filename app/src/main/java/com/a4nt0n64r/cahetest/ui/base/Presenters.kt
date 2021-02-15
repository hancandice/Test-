package com.a4nt0n64r.cahetest.ui.base

import com.a4nt0n64r.cahetest.domain.model.Person
import moxy.MvpPresenter


//Тут описаны события которые могут происходить (нажата кнопка, выделен элемент...)
interface ActivityPresenter {

    fun loadFragment(fragmentId: Int)

    fun onDestroy()
}


interface FragmentPresenter {

    fun onDeleteButtonWasClicked(name: String)
    fun onFindButtonWasClicked(name: String)
    fun onShowButtonWasClicked()
    fun onSaveButtonWasClicked(person: Person)

    fun onNetButtonWasClicked()

    fun onDestroy()
}

abstract class AbstractActivityPresenter : MvpPresenter<ActivityView>(), ActivityPresenter
abstract class AbstractFragmentPresenter : MvpPresenter<FragmentView>(), FragmentPresenter