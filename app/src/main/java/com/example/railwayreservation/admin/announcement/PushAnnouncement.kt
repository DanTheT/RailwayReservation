package com.example.railwayreservation.admin.announcement

data class PushAnnouncement(
    val data: AnnouncementData,
    val to: String
)