package com.example.railwayreservation.admin.announcement

import com.example.railwayreservation.admin.announcement.ApiConstants.Companion.CONTENT_TYPE
import com.example.railwayreservation.admin.announcement.ApiConstants.Companion.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AnnouncementAPI {
    @Headers("Authorization: key=$SERVER_KEY", "Content=Type: $CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postAnnouncement (
        @Body notification: PushAnnouncement
    ): Response<ResponseBody>
}