package com.me.employees.model

import com.google.gson.annotations.SerializedName

data class Employee (
    var id: Int = 0,
    @SerializedName("first_name") private var firstName: String = "N/A",
    @SerializedName("last_name") private var lastName: String = "N/A",
    private var gender: String = "N/A",
    @SerializedName("birth_date") var birthDate: String = "N/A",
    @SerializedName("thumbImage")var base64ThumbImageString: String? = null,
    @SerializedName("image") var base64FullImageString: String? = null
)  {
    val name: String get() = "$lastName,$firstName"
    val genderVal: String get() = gender.toUpperCase()

}