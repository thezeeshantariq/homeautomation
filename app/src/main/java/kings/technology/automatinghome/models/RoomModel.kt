package kings.technology.automatinghome.models

import android.widget.ImageView

data class RoomModel(
        val name: String,
        val image: Int,
        val image2d: Int,
        val number: Int,
        val type: RoomType,
        var isOn: Boolean = false,
        var associatedImageView: Int = -1
) {
    fun isRoomOnOrOff(): String {
        return if (isOn) "ON" else "OFF"
    }
}