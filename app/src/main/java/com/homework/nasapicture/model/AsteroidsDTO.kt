package com.homework.nasapicture.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class AsteroidsDTO(
    @SerializedName("element_count")
    val elementCount: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("near_earth_objects")
    val nearEarthObjects: NearEarthObjects
) : Parcelable
@Parcelize
data class Links(
    @SerializedName("next")
    val next: String,
    @SerializedName("prev")
    val prev: String,
    @SerializedName("self")
    val self: String
):Parcelable
@Parcelize
data class NearEarthObjects(
    @SerializedName("2015-09-07")
    val x20150907: List<X20150907>,
    @SerializedName("2015-09-08")
    val x20150908: List<X20150908>
):Parcelable
@Parcelize
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
    @SerializedName("name")
    val name: String,
    @SerializedName("nasa_jpl_url")
    val nasaJplUrl: String,
    @SerializedName("neo_reference_id")
    val neoReferenceId: String
):Parcelable
@Parcelize
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
    @SerializedName("name")
    val name: String,
    @SerializedName("nasa_jpl_url")
    val nasaJplUrl: String,
    @SerializedName("neo_reference_id")
    val neoReferenceId: String
):Parcelable
@Parcelize
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
    val orbitingBody: String
):Parcelable
@Parcelize
data class EstimatedDiameter(
    @SerializedName("kilometers")
    val kilometers: Kilometers

):Parcelable


@Parcelize
data class MissDistance(
    @SerializedName("astronomical")
    val astronomical: String,
    @SerializedName("kilometers")
    val kilometers: String,
    @SerializedName("lunar")
    val lunar: String,
    @SerializedName("miles")
    val miles: String
):Parcelable


@Parcelize
data class Kilometers(
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double,
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double
):Parcelable

@Parcelize
data class CloseApproachDataX(
    @SerializedName("close_approach_date")
    val closeApproachDate: String,
    @SerializedName("close_approach_date_full")
    val closeApproachDateFull: String,
    @SerializedName("epoch_date_close_approach")
    val epochDateCloseApproach: Long,
    @SerializedName("orbiting_body")
    val orbitingBody: String,

):Parcelable