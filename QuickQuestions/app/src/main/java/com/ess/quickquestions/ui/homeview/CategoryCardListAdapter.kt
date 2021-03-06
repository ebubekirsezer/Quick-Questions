package com.ess.quickquestions.ui.homeview

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ess.quickquestions.R
import com.ess.quickquestions.model.CategoryX
import kotlinx.android.synthetic.main.category_card_view.view.*

class CategoryCardListAdapter(private val postList: ArrayList<CategoryX>) : RecyclerView.Adapter<CategoryCardListAdapter.RowHolder>() {

    private val cardColors : Array<Int> = arrayOf(Color.RED,Color.MAGENTA,Color.BLUE,Color.GREEN,Color.DKGRAY)

    class RowHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(model: CategoryX,color : Int){
            itemView.category_card_title.text = model.name
            itemView.category_card.setCardBackgroundColor(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_card_view,parent,false)

        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        var colorPosition = position % 5

        holder.bind(postList[position],cardColors[colorPosition])
    }
}
