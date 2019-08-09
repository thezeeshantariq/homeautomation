package kings.technology.automatinghome.contracts


import android.view.View
import kings.technology.automatinghome.models.RenderType
import kings.technology.automatinghome.models.RoomType


interface RoomContract {

    interface View {
        fun showRooms()
        fun toggleLight(id: Int, imageViewId: Int, position: Int, isOn: Boolean)
        fun onRenderViewSwitch(imageViewId: Int)
    }

    interface RoomItemView {
        fun setRoomName(name: String)
        fun setRoomType(type: String)
        fun setRoomIcon(icon: Int)
        fun setRoomLightStatus(status: String)
    }

    interface Presenter {
        fun onBindRoomAtPosition(position: Int, roomItemView: RoomItemView)
        fun getRoomCount(): Int
        fun onItemClick(position: Int, renderType: RenderType)
        fun setLigth(id: Int, position: Int, isOn: Boolean)
        fun clearLights()
    }
}