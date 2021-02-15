package com.a4nt0n64r.cahetest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a4nt0n64r.cahetest.R
import com.a4nt0n64r.cahetest.domain.model.Person
import com.a4nt0n64r.cahetest.ui.base.AbstractFragmentPresenter
import com.a4nt0n64r.cahetest.ui.base.FragmentView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_first.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get


class FirstFragment : MvpAppCompatFragment(), FragmentView {

    @InjectPresenter
    lateinit var presenter: AbstractFragmentPresenter

    @ProvidePresenter
    fun provide(): AbstractFragmentPresenter = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        show.setOnClickListener {
            presenter.onShowButtonWasClicked()
        }

        find.setOnClickListener {
            presenter.onFindButtonWasClicked(name_tv.text.toString())
        }

        save.setOnClickListener {
            presenter.onSaveButtonWasClicked(
                Person(
                    name_tv.text.toString(),
                    data_tv.text.toString()
                )
            )
        }

        delete.setOnClickListener {
            presenter.onDeleteButtonWasClicked(find_or_delete_tv.text.toString())
        }

        network.setOnClickListener {
            presenter.onNetButtonWasClicked()
        }


    }

    override fun fillName(name: String) {
        tv1.text = name
    }

    override fun fillData(data: String) {
        tv2.text = data
    }

    override fun showSnackbar(msg: String) {
        Snackbar.make(constr, msg, Snackbar.LENGTH_LONG).show()
    }
}
