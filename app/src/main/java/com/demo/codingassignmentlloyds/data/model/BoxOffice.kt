package com.demo.codingassignmentlloyds.data.model

import com.squareup.moshi.Json

data class BoxOffice (
  @Json(name = "budget")
  val budget: String?,
  @Json(name = "openingWeekendUSA")
  val openingWeekendUSA: String?,
  @Json(name = "grossUSA")
  val grossUSA: String?,
  @Json(name = "cumulativeWorldwideGross")
  val cumulativeWorldwideGross : String?
)