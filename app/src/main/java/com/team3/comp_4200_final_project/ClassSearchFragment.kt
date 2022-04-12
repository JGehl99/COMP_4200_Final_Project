package com.team3.comp_4200_final_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team3.comp_4200_final_project.db.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ClassSearchFragment : Fragment() {

    private lateinit var reView: RecyclerView       // Initializing reView
    private var arr: ArrayList<Course> = ArrayList()  // Creating ArrayList to hold Cards
    private lateinit var reAdapter: ClassSearchRecyclerAdapter // Initializing reAdapter

    private lateinit var searchBar: AutoCompleteTextView

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

        reAdapter = ClassSearchRecyclerAdapter(context, arr)
        reView.layoutManager = LinearLayoutManager(view.context)
        reView.adapter = reAdapter

        val service = Retrofit.Builder()
            .baseUrl("https://utable.net/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UTableService::class.java)

        var autofillStrings = mutableListOf<String>()
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
                var results = response.body()
                if (results != null) {
                    for (result in results) {
                        autofillStrings.add(result.courseCode)
                        autofillStrings.add(result.courseName)
                    }
                }
                //Log.d("TAG_", results.toString())
            }
        })

        var aa = ArrayAdapter<String>(view.context, android.R.layout.simple_dropdown_item_1line, autofillStrings)
        searchBar = view.findViewById(R.id.search_field)
        searchBar.setAdapter(aa)

        var searchButton : Button = view.findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            service.getSearchResult(searchBar.text.toString()).enqueue(object : Callback<List<CourseSearchResult>> {

                /* The HTTP call failed. This method is run on the main thread */
                override fun onFailure(call: Call<List<CourseSearchResult>>, t: Throwable) {
                    Log.d("TAG_", "An error happened!")
                    t.printStackTrace()
                }

                /* The HTTP call was successful, we should still check status code and response body
                 * on a production app. This method is run on the main thread */
                override fun onResponse(call: Call<List<CourseSearchResult>>, response: Response<List<CourseSearchResult>>) {
                    /* This will print the response of the network call to the Logcat */
                    var results = response.body()
                    if (results != null) {
                        arr.clear()
                        for(result in results) {
                            Log.d("RESPONSE_", result.toString())
                            for (section in result.sections) {
                                if (section.semester_id == 3) // set this to latest semester each time, IK its bad
                                // todo: make subsections better
                                if (section.subsections != null && section.subsections.isNotEmpty()) {
                                    var subsec = section.subsections[0]
                                    arr.add(Course(
                                        0,
                                        result.courseCode,
                                        result.courseName,
                                        if(subsec.startTime == null || subsec.endTime == null) "" else subsec.startTime + "-" + subsec.endTime,
                                        parseDays(subsec.day).toString().drop(1).dropLast(1),
                                        if(subsec.location == null)"" else subsec.location,
                                        subsec.professors.toString()
                                    ))
                                }
                            }
                        }
                        reAdapter.notifyDataSetChanged()
                    }
                }
            })
        }
    }
}

/* Kotlin data/model classes that map the JSON response, we could also add Moshi
 * annotations to help the compiler with the mappings on a production app */
data class AutofillData(val courseCode: String, val courseName: String, val courseProf: List<Prof>)
data class Prof(val firstName: String, val lastName: String)  {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}

data class CourseSearchResult(val courseCode: String, val courseName: String, val sections: List<Section>)

data class Section(val sectionNum: Int, val sectionType: String, val subsections: List<Subsec>, val semester_id: Int)
data class Subsec(val day: String, val startTime: String, val endTime: String, val location: String, val professors: List<Prof>)

/* Retrofit service that maps the different endpoints on the API, you'd create one
 * method per endpoint, and use the @Path, @Query and other annotations to customize
 * these at runtime */
interface UTableService {
    @GET("/api/autofill/")
    fun getCourses(): Call<List<AutofillData>>

    @GET("/api/search/?search")
    fun getSearchResult(
        @Query("search") search: String
    ) : Call<List<CourseSearchResult>>
}

// Function to parse passed in classDays string (formatted like: MWF, TWF, MTH, etc.)
fun parseDays(str_: String): ArrayList<String>{
    var str = str_
    val arr = ArrayList<String>()

    // Loop until str is empty, read first char, add day to list, then drop first char
    // Special cases for T and TH since TH is the only one with two chars
    while (str.isNotEmpty()){
        if (str[0] == 'M') {
            arr.add("Monday")
            str = str.drop(1)
            continue
        }
        if (str[0] == 'W') {
            arr.add("Wednesday")
            str = str.drop(1)
            continue
        }
        if (str[0] == 'F') {
            arr.add("Friday")
            str = str.drop(1)
            continue
        }
        if (str[0] == 'T') {
            if (str.length > 1 && str[1] == 'H') {
                arr.add("Thursday")
                str = str.drop(2)
                continue
            }
            arr.add("Tuesday")
            str = str.drop(1)
            continue
        }
        Log.e("TAG_", "String does not match any letters! $str")
        break
    }
    return arr
}