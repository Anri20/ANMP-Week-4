package com.example.advweek4.view

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.advweek4.R
import com.example.advweek4.databinding.FragmentStudentDetailBinding
import com.example.advweek4.model.Student
import com.example.advweek4.util.loadImage
import com.example.advweek4.viewmodel.DetailViewModel
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), UpdateStudentListener, NotifStudentListener {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding

    private lateinit var student_id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentStudentDetailBinding.inflate(inflater, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.updateListener = this
        dataBinding.notifListener = this

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

        arguments.let {
            student_id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
        }

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch(student_id = student_id)

        observeViewModel()
    }

    private fun observeViewModel() {
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it
//            txtID.setText(it.id)
//            txtName.setText(it.name)
//            txtBod.setText(it.bod)
//            txtPhone.setText(it.phone)
//
//            Picasso.get()
//                .load(it.photoUrl)
//                .into(imageView2)
//
//            var student = it
//
//            btnNotif.setOnClickListener {
//                Observable.timer(3, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("delay", "five seconds delay")
//                        MainActivity.showNotification(
//                            student.name.toString(),
//                            "Notification created for ${student.name.toString()}",
//                            R.drawable.baseline_person_24
//                        )
//                    }
//            }
        })
    }

    override fun onUpdateStudentClick(view: View) {
        Toast.makeText(view.context, "Student Detail Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }

    override fun onNotifClick(view: View, student: Student) {
        Observable.timer(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("delay", "three seconds delay")
                MainActivity.showNotification(
                    student.name.toString(),
                    "Notification created for ${student.name.toString()}",
                    R.drawable.baseline_person_24
                )
            }
    }
}