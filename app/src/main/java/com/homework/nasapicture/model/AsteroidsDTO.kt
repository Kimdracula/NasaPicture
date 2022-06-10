package com.homework.nasapicture.model
import com.google.gson.annotations.SerializedName


data class AsteroidsDTO(
    @SerializedName("element_count")
    val elementCount: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("near_earth_objects")
    val nearEarthObjects: NearEarthObjects
)

data class Links(
    @SerializedName("next")
    val next: String,
    @SerializedName("prev")
    val prev: String,
    @SerializedName("self")
    val self: String
)

data class NearEarthObjects(
    @SerializedName("2015-09-07")
    val x20150907: List<X20150907>,
    @SerializedName("2015-09-08")
    val x20150908: List<X20150908>
)

data class X20150907(
    @SerializedName("absolute_magnitude_h")
    val absoluteMagnitudeH: Double,
    @SerializedName("close_approach_data")
    val closeApproachData: List<CloseApproachData>,
    @SerializedName("estimated_diameter")
    val estimatedDiameter: EstimatedDiameter,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_potentially_hazardous_asteroid")
    val isPotentiallyHazardousAsteroid: Boolean,
    @SerializedName("is_sentry_object")
    val isSentryObject: Boolean,
    @SerializedName("links")
    val links: LinksX,
    @SerializedName("name")
    val name: String,
    @SerializedName("nasa_jpl_url")
    val nasaJplUrl: String,
    @SerializedName("neo_reference_id")
    val neoReferenceId: String
)

data class X20150908(
    @SerializedName("absolute_magnitude_h")
    val absoluteMagnitudeH: Double,
    @SerializedName("close_approach_data")
    val closeApproachData: List<CloseApproachDataX>,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_potentially_hazardous_asteroid")
    val isPotentiallyHazardousAsteroid: Boolean,
    @SerializedName("is_sentry_object")
    val isSentryObject: Boolean,
    @SerializedName("links")
    val links: LinksX,
    @SerializedName("name")
    val name: String,
    @SerializedName("nasa_jpl_url")
    val nasaJplUrl: String,
    @SerializedName("neo_reference_id")
    val neoReferenceId: String
)

data class CloseApproachData(
    @SerializedName("close_approach_date")
    val closeApproachDate: String,
    @SerializedName("close_approach_date_full")
    val closeApproachDateFull: String,
    @SerializedName("epoch_date_close_approach")
    val epochDateCloseApproach: Long,
    @SerializedName("miss_distance")
    val missDistance: MissDistance,
    @SerializedName("orbiting_body")
    val orbitingBody: String,
    @SerializedName("relative_velocity")
    val relativeVelocity: RelativeVelocity
)

data class EstimatedDiameter(
    @SerializedName("feet")
    val feet: Feet,
    @SerializedName("kilometers")
    val kilometers: Kilometers,
    @SerializedName("meters")
    val meters: Meters,
    @SerializedName("miles")
    val miles: Miles
)

data class LinksX(
    @SerializedName("self")
    val self: String
)

data class MissDistance(
    @SerializedName("astronomical")
    val astronomical: String,
    @SerializedName("kilometers")
    val kilometers: String,
    @SerializedName("lunar")
    val lunar: String,
    @SerializedName("miles")
    val miles: String
)

data class RelativeVelocity(
    @SerializedName("kilometers_per_hour")
    val kilometersPerHour: String,
    @SerializedName("kilometers_per_second")
    val kilometersPerSecond: String,
    @SerializedName("miles_per_hour")
    val milesPerHour: String
)

data class Feet(
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double,
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double
)

data class Kilometers(
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double,
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double
)

data class Meters(
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double,
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double
)

data class Miles(
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double,
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double
)

data class CloseApproachDataX(
    @SerializedName("close_approach_date")
    val closeApproachDate: String,
    @SerializedName("close_approach_date_full")
    val closeApproachDateFull: String,
    @SerializedName("epoch_date_close_approach")
    val epochDateCloseApproach: Long,
    @SerializedName("orbiting_body")
    val orbitingBody: String,

)