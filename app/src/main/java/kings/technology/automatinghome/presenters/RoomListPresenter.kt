package kings.technology.automatinghome.presenters

import kings.technology.automatinghome.R
import kings.technology.automatinghome.contracts.RoomContract
import kings.technology.automatinghome.models.RenderType
import kings.technology.automatinghome.models.RoomModel
import kings.technology.automatinghome.models.RoomType

class RoomListPresenter(private val view: RoomContract.View) : RoomContract.Presenter {

    private var roomList: ArrayList<RoomModel>

    init {
        val roomTypeBedroom = RoomType("Bedroom", R.drawable.ic_bedroom)
        val roomTypeKitchen = RoomType("Kitchen", R.drawable.ic_kitchen)
        val roomTypeBathroom = RoomType("Bathroom", R.drawable.ic_bathroom)
        val roomTypeLiving = RoomType("Living", R.drawable.ic_living)
        val roomTypeDining = RoomType("Dining", R.drawable.ic_dinning)
        val roomTypeEmpty = RoomType("Empty", R.drawable.ic_empty)

        roomList = arrayListOf(
                RoomModel("Bedroom 1", R.drawable.bedroom_1, R.drawable.bedroom_1_2d, 1, roomTypeBedroom),
                RoomModel("Bedroom 2", R.drawable.bedroom_2, R.drawable.bedroom_2_2d, 2, roomTypeBedroom),
                RoomModel("Bedroom 3", R.drawable.bedroom_3, R.drawable.bedroom_3_2d, 3, roomTypeBedroom),
                RoomModel("Kitchen", R.drawable.kitchen, R.drawable.kitchen_2d, 4, roomTypeKitchen),
                RoomModel("Bathroom", R.drawable.bathroom, R.drawable.bathroom_2d, 5, roomTypeBathroom),
                RoomModel("Living Room", R.drawable.living, R.drawable.living_2d, 6, roomTypeLiving),
                RoomModel("Dining Room", R.drawable.sitting_area, R.drawable.dining_room_2d, 7, roomTypeDining),
                RoomModel("Empty Room 1", R.drawable.empty_1, R.drawable.empty_1_2d, 8, roomTypeEmpty),
                RoomModel("Empty Room 2", R.drawable.empty_2, R.drawable.empty_2_2d, 9, roomTypeEmpty)

        )
    }

    override fun onItemClick(position: Int, renderType: RenderType) {
        val room = roomList[position]
        val light = if (renderType == RenderType.RENDER_2D) room.image2d else room.image
        view.toggleLight(light, room.associatedImageView, position, room.isOn)
    }

    override fun onBindRoomAtPosition(position: Int, roomItemView: RoomContract.RoomItemView) {
        val roomModel = roomList[position]
        roomItemView.setRoomName(roomModel.name)
        roomItemView.setRoomType(roomModel.type.type)
        roomItemView.setRoomIcon(roomModel.type.icon)
        roomItemView.setRoomLightStatus(roomModel.isRoomOnOrOff())
    }

    override fun getRoomCount(): Int {
        return roomList.size
    }

    override fun setLigth(id: Int, position: Int, isOn: Boolean) {
        roomList[position].associatedImageView = id
        roomList[position].isOn = isOn
    }

    override fun clearLights() {
        for (room in roomList) {
            view.onRenderViewSwitch(room.associatedImageView)
            room.associatedImageView = -1

        }
    }


}