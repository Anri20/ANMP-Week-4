package com.example.advweek4.view

import android.view.View
import com.example.advweek4.model.Student

interface StudentDetailClickListener {
    fun onStudentDetailClick(
        view: View,
        studentId: String,
        studentName: String,
        birthDate: String,
        phone: String,
        imgURL: String
    )
}

interface UpdateStudentListener {
    fun onUpdateStudentClick(view: View)
}

interface NotifStudentListener {
    fun onNotifClick(view: View, student: Student)
}