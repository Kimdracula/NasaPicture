package com.homework.nasapicture.model
import com.google.gson.annotations.SerializedName
class EarthDTO : ArrayList<EarthDTOItem>()

data class EarthDTOItem(
    @SerializedName("attitude_quaternions")
    val attitudeQuaternions: AttitudeQuaternions,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("centroid_coordinates")
    val centroidCoordinates: CentroidCoordinates,
    @SerializedName("coords")
    val coords: Coords,
    @SerializedName("date")
    val date: String,
    @SerializedName("dscovr_j2000_position")
    val dscovrJ2000Position: DscovrJ2000PositionX,
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("lunar_j2000_position")
    val lunarJ2000Position: LunarJ2000PositionX,
    @SerializedName("sun_j2000_position")
    val sunJ2000Position: SunJ2000PositionX,
    @SerializedName("version")
    val version: String
)

data class AttitudeQuaternions(
    @SerializedName("q0")
    val q0: Double,
    @SerializedName("q1")
    val q1: Double,
    @SerializedName("q2")
    val q2: Double,
    @SerializedName("q3")
    val q3: Double
)

data class CentroidCoordinates(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)

data class Coords(
    @SerializedName("attitude_quaternions")
    val attitudeQuaternions: AttitudeQuaternionsX,
    @SerializedName("centroid_coordinates")
    val centroidCoordinates: CentroidCoordinatesX,
    @SerializedName("dscovr_j2000_position")
    val dscovrJ2000Position: DscovrJ2000Position,
    @SerializedName("lunar_j2000_position")
    val lunarJ2000Position: LunarJ2000Position,
    @SerializedName("sun_j2000_position")
    val sunJ2000Position: SunJ2000Position
)

data class DscovrJ2000PositionX(
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double,
    @SerializedName("z")
    val z: Double
)

data class LunarJ2000PositionX(
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double,
    @SerializedName("z")
    val z: Double
)

data class SunJ2000PositionX(
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double,
    @SerializedName("z")
    val z: Double
)

data class AttitudeQuaternionsX(
    @SerializedName("q0")
    val q0: Double,
    @SerializedName("q1")
    val q1: Double,
    @SerializedName("q2")
    val q2: Double,
    @SerializedName("q3")
    val q3: Double
)

data class CentroidCoordinatesX(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)

data class DscovrJ2000Position(
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double,
    @SerializedName("z")
    val z: Double
)

data class LunarJ2000Position(
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double,
    @SerializedName("z")
    val z: Double
)

data class SunJ2000Position(
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double,
    @SerializedName("z")
    val z: Double
)