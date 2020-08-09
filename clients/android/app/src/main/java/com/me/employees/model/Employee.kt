package com.me.employees.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class Employee (
    @SerializedName("first_name") private var firstName: String = "N/A",
    @SerializedName("last_name") private var lastName: String = "N/A",
    private var gender: String = "N/A",
    @SerializedName("birth_date") var birthDate: String = "N/A",
    @SerializedName("thumbImage")var base64ImageString: String = "N/A"
){
    val name: String get() = "$lastName,$firstName"
    val genderVal: String get() = gender.toUpperCase()

}