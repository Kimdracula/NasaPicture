import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class AsteroidsDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("codename")
    val codename: String,
    @SerializedName("closeApproachDate")
    val closeApproachDate: String,
    @SerializedName("absoluteMagnitude")
    val absoluteMagnitude: Double,
    @SerializedName("estimatedDiameter")
    val estimatedDiameter: Double,
    @SerializedName("relativeVelocity")
    val relativeVelocity: Double,
    @SerializedName("distanceFromEarth")
    val distanceFromEarth: Double,
    @SerializedName("isPotentiallyHazardous")
    val isPotentiallyHazardous: Boolean
) : Parcelable