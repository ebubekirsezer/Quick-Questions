package com.ess.quickquestions.ui.homeview

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ess.quickquestions.R
import kotlinx.android.synthetic.main.category_card_view.view.*

class CategoryCardListAdapter(private val postList: ArrayList<String>) : RecyclerView.Adapter<CategoryCardListAdapter.RowHolder>() {

    private val cardColors : Array<Int> = arrayOf(Color.MAGENTA,Color.GREEN,Color.YELLOW,Color.RED,Color.BLUE)

    class RowHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(title: String,color : Int){
            itemView.category_card_title.text = title
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
