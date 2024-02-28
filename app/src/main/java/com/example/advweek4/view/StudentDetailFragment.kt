package com.example.advweek4.view

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.advweek4.R
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class StudentDetailFragment : Fragment() {
    private lateinit var view: View
    private lateinit var imageView2: ImageView
    private lateinit var txtID: TextInputEditText
    private lateinit var txtName: TextInputEditText
    private lateinit var txtBod: TextInputEditText
    private lateinit var txtPhone: TextInputEditText

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
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            txtID.text = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId.toEditable()
            txtName.text = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentName.toEditable()
            txtBod.text = StudentDetailFragmentArgs.fromBundle(requireArguments()).birthDate.toEditable()
            txtPhone.text = StudentDetailFragmentArgs.fromBundle(requireArguments()).phone.toEditable()

            Picasso.get()
                .load(StudentDetailFragmentArgs.fromBundle(requireArguments()).imgUrl)
                .into(imageView2)
        }
    }

    fun String.toEditable() : Editable = Editable.Factory.getInstance().newEditable(this)
}