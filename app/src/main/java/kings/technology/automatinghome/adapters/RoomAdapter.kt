package kings.technology.automatinghome.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kings.technology.automatinghome.R
import kings.technology.automatinghome.contracts.RoomContract
import kings.technology.automatinghome.models.RenderType
import kings.technology.automatinghome.models.ViewType

class RoomAdapter(private val roomListPresenter: RoomContract.Presenter,
                  private val type: ViewType,
                  private val renderType: RenderType)
    : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view: View? = null
        if (type == ViewType.GRID)
            view = inflater.inflate(R.layout.rv_item_grid, parent, false)
        else if (type == ViewType.LIST)
            view = inflater.inflate(R.layout.rv_item_list, parent, false)

        return RoomViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return roomListPresenter.getRoomCount()
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        roomListPresenter.onBindRoomAtPosition(position, holder)
    }

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            RoomContract.RoomItemView {

        val imageViewIcon: ImageView
        val textViewName: TextView
        val textViewType: TextView
        val textViewStatus: TextView

        init {
            imageViewIcon = itemView.findViewById(R.id.rv_item_icon) as ImageView
            textViewName = itemView.findViewById(R.id.rv_room_name) as TextView
            textViewType = itemView.findViewById(R.id.rv_room_type) as TextView
            textViewStatus = itemView.findViewById(R.id.rv_status) as TextView

            itemView.setOnClickListener {
                roomListPresenter.onItemClick(adapterPosition, renderType)
            }
        }

        override fun setRoomName(name: String) {
            textViewName.text = name
        }

        override fun setRoomType(type: String) {
            textViewType.text = type
        }

        override fun setRoomIcon(icon: Int) {
            imageViewIcon.setImageResource(icon)
        }

        override fun setRoomLightStatus(status: String) {
            textViewStatus.text = status
        }

    }

    fun updateData() {
        notifyDataSetChanged()
    }
}