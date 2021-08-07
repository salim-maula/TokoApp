package com.salim.penjualan3.network

import com.salim.penjualan3.data.model.agent.ResponseAgentDetail
import com.salim.penjualan3.data.model.agent.ResponseAgentList
import com.salim.penjualan3.data.model.agent.ResponseAgentUpdate
import com.salim.penjualan3.data.model.login.ResponseLogin
import com.salim.penjualan3.data.model.transaction.ResponseTransactionList
import com.salim.penjualan3.data.model.transaction.detail.ResponseTransactionDetail
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {

    @FormUrlEncoded
    @POST("login_pegawai")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") description: String
    ) : Call<ResponseLogin>

    @GET("agen")
    fun getAgent(): Call<ResponseAgentList>

    @Multipart
    @POST("agen")
    fun insertAgent(
        @Query("nama_toko") nama_toko: String,
        @Query("nama_pemilik") nama_pemilik: String,
        @Query("alamat") alamat: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part gambar_toko: MultipartBody.Part
    ): Call<ResponseAgentUpdate>

    @GET("agen/{kd_agen}")
    fun getAgentDetail(
            @Path("kd_agen") kd_agen: Long
    ): Call<ResponseAgentDetail>

    @Multipart
    @POST("agen/{kd_agen}")
    fun updateAgent(
            @Path("kd_agen") kd_agen: Long,
            @Query("nama_toko") nama_toko: String,
            @Query("nama_pemilik") nama_pemilik: String,
            @Query("alamat") alamat: String,
            @Query("latitude") latitude: String,
            @Query("longitude") longitude: String,
            @Part gambar_toko: MultipartBody.Part?,
            @Query("_method") _method: String
    ): Call<ResponseAgentUpdate>

    @DELETE("agen/{kd_agen}")
    fun deleteAgent(
            @Path("kd_agen") kd_agen: Long
    ): Call<ResponseAgentUpdate>

    @FormUrlEncoded
    @POST("get_transaksi")
    fun getTransactionByUsername(
            @Field("username") username: String
    ): Call<ResponseTransactionList>

    @FormUrlEncoded
    @POST("get_detail_transaksi")
    fun getTransactionByInvoice(
            @Field("no_faktur") no_faktur: String
    ): Call<ResponseTransactionDetail>

}























