package com.example.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.databinding.StudentListItemBinding
import com.example.advweek4.model.Student
import com.example.advweek4.util.loadImage
import com.squareup.picasso.Picasso

class StudentListAdapter(val studentList: ArrayList<Student>) :
    RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), StudentDetailClickListener {
    class StudentViewHolder(var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = StudentListItemBinding.inflate(inflater, parent, false)

        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
//    override fun getItemCount() = studentList.size()

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        with(holder.view){
            student = studentList[position]
            detailListener = this@StudentListAdapter
        }
        /*holder.view.findViewById<TextView>(R.id.txtID).text = studentList[position].id
        holder.view.findViewById<TextView>(R.id.txtName).text = studentList[position].name


        holder.view.findViewById<Button>(R.id.btnDetail).setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail(
                studentId = studentList[position].id.toString(),
                studentName = studentList[position].name.toString(),
                birthDate = studentList[position].bod.toString(),
                phone = studentList[position].phone.toString(),
                imgUrl = studentList[position].photoUrl.toString()
            )
            Navigation.findNavController(it).navigate(action)
        }*/

//        Picasso.get()
//            .load(studentList[position].photoUrl)
//            .into(holder.view.findViewById<ImageView>(R.id.imageStudentPhoto))

        /*holder.view.findViewById<ImageView>(R.id.imageStudentPhoto)
            .loadImage(studentList[position].photoUrl, holder.view.findViewById(R.id.progressBar))*/
    }

    fun updateStudentList(newStudentList: ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onStudentDetailClick(
        view: View,
        studentId: String,
        studentName: String,
        birthDate: String,
        phone: String,
        imgURL: String
    ) {
        val action = StudentListFragmentDirections.actionStudentDetail(studentId, studentName, birthDate, phone, imgURL)
        Navigation.findNavController(view).navigate(action)
    }
}