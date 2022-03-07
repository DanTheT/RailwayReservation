package com.example.railwayreservation.reportIssue.updates

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelizedIssueData(
    val issueCategory: String? = "",
    val issueDescription: String? = "",
    val issueResolve: String? = "",
    var trainId: String? = "-",
    var coachPick: String? = "-",
    val issueDate: String? = "",
    val issueTime: String? = "",
): Parcelable
