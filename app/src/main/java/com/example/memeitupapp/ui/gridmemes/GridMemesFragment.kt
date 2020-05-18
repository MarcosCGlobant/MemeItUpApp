package com.example.memeitupapp.ui.gridmemes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.memeitupapp.R
import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.util.DEFAULT_INT
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import kotlinx.android.synthetic.main.fragment_grid_memes.activity_main_call_service_button
import kotlinx.android.synthetic.main.fragment_grid_memes.activity_main_first_meme_name

class GridMemesFragment : Fragment() {

    private lateinit var gridMemesViewModel: GridMemesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_grid_memes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridMemesViewModel = ViewModelProvider(this).get(GridMemesViewModel::class.java)

        activity_main_call_service_button.setOnClickListener {
            gridMemesViewModel.getMemesFromApi()
        }
        gridMemesViewModel.mainState.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(memesData: Event<Data<List<Meme>>>) {
        showMemes(memesData.peekContent().data)
    }

    private fun showMemes(memesData: List<Meme>?) {
        activity_main_first_meme_name.text = memesData?.get(DEFAULT_INT)?.name
    }
}