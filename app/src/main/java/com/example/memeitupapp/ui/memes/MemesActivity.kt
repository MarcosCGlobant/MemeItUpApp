package com.example.memeitupapp.ui.memes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.memeitupapp.R
import com.example.memeitupapp.data.entity.Meme
import com.example.memeitupapp.util.DEFAULT_INT
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import kotlinx.android.synthetic.main.activity_main.activity_main_call_service_button
import kotlinx.android.synthetic.main.activity_main.activity_main_first_meme_id

class MemesActivity : AppCompatActivity() {

    private lateinit var memeItUpViewModel: MemeItUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        memeItUpViewModel = MemeItUpViewModel()

        activity_main_call_service_button.setOnClickListener {
            memeItUpViewModel.getMemesFromApi()
        }
        memeItUpViewModel.mainState.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(memesData: Event<Data<List<Meme>>>) {
        showMemes(memesData.peekContent().data)
    }

    private fun showMemes(memesData: List<Meme>?) {
        activity_main_first_meme_id.text = memesData?.get(DEFAULT_INT)?.name
    }
}