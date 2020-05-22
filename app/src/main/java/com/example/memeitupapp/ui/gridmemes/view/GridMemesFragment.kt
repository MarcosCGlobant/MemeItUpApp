package com.example.memeitupapp.ui.gridmemes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memeitupapp.R
import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.data.repository.MemeService
import com.example.memeitupapp.ui.adapter.GridMemesAdapter
import com.example.memeitupapp.ui.contract.GridMemesContract
import com.example.memeitupapp.ui.gridmemes.model.GridMemesModel
import com.example.memeitupapp.ui.gridmemes.viewmodel.GridMemesViewModel
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.example.memeitupapp.util.Status
import kotlinx.android.synthetic.main.fragment_grid_memes.fragment_grid_memes_progress_bar
import kotlinx.android.synthetic.main.fragment_grid_memes.fragment_grid_memes_recycler_view

class GridMemesFragment : Fragment() {

    private lateinit var gridMemesViewModel: GridMemesContract.ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_grid_memes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridMemesViewModel = ViewModelProvider(this, viewModelFactory {
            GridMemesViewModel(GridMemesModel(MemeService())) })
            .get(GridMemesViewModel::class.java)
        gridMemesViewModel.fetchMemes()
        gridMemesViewModel.getLiveData().observe(::getLifecycle, ::updateUI)
    }

    private fun showErrorMsg(errorMessage: String?) {
        fragment_grid_memes_progress_bar.visibility = View.GONE
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(memes: Event<Data<List<Meme>>>) {
        when (memes.peekContent().responseType) {
            Status.GET_MEMES_ERROR -> {
                fragment_grid_memes_progress_bar.visibility = View.GONE
                showErrorMsg(memes.peekContent().error?.message)
            }
            Status.LOADING -> {
                fragment_grid_memes_progress_bar.visibility = View.VISIBLE
            }
            Status.GET_MEMES_SUCCESS -> {
                fragment_grid_memes_progress_bar.visibility = View.GONE
                showMemes(memes.peekContent().data)
            }
        }
    }

    private fun showMemes(memes: List<Meme>?) {
        memes?.let {
            val gridMemesAdapter = GridMemesAdapter()
            gridMemesAdapter.submitList(memes)
            fragment_grid_memes_recycler_view.layoutManager = GridLayoutManager(context, COLUMNS)
            fragment_grid_memes_recycler_view.adapter = gridMemesAdapter
        }
    }

    companion object {
        const val COLUMNS = 4
    }

    private inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
        }
}