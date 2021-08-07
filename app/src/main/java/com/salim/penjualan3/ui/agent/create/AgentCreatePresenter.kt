package com.salim.penjualan3.ui.agent.create

import com.salim.penjualan3.data.model.agent.ResponseAgentUpdate
import com.salim.penjualan3.network.ApiService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class   AgentCreatePresenter(val view: AgentCreateContract.View): AgentCreateContract.Presenter {
    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }
    override fun insertAgent(
        nama_toko: String,
        nama_pemilik: String,
        alamat: String,
        latitude: String,
        longitude: String,
        gambar_toko: File
    ) {
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), gambar_toko)
        val multipartBody: MultipartBody.Part? = MultipartBody.Part.createFormData("gambar_toko",
            gambar_toko.name, requestBody)

        view.onLoading(true)
        ApiService.endpoint.insertAgent(nama_toko, nama_pemilik, alamat, latitude, longitude, multipartBody!!)
            .enqueue(object : Callback<ResponseAgentUpdate> {
                override fun onFailure(call: Call<ResponseAgentUpdate>, t: Throwable) {
                    view.onLoading(false)
                }

                override fun onResponse(call: Call<ResponseAgentUpdate>,
                                        response: Response<ResponseAgentUpdate>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val responseAgent: ResponseAgentUpdate? = response.body()
                        view.onResult(responseAgent!!)
                    }
                }

            })
    }
}