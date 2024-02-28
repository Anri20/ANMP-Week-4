package com.example.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.advweek4.model.Student

class DetailViewModel : ViewModel() {
    val studentLD = MutableLiveData<Student>()

    fun fetch(){
        studentLD.value = Student(
            "14865",
            "Poor",
            "1995/11/15",
            "948644615",
            "https://th.bing.com/th/id/OIP.X3mfeI7lW-x1NvHx8AZwAAHaHa?rs=1&pid=ImgDetMain"
        )
    }
}