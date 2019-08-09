package kings.technology.automatinghome.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kings.technology.automatinghome.R
import kings.technology.automatinghome.adapters.RoomAdapter
import kings.technology.automatinghome.contracts.RoomContract
import kings.technology.automatinghome.models.RenderType
import kings.technology.automatinghome.models.ViewType
import kings.technology.automatinghome.presenters.RoomListPresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), RoomContract.View {


    private lateinit var mPresenter: RoomContract.Presenter
    private lateinit var mRoomAdapter: RoomAdapter
    private var mCurrentRenderType = RenderType.RENDER_3D
    private var mCurrentViewType = ViewType.GRID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter = RoomListPresenter(this)

        showHouseAs(RenderType.RENDER_3D)
        showRooms()

        buttonListView.setOnClickListener {
            mCurrentViewType = ViewType.LIST
            showRooms()
        }

        buttonGridView.setOnClickListener {
            mCurrentViewType = ViewType.GRID
            showRooms()
        }

        button2DView.setOnClickListener {
            showHouseAs(RenderType.RENDER_2D)
            showRooms()
        }

        button3DView.setOnClickListener {
            showHouseAs(RenderType.RENDER_3D)
            showRooms()
        }

    }

    override fun showRooms() {
        mRoomAdapter = RoomAdapter(
                mPresenter,
                mCurrentViewType,
                mCurrentRenderType
        )

        if (mCurrentViewType == ViewType.GRID) {
            rv_room.layoutManager = GridLayoutManager(this, 3)
            buttonGridView.visibility = View.GONE
            buttonListView.visibility = View.VISIBLE
        } else {
            rv_room.layoutManager = LinearLayoutManager(this)
            buttonGridView.visibility = View.VISIBLE
            buttonListView.visibility = View.GONE
        }

        rv_room.adapter = mRoomAdapter

    }

    override fun toggleLight(id: Int, imageViewId: Int, position: Int, isOn: Boolean) {
        if (imageViewId == -1) {
            val imageView = ImageView(this)
            imageView.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            )

            imageView.setImageResource(id)
            val generatedId = ViewCompat.generateViewId()
            imageView.id = generatedId

            frameLayoutImageContainer.addView(imageView)

            mPresenter.setLigth(generatedId, position, true)
        } else {
            val iv = findViewById<ImageView>(imageViewId)
            if (isOn) {
                iv.visibility = View.GONE
                mPresenter.setLigth(imageViewId, position, false)
            } else {
                iv.visibility = View.VISIBLE
                mPresenter.setLigth(imageViewId, position, true)
            }

        }

        mRoomAdapter.updateData()
    }

    override fun onRenderViewSwitch(imageViewId: Int) {
        val iv = findViewById<ImageView>(imageViewId)
        frameLayoutImageContainer.removeView(iv)
    }

    private fun showHouseAs(renderType: RenderType) {
        mPresenter.clearLights()
        mCurrentRenderType = renderType

        when (renderType) {
            RenderType.RENDER_2D -> {
                iv_house.setImageResource(R.drawable.full_2d)

                button2DView.visibility = View.GONE
                button3DView.visibility = View.VISIBLE
            }

            RenderType.RENDER_3D -> {
                iv_house.setImageResource(R.drawable.house)

                button2DView.visibility = View.VISIBLE
                button3DView.visibility = View.GONE
            }
        }

    }

}

