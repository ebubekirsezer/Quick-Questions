package com.ess.quickquestions.ui.homeview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ess.quickquestions.R
import com.ess.quickquestions.model.CategoryX
import kotlinx.android.synthetic.main.category_card_view.view.*
import kotlinx.android.synthetic.main.quiz_list_view.view.*

class QuizListAdapter(private val postList: ArrayList<CategoryX>,private val onClickListener: OnClickListener) : RecyclerView.Adapter<QuizListAdapter.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(model: CategoryX){
            itemView.quiz_list_title.text = model.name
            itemView.quiz_list_detail.text ="Develop with " + model.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_list_view,parent,false)

        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val category = postList[position]
        holder.itemView.start_button.setOnClickListener {
            onClickListener.onClick(category)
        }
        holder.bind(category)
    }

    class OnClickListener(val clickListener: (category: CategoryX) -> Unit){
     fun onClick(category: CategoryX) = clickListener(category)
    }
}