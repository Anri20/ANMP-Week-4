package com.example.advweek4.view

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advweek4.R
import com.example.advweek4.util.loadImage
import com.example.advweek4.viewmodel.DetailViewModel
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class StudentDetailFragment : Fragment() {
    private lateinit var view: View
    private lateinit var imageView2: ImageView
    private lateinit var txtID: TextInputEditText
    private lateinit var txtName: TextInputEditText
    private lateinit var txtBod: TextInputEditText
    private lateinit var txtPhone: TextInputEditText

    private lateinit var detailViewModel: DetailViewModel

    private lateinit var student_id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_detail, container, false)

        imageView2 = view.findViewById(R.id.imageView2)
        txtID = view.findViewById(R.id.txtID)
        txtName = view.findViewById(R.id.txtName)
        txtBod = view.findViewById(R.id.txtBod)
        txtPhone = view.findViewById(R.id.txtPhone)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)

//        arguments.let {
//            txtID.setText(StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId)
//            txtName.setText(StudentDetailFragmentArgs.fromBundle(requireArguments()).studentName)
//            txtBod.setText(StudentDetailFragmentArgs.fromBundle(requireArguments()).birthDate)
//            txtPhone.setText(StudentDetailFragmentArgs.fromBundle(requireArguments()).phone)
//
//            Picasso.get()
//                .load(StudentDetailFragmentArgs.fromBundle(requireArguments()).imgUrl)
//                .into(imageView2)
//        }

        arguments.let{
            student_id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
        }

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch(student_id = student_id)

        observeViewModel()
    }

    private fun observeViewModel() {
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            txtID.setText(it.id)
            txtName.setText(it.name)
            txtBod.setText(it.bod)
            txtPhone.setText(it.phone)

            Picasso.get()
                .load(it.photoUrl)
                .into(imageView2)
        })
    }

}