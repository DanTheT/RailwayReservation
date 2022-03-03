package com.example.railwayreservation.reportIssue

data class IssuesData(
    val issueCategory: String? = "",
    val issueDescription: String? = "",
    val issueDate: String? = "",
    val issueTime: String? = "",
    val issueResolve: String? = "",
    var trainId: String? = "-",
    var coachPick: String? = "-",
)
