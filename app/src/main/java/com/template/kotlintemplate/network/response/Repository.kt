package com.template.kotlintemplate.network.response

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Repository(
    @SerializedName("full_name")
    var fullName: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("id")
    @PrimaryKey
    var id: Int = 0,
    @SerializedName("node_id")
    var nodeId: String = "") {
}
