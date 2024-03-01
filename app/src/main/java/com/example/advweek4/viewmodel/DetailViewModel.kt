package com.example.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advweek4.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(student_id: String) {
//        studentLD.value = Student(
//            "14865",
//            "Poor",
//            "1995/11/15",
//            "948644615",
//            "https://th.bing.com/th/id/OIP.X3mfeI7lW-x1NvHx8AZwAAHaHa?rs=1&pid=ImgDetMain"
//        )

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=$student_id"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Student>() {}.type
                val result = Gson().fromJson<Student>(it, sType)
                studentLD.value = result
//                if success, set loading progress live data to false
                Log.d("showvolley", result.toString())
            },
            {
//                if failed, show error message, set error live data to true and progress live data to false
                Log.d("showvolley", it.toString())
            }
        ).apply {
//            this.tag = TAG
            tag = TAG
            queue?.add(this)
        }
    }

    override fun onCleared() {
        super.onCleared()

        queue?.cancelAll(TAG)
    }
}