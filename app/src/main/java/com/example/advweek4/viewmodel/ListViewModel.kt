package com.example.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.advweek4.model.Student

class ListViewModel : ViewModel() {
    val studentLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val LoadingLD = MutableLiveData<Boolean>()

    fun refresh() {
        studentLD.value = arrayListOf(
            Student(
                "16055",
                "Nonie",
                "1998/03/28",
                "5718444778",
                "https://i.pinimg.com/originals/62/8c/01/628c01e4b8321396e45d812a871ffd26.jpg"
            ),
            Student(
                "13312",
                "Rich",
                "1994/12/14",
                "3925444073",
                "https://i.pinimg.com/originals/62/8c/01/628c01e4b8321396e45d812a871ffd26.jpg"
            ),
            Student(
                "11204",
                "Dinny",
                "1994/10/07",
                "6827808747",
                "https://i.pinimg.com/originals/62/8c/01/628c01e4b8321396e45d812a871ffd26.jpg"
            )
        )
        studentLoadErrorLD.value = false
        LoadingLD.value = false
    }
}