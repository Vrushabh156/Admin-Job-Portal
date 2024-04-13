package com.adminjob.adminjobapp.models

data class Job(
    var company: String = "",
    var name: String = "",
    var startDate: String = "",
    var summary: String = "",
    var description: String = "",
    var position: String = "",
    var vacancy: String = "",
    var category: String = "",
    var platform: String = "",
    var minExperience: String = "",
    var maxExperience: String = ""
)