package com.demo.codingassignmentlloyds.data.model

import com.squareup.moshi.Json

data class IDNameData (
  @Json(name = "id")
  val id: String?,
  @Json(name = "name")
  val name: String?
)