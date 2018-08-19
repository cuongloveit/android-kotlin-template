package com.template.kotlintemplate.network.response

import com.google.gson.annotations.SerializedName

data class Repository(@SerializedName("owner")
                     val owner: Owner,
                      @SerializedName("full_name")
                     val fullName: String = "",
                      @SerializedName("name")
                     val name: String = "",
                      @SerializedName("id")
                     val id: Int = 0,
                      @SerializedName("node_id")
                     val nodeId: String = "")