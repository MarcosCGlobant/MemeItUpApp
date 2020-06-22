package com.example.memeitupapp.ui.listmemes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memeitupapp.R
import com.globant.domain.entity.Meme
import com.example.memeitupapp.ui.adapter.ListMemesAdapter
import com.example.memeitupapp.ui.listmemes.viewmodel.ListMemesViewModel
import com.example.memeitupapp.ui.memedetail.view.MemeDetailFragment
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.example.memeitupapp.util.Status
import com.example.memeitupapp.util.getColumnsByOrientation
import kotlinx.android.synthetic.main.layout_fragment_list_memes.layout_fragment_list_memes_progress_bar
import kotlinx.android.synthetic.main.layout_fragment_list_memes.layout_fragment_list_memes_recycler_view
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMemesFragment : Fragment() {

    private val listMemesViewModel by viewModel<ListMemesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.layout_fragment_list_memes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listMemesViewModel.getLiveData().observe(::getLifecycle, ::updateUI)
        listMemesViewModel.fetchMemes()
    }

    override fun onPause() {
        layout_fragment_list_memes_recycler_view.visibility = View.GONE
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        listMemesViewModel.fetchMemes()
    }

    private fun showErrorMsg(errorMessage: String?) {
        layout_fragment_list_memes_progress_bar.visibility = View.GONE
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(memes: Event<Data<List<Meme>>>) {
        when (memes.peekContent().responseType) {
            Status.GET_MEMES_ERROR -> {
                layout_fragment_list_memes_progress_bar.visibility = View.GONE
                showErrorMsg(memes.peekContent().error?.message)
            }
            Status.LOADING -> {
                layout_fragment_list_memes_progress_bar.visibility = View.VISIBLE
            }
            Status.GET_MEMES_SUCCESS -> {
                layout_fragment_list_memes_progress_bar.visibility = View.GONE
                showMemes(memes.peekContent().data)
            }
        }
    }

    private fun showMemes(memes: List<Meme>?) {
        memes?.let {
            val listMemesAdapter = ListMemesAdapter { meme ->
                val memeFragment = MemeDetailFragment.newInstance(meme.id)
                memeFragment.show(childFragmentManager, getString(R.string.tag))
            }
            listMemesAdapter.submitList(memes)
            layout_fragment_list_memes_recycler_view.apply {
                layoutManager =
                        GridLayoutManager(context, resources.configuration.getColumnsByOrientation(COLUMNS_PORTRAIT, COLUMNS_LANDSCAPE))
                adapter = listMemesAdapter
                visibility = View.VISIBLE
            }
        }
    }

    companion object {
        private const val COLUMNS_PORTRAIT = 1
        private const val COLUMNS_LANDSCAPE = 2
    }
}
