package com.example.memeitupapp.ui.gridmemes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memeitupapp.R
import com.globant.domain.entity.Meme
import com.example.memeitupapp.ui.adapter.GridMemesAdapter
import com.example.memeitupapp.ui.gridmemes.viewmodel.GridMemesViewModel
import com.example.memeitupapp.ui.memedetail.view.MemeDetailFragment
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.example.memeitupapp.util.Status
import kotlinx.android.synthetic.main.layout_fragment_grid_memes.layout_fragment_grid_memes_progress_bar
import kotlinx.android.synthetic.main.layout_fragment_grid_memes.layout_fragment_grid_memes_recycler_view
import org.koin.androidx.viewmodel.ext.android.viewModel

class GridMemesFragment : Fragment() {

    private val gridMemesViewModel by viewModel<GridMemesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.layout_fragment_grid_memes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridMemesViewModel.getLiveData().observe(::getLifecycle, ::updateUI)
        gridMemesViewModel.fetchMemes()
    }

    private fun showErrorMsg(errorMessage: String?) {
        layout_fragment_grid_memes_progress_bar.visibility = View.GONE
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(memes: Event<Data<List<Meme>>>) {
        when (memes.peekContent().responseType) {
            Status.GET_MEMES_ERROR -> {
                layout_fragment_grid_memes_progress_bar.visibility = View.GONE
                showErrorMsg(memes.peekContent().error?.message)
            }
            Status.LOADING -> {
                layout_fragment_grid_memes_progress_bar.visibility = View.VISIBLE
            }
            Status.GET_MEMES_SUCCESS -> {
                layout_fragment_grid_memes_progress_bar.visibility = View.GONE
                showMemes(memes.peekContent().data)
            }
        }
    }

    private fun showMemes(memes: List<Meme>?) {
        memes?.let {
            val gridMemesAdapter = GridMemesAdapter { meme ->
                val memeFragment = MemeDetailFragment.newInstance(meme.id)
                memeFragment.show(childFragmentManager, getString(R.string.tag))
            }
            gridMemesAdapter.submitList(memes)
            layout_fragment_grid_memes_recycler_view.layoutManager = GridLayoutManager(context, COLUMNS)
            layout_fragment_grid_memes_recycler_view.adapter = gridMemesAdapter
        }
    }

    companion object {
        const val COLUMNS = 4
    }
}