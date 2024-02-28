package com.example.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.model.Student
import com.squareup.picasso.Picasso
import javax.security.auth.callback.Callback

class StudentListAdapter(val studentList: ArrayList<Student>) :
    RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class StudentViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)

        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
//    override fun getItemCount() = studentList.size()

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtID).text = studentList[position].id
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
        }

        Picasso.get()
            .load(studentList[position].photoUrl)
            .into(holder.view.findViewById<ImageView>(R.id.imageView))
    }

    fun updateStudentList(newStudentList: ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}