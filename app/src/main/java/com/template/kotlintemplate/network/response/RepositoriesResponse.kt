package com.template.kotlintemplate.network.response

import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(@SerializedName("total_count")
                                val totalCount: Int = 0,
                                @SerializedName("incomplete_results")
                                val incompleteResults: Boolean = false,
                                @SerializedName("items")
                                val items: List<Repository>)