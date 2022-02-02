package com.anas.cleanarchitecturetestlab.firebase.presention.postList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.anas.cleanarchitecturetestlab.R
import com.anas.cleanarchitecturetestlab.databinding.ActivityMainBinding
import com.anas.cleanarchitecturetestlab.databinding.FragmentPostListBinding
import com.anas.cleanarchitecturetestlab.databinding.PostRawItemBinding
import com.anas.cleanarchitecturetestlab.firebase.domain.models.Post
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "PostListFragment"
@AndroidEntryPoint
class PostListFragment : Fragment() {

    companion object {
        fun newInstance() = PostListFragment()
    }

    private  val viewModel: PostListViewModel by viewModels()

    private lateinit var mainBinding: FragmentPostListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPostsList()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainBinding = FragmentPostListBinding.inflate(layoutInflater,container,false)
        mainBinding.rv.layoutManager = LinearLayoutManager(requireContext())

        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.postLiveData.observe(viewLifecycleOwner){
            Log.d(TAG, "onViewCreated: $it")
            mainBinding.rv.adapter = PostsAdapter(it)
        }


    }

    private inner class PostsHolder(val binding: PostRawItemBinding):RecyclerView.ViewHolder(binding.root){


        fun bind(post: Post){
            binding.decriptionRaw.text = post.description
            binding.titleRaw.text = post.title
            binding.picItem.load(post.pic)
        }
    }

    private inner class PostsAdapter(val posts : List<Post>)
        : RecyclerView.Adapter<PostsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsHolder {
            val binding = PostRawItemBinding.inflate(layoutInflater,
            parent,
            false)

            return PostsHolder(binding)
        }

        override fun onBindViewHolder(holder: PostsHolder, position: Int) {
          val  post = posts[position]
           holder.bind(post)

        }

        override fun getItemCount(): Int = posts.size
    }

}