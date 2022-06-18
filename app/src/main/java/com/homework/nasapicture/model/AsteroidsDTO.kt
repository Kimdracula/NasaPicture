import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class AsteroidsDTO(
    @SerializedName("absolute_magnitude_h")
    val absoluteMagnitudeH: Double,
    @SerializedName("close_approach_data")
    val closeApproachData: CloseApproachData,
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
) : Parcelable

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
    val orbitingBody: String,
    @SerializedName("relative_velocity")
    val relativeVelocity: RelativeVelocity
) : Parcelable

@Parcelize
data class EstimatedDiameter(
    @SerializedName("kilometers")
    val kilometers: Kilometers,
    @SerializedName("meters")
    val meters: Meters,
    @SerializedName("miles")
    val miles: Miles
) : Parcelable


@Parcelize
data class MissDistance(
    @SerializedName("kilometers")
    val kilometers: String,
) : Parcelable

@Parcelize
data class RelativeVelocity(
    @SerializedName("kilometers_per_hour")
    val kilometersPerHour: String,
    @SerializedName("kilometers_per_second")
    val kilometersPerSecond: String,
) : Parcelable

@Parcelize
data class Kilometers(
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double,
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double
) : Parcelable

@Parcelize
data class Meters(
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double,
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double
) : Parcelable

@Parcelize
data class Miles(
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double,
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double
) : Parcelable

