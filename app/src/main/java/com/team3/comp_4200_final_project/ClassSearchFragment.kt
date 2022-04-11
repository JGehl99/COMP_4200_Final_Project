package com.team3.comp_4200_final_project

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class ClassSearchFragment : Fragment() {

    private lateinit var reView: RecyclerView       // Initializing reView
    private var arr: ArrayList<ClassData> = ArrayList()  // Creating ArrayList to hold Cards
    private lateinit var reAdapter: RecyclerAdapter // Initializing reAdapter

    private lateinit var searchBar: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_class_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reView = view.findViewById(R.id.recycler_view)

        reAdapter = RecyclerAdapter(context, arr)
        reView.layoutManager = LinearLayoutManager(view.context)
        reView.adapter = reAdapter

        val service = Retrofit.Builder()
            .baseUrl("https://utable.net/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UserService::class.java)

        service.getCourses().enqueue(object : Callback<List<AutofillData>> {

            /* The HTTP call failed. This method is run on the main thread */
            override fun onFailure(call: Call<List<AutofillData>>, t: Throwable) {
                Log.d("TAG_", "An error happened!")
                t.printStackTrace()
            }

            /* The HTTP call was successful, we should still check status code and response body
             * on a production app. This method is run on the main thread */
            override fun onResponse(call: Call<List<AutofillData>>, response: Response<List<AutofillData>>) {
                /* This will print the response of the network call to the Logcat */
                Log.d("TAG_", response.body().toString())
            }
        })

        searchBar = view.findViewById(R.id.search_field)
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                Log.d("TAG_", p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }
        })
    }
}

/* Kotlin data/model classes that map the JSON response, we could also add Moshi
 * annotations to help the compiler with the mappings on a production app */
data class AutofillData(val courseCode: String, val courseName: String)

/* Retrofit service that maps the different endpoints on the API, you'd create one
 * method per endpoint, and use the @Path, @Query and other annotations to customize
 * these at runtime */
interface UserService {
    @GET("/api/autofill/")
    fun getCourses(): Call<List<AutofillData>>
}