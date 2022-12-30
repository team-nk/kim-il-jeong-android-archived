package com.teamnk.kimiljung.feature.post

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamnk.kimiljung.R
import com.teamnk.kimiljung.base.BaseFragment
import com.teamnk.kimiljung.databinding.FragmentPostBinding
import com.teamnk.kimiljung.util.Adapter
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
                adapter = Adapter(
                    postList = it.body()!!.post_list,
                    commentList = arrayListOf(),
                    temp = 1,
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