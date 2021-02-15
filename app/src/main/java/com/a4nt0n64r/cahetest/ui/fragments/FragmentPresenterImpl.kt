package com.a4nt0n64r.cahetest.ui.fragments

import androidx.annotation.WorkerThread
import com.a4nt0n64r.cahetest.domain.model.CloudPlayer
import com.a4nt0n64r.cahetest.domain.model.Person
import com.a4nt0n64r.cahetest.domain.repository.Repository
import com.a4nt0n64r.cahetest.network.NetworkRepository
import com.a4nt0n64r.cahetest.ui.base.AbstractFragmentPresenter
import kotlinx.coroutines.*
import moxy.InjectViewState


@InjectViewState
class FragmentPresenterImpl(
    private val repository: Repository,
    private val cloudRepository: NetworkRepository
) : AbstractFragmentPresenter() {

    private lateinit var cloudPlayer: CloudPlayer

    private val job: Job by lazy { SupervisorJob() }

    override fun onDeleteButtonWasClicked(name: String) {
        if (name != "") {
            CoroutineScope(Dispatchers.Main + job).launch {
                val person: Person? = repository.findPlayer(name)
                if (person != null) {
                    repository.deletePlayer(name)
                    withContext(Dispatchers.Main) {
                        viewState.showSnackbar("deleted $name")
                    }
                } else {
                    withContext(Dispatchers.Main) { viewState.showSnackbar("There's no players with name $name") }
                }
            }
        } else {
            CoroutineScope(Dispatchers.Main + job).launch {
                viewState.showSnackbar("Empty delete request!")
            }
        }
    }

    override fun onSaveButtonWasClicked(person: Person) {
        CoroutineScope(Dispatchers.Main + job).launch {
            if (person.name != "" && person.data != "") {
                repository.savePlayer(Person(person.name, person.data))
                withContext(Dispatchers.Main) {
                    viewState.showSnackbar("save ${person.name} ${person.data}")
                }
            } else {
                CoroutineScope(Dispatchers.Main + job).launch {
                    viewState.showSnackbar("Enter data and name!")
                }
            }
        }
    }

    override fun onFindButtonWasClicked(name: String) {
        if (name != "") {
            val person: Deferred<Person>? = CoroutineScope(Dispatchers.IO).async {
                repository.findPlayer(name)
            }
            CoroutineScope(Dispatchers.Main + job).launch {
                if (person != null) {
                    viewState.showSnackbar("find ${person.await()}")
                    viewState.fillName(person.await().name)
                    viewState.fillData(person.await().data)
                } else {
                    withContext(Dispatchers.Main) { viewState.showSnackbar("There's no players with name $name") }
                }
            }
        } else {
            CoroutineScope(Dispatchers.Main + job).launch {
                viewState.showSnackbar("Empty find request!")
            }
        }
    }

    override fun onShowButtonWasClicked() {
        CoroutineScope(Dispatchers.Main + job).launch {
            val people: List<Person>? = withContext(Dispatchers.IO) {
                repository.getAllPlayers()
            }
            if (!people.isNullOrEmpty()) {
                withContext(Dispatchers.Main) { viewState.showSnackbar("show all") }
                var names = ""
                var data = ""
                for (pl in people) {
                    names += (" " + pl.name)
                    data += (" " + pl.data)
                }
                viewState.fillName(names)
                viewState.fillData(data)
            } else {
                withContext(Dispatchers.Main) { viewState.showSnackbar("There's no players!") }
            }
        }
    }


    override fun onNetButtonWasClicked() {
        CoroutineScope(Dispatchers.Main + job).launch {

            cloudPlayer = withContext(Dispatchers.IO) {
                getCloudPlayer()
            }

            viewState.showSnackbar(cloudPlayer.toString())
        }

    }

    @WorkerThread
    private suspend fun getCloudPlayer(): CloudPlayer {

        try {
            val cloudPlayer = cloudRepository.getPlayer()
            return cloudPlayer!!
        } catch (e: NullPointerException) {
            viewState.showSnackbar("Данные не пришли!")
        }

        return CloudPlayer(Person("Пусто", "Пусто"))
    }

    override fun onDestroy() {

        job.cancel()
    }
}
