package com.teamnk.kimiljung.feature.post

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentCalendarBinding
import com.teamnk.kimiljung.databinding.FragmentPostBinding
import com.teamnk.kimiljung.util.showShortSnackBar

class PostFragment : BaseFragment<FragmentPostBinding>(
    R.layout.fragment_post
) {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            PostViewModelFactory(
                PostRepository(),
                requireActivity().application,
            )
        )[PostViewModel::class.java]
    }

    override fun observeEvent() {
        viewModel.postListResponse.observe(
            viewLifecycleOwner
        ) {
            binding.rvFragmentPostMain.run {
                adapter = PostAdapter(
                    postList = it.body()!!.post_list
                )
                layoutManager = LinearLayoutManager(
                    requireActivity(),
                )
            }
        }
        viewModel.snackBarMessage.observe(
            viewLifecycleOwner
        ){
            showShortSnackBar(
                binding.root,
                it,
            )
        }

    }
}