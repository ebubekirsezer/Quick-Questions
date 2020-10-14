package com.ess.quickquestions.ui.questionview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ess.quickquestions.R
import com.ess.quickquestions.databinding.FragmentQuestionBinding


class QuestionFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = ""

        val binding = FragmentQuestionBinding.inflate(inflater)

        val category= QuestionFragmentArgs.fromBundle(requireArguments()).Category
        binding.questionText.text  = category.questions[0].question

        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        binding.viewModel = viewModel
        return binding.root
    }
}