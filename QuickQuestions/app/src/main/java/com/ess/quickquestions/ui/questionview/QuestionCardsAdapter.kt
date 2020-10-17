package com.ess.quickquestions.ui.questionview

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ess.quickquestions.R
import com.ess.quickquestions.model.Question
import kotlinx.android.synthetic.main.question_card.view.*

class QuestionCardsAdapter(
    private val questionList: List<Question>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<QuestionCardsAdapter.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: Question){
        }
    }

    class OnClickListener(val clickListener: (question: Question) -> Unit) {
        fun onClick(question: Question) = clickListener(question)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.question_card, parent, false)

        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return questionList.count()
    }

}