package com.example.memeitupapp.ui.listmemes

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
import com.example.memeitupapp.ui.adapter.ListMemesAdapter
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.example.memeitupapp.util.Status
import kotlinx.android.synthetic.main.fragment_list_memes.fragment_list_memes_progress_bar
import kotlinx.android.synthetic.main.fragment_list_memes.fragment_list_memes_recycler_view

class ListMemesFragment : Fragment() {

    private lateinit var listMemesViewModel: ListMemesViewModel
    private lateinit var listMemesAdapter: ListMemesAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_list_memes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listMemesViewModel = ViewModelProvider(this).get(ListMemesViewModel::class.java)
        listMemesViewModel.getMemesFromApi()
        listMemesViewModel.mainState.observe(::getLifecycle, ::updateUI)
    }

    private fun showErrorMsg(errorMessage: String?) {
        fragment_list_memes_progress_bar.visibility = View.GONE
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(memes: Event<Data<List<Meme>>>) {
        when (memes.peekContent().responseType) {
            Status.GET_MEMES_ERROR -> {
                fragment_list_memes_progress_bar.visibility = View.GONE
                showErrorMsg(memes.peekContent().error?.message)
            }
            Status.LOADING -> {
                fragment_list_memes_progress_bar.visibility = View.VISIBLE
            }
            Status.GET_MEMES_SUCCESS -> {
                fragment_list_memes_progress_bar.visibility = View.GONE
                showMemes(memes.peekContent().data)
            }
        }
    }

    private fun showMemes(memes: List<Meme>?) {
        memes?.let {
            listMemesAdapter = ListMemesAdapter()
            listMemesAdapter.submitList(memes)
            fragment_list_memes_recycler_view.layoutManager = LinearLayoutManager(context)
            fragment_list_memes_recycler_view.adapter = listMemesAdapter
        }
    }
}