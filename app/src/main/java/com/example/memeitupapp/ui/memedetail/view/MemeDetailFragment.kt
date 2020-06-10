package com.example.memeitupapp.ui.memedetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.memeitupapp.R
import com.globant.domain.entity.MemeDetail
import com.example.memeitupapp.ui.memedetail.viewmodel.MemeDetailViewModel
import com.example.memeitupapp.util.Data
import com.example.memeitupapp.util.Event
import com.example.memeitupapp.util.Status
import kotlinx.android.synthetic.main.dialog_container_error.view.dialog_container_error_close_button
import kotlinx.android.synthetic.main.dialog_container_success.dialog_container_success_image_view_meme_image
import kotlinx.android.synthetic.main.dialog_container_success.dialog_container_success_text_view_name
import kotlinx.android.synthetic.main.dialog_container_success.dialog_container_success_text_view_origin_text
import kotlinx.android.synthetic.main.dialog_container_success.dialog_container_success_text_view_rank_text
import kotlinx.android.synthetic.main.dialog_container_success.dialog_container_success_text_view_tags_text
import kotlinx.android.synthetic.main.dialog_container_success.view.dialog_container_success_close_button
import kotlinx.android.synthetic.main.layout_fragment_meme_details.dialog_error
import kotlinx.android.synthetic.main.layout_fragment_meme_details.dialog_success
import kotlinx.android.synthetic.main.layout_fragment_meme_details.layout_fragment_meme_detail_progress_bar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MemeDetailFragment : DialogFragment() {

    private val memeDetailsViewModel by viewModel<MemeDetailViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.layout_fragment_meme_details, container, false)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        arguments?.getInt(MEME_ID)?.let {
            memeDetailsViewModel.fetchMeme(it)
        }
        memeDetailsViewModel.getLiveData().observe(::getLifecycle, ::updateUI)
        rootView.dialog_container_success_close_button.setOnClickListener { this.dismiss() }
        rootView.dialog_container_error_close_button.setOnClickListener { this.dismiss() }
        return rootView
    }

    private fun updateUI(meme: Event<Data<MemeDetail>>) {
        when (meme.peekContent().responseType) {
            Status.GET_MEME_BY_ID_ERROR -> {
                showErrorMsg(meme.peekContent().error?.message)
                dialog_error.visibility = View.VISIBLE
            }
            Status.LOADING -> {
                layout_fragment_meme_detail_progress_bar.visibility = View.VISIBLE
            }
            Status.GET_MEME_BY_ID_SUCCESS -> {
                showMemeDetails(meme.peekContent().data)
                dialog_success.visibility = View.VISIBLE
            }
        }
    }

    private fun showErrorMsg(errorMsg: String?) {
        layout_fragment_meme_detail_progress_bar.visibility = View.GONE
        errorMsg?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showMemeDetails(meme: MemeDetail?) {
        layout_fragment_meme_detail_progress_bar.visibility = View.GONE
        meme?.let {
            dialog_container_success_text_view_name.text = it.name
            if (it.origin == EMPTY_STRING) {
                dialog_container_success_text_view_origin_text.text = ORIGIN_NOT_FOUND
            } else {
                dialog_container_success_text_view_origin_text.text = it.origin
            }
            dialog_container_success_text_view_rank_text.text = it.rank.toString()
            dialog_container_success_text_view_tags_text.text = it.tags
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.imagenotavailable)
                .error(R.drawable.imagenotavailable)

            context?.let { it1 ->
                Glide.with(it1)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(it.image)
                    .into(dialog_container_success_image_view_meme_image)
            }
        }
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val ORIGIN_NOT_FOUND = "Origin not found"
        private const val MEME_ID = "meme_id"
        fun newInstance(meme_id: Int): MemeDetailFragment {
            val args = Bundle()
            args.putInt(MEME_ID, meme_id)
            val fragment = MemeDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}