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

class ListViewModel(application: Application) : AndroidViewModel(application) {
    val studentLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val LoadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh() {
//        studentLD.value = arrayListOf(
//            Student(
//                "16055",
//                "Nonie",
//                "1998/03/28",
//                "5718444778",
//                "https://i.pinimg.com/originals/62/8c/01/628c01e4b8321396e45d812a871ffd26.jpg"
//            ),
//            Student(
//                "13312",
//                "Rich",
//                "1994/12/14",
//                "3925444073",
//                "https://th.bing.com/th/id/OIP.nBBABfTYuzj2DrsaqZ7pJgAAAA?rs=1&pid=ImgDetMain"
//            ),
//            Student(
//                "11204",
//                "Dinny",
//                "1994/10/07",
//                "6827808747",
//                "https://i.pinimg.com/originals/e8/33/43/e83343f654a670939e13fb38b15782e6.jpg"
//            )
//        )
        studentLoadErrorLD.value = false
        LoadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Student>>() {}.type
                val result = Gson().fromJson<ArrayList<Student>>(it, sType)
                studentLD.value = result
//                if success, set loading progress live data to false
                LoadingLD.value = false
                Log.d("showvolley", result.toString())
            },
            {
//                if failed, show error message, set error live data to true and progress live data to false
                Log.d("showvolley", it.toString())
                studentLoadErrorLD.value = true
                LoadingLD.value = false
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