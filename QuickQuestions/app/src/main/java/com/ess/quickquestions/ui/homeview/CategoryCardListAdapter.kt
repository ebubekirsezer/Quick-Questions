package com.ess.quickquestions.ui.homeview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ess.quickquestions.R
import kotlinx.android.synthetic.main.category_card_view.view.*

class CategoryCardListAdapter(private val postList: ArrayList<String>) : RecyclerView.Adapter<CategoryCardListAdapter.RowHolder>() {

    private val images: Array<String> = arrayOf("https://www.dreamofholiday.com/userFiles/bolgeler/950205793_1588435982.jpg",
        "https://www.costacruises.com/content/dam/costa/inventory-assets/ports/IST/ist-istanbul-port-2.jpg.image.750.563.low.jpg",
        "https://im.haberturk.com/2019/08/30/ver1567226646/2517777_810x458.jpg",
        "https://pix10.agoda.net/geo/city/14932/1_14932_02.jpg?s=1920x822,",
        "https://i2.milimaj.com/i/milliyet/75/750x0/5e99617855428118b85feb1f")

    class RowHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(title: String){
            itemView.category_card_title.text = title
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
        println(position)
        holder.bind(postList[position])
    }
}
