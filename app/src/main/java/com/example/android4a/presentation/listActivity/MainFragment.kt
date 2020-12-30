package com.example.android4a.presentation.listActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.android4a.R
import com.example.android4a.data.remote.WikiApi
import com.example.android4a.data.remote.remote.Pages
import com.example.android4a.data.remote.remote.RestApiResponse
import com.example.android4a.domain.entity.User
import com.example.android4a.presentation.createAccount.AccountError
import com.example.android4a.presentation.createAccount.AccountSuccess
import kotlinx.android.synthetic.main.activity_second.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {

    val secondViewModel : SecondViewModel by inject()

    var email: String = "ezafdczeafa"

    fun apiCall(user: User){

        var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://fr.wikipedia.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service  = retrofit.create(WikiApi::class.java)

        val apiRequest = service.getApiResponse("https://fr.wikipedia.org/w/api.php?action=query&format=json&prop=coordinates%7Cpageimages&meta=&generator=geosearch&formatversion=2&colimit=30&piprop=thumbnail%7Cname&ggscoord=" + user.latitude + "|" + user.longitude +"&ggsradius=1000&ggslimit=30")

        apiRequest.enqueue(object : Callback<RestApiResponse> {
            override fun onResponse(call: Call<RestApiResponse>, response: Response<RestApiResponse>) {
                if (response.isSuccessful && response.body() != null) {

                    val apiResponse: RestApiResponse? = response.body()

                    val wikiList: List<Pages> = apiResponse!!.query.pages

                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = ListAdapter(wikiList)
                    }

                } else {
                    error("KO")
                }
            }

            override fun onFailure(call: Call<RestApiResponse>, t: Throwable) {
                error("KO")
            }
        })


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.activity_second, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("key")?.let {
            email = it
        }
        var user = User("","","","")

        secondViewModel.accountLiveData.observe(viewLifecycleOwner,  {
            when (it) {
                is AccountSuccess -> {

                    user.email = it.email
                    user.password = it.password
                    user.latitude = it.latitude
                    user.longitude = it.longitude

                    apiCall(user)

                }
                AccountError -> {

                }
            }
        })

        secondViewModel.getUser(email)

    }

    companion object {
        fun newInstance(email :String?): MainFragment = MainFragment().apply {
            arguments = Bundle().apply {
                putString("key", email)
            }
        }
    }
}