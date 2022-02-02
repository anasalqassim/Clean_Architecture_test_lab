package com.anas.cleanarchitecturetestlab.firebase.presention.addPost

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.anas.cleanarchitecturetestlab.R
import com.anas.cleanarchitecturetestlab.databinding.ActivityMainBinding
import com.anas.cleanarchitecturetestlab.databinding.FragmentAddPostBinding
import com.anas.cleanarchitecturetestlab.firebase.data.remote.PostDto
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


private const val TAG = "AddPostFragment"
@AndroidEntryPoint
class AddPostFragment : Fragment() {

    companion object {
        fun newInstance() = AddPostFragment()
    }

    private val viewModel: AddPostViewModel by viewModels()

    private lateinit var imageUri: Uri
    private lateinit var imageFile: File

    private lateinit var mainBinding: FragmentAddPostBinding

    private val camLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()){
        if (it){
            mainBinding.pic.setImageURI(imageUri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainBinding = FragmentAddPostBinding.inflate(layoutInflater, container,false)

        imageFile = File(requireContext().applicationContext.filesDir,"sss.jpg")

        imageUri = FileProvider.getUriForFile(requireContext(),
        "com.anas.cleanarchitecturetestlab",
        imageFile
        )

        mainBinding.pic.setOnClickListener {
            camLauncher.launch(imageUri)
        }

        mainBinding.sendData.setOnClickListener {
            val title = mainBinding.titleText.editText?.text.toString()
            val description = mainBinding.descriptionText.editText?.text.toString()



            val postDto = PostDto(
                title = title,
                pic = "",
                ownerId = Firebase.auth.currentUser?.uid!!,
                description = description,
                isPublished = true
            )

            addPost(postDto,imageUri).observe(viewLifecycleOwner){
                Log.d(TAG, "onCreateView: the result is $it")
            }

        }

        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    fun addPost(postDto: PostDto,imageUri: Uri?):LiveData<Boolean> = viewModel.addPost(postDto,imageUri)

}