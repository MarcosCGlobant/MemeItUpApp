package com.example.memeitupapp.ui.listmemes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memeitupapp.R
import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.data.repository.MemeService
import com.example.memeitupapp.ui.adapter.ListMemesAdapter
import com.example.memeitupapp.ui.contract.ListMemesContract
import com.example.memeitupapp.ui.listmemes.model.ListMemesModel
import com.example.memeitupapp.ui.listmemes.viewmodel.ListMemesViewModel
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.example.memeitupapp.util.MemeViewModelFactory
import com.example.memeitupapp.util.Status
import kotlinx.android.synthetic.main.layout_fragment_list_memes.layout_fragment_list_memes_progress_bar
import kotlinx.android.synthetic.main.layout_fragment_list_memes.layout_fragment_list_memes_recycler_view

class ListMemesFragment : Fragment() {

    private lateinit var listMemesViewModel: ListMemesContract.ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.layout_fragment_list_memes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listMemesViewModel = ViewModelProvider(this, MemeViewModelFactory.viewModelFactory {
            ListMemesViewModel(ListMemesModel(MemeService()))
        })
            .get(ListMemesViewModel::class.java)
        listMemesViewModel.fetchMemes()
        listMemesViewModel.getLiveData().observe(::getLifecycle, ::updateUI)
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
            val listMemesAdapter = ListMemesAdapter()
            listMemesAdapter.submitList(memes)
            layout_fragment_list_memes_recycler_view.layoutManager = LinearLayoutManager(context)
            layout_fragment_list_memes_recycler_view.adapter = listMemesAdapter
        }
    }
}