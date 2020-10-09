package com.ess.quickquestions.ui.homeview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ess.quickquestions.R
import kotlinx.android.synthetic.main.category_card_view.view.*
import kotlinx.android.synthetic.main.quiz_list_view.view.*

class QuizListAdapter(private val postList: ArrayList<String>) : RecyclerView.Adapter<QuizListAdapter.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(title: String){
            itemView.quiz_list_title.text = title
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
        println(position)
        holder.bind(postList[position])
    }
}