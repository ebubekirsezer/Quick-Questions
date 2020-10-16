package com.ess.quickquestions.ui.questionview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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

        val application = requireNotNull(this.activity).application
        val viewModelFactory = QuestionViewModelFactory(category,application)
        viewModel = ViewModelProvider(this,viewModelFactory).get(QuestionViewModel::class.java)


        viewModel.isNext.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.buttonSubmit.visibility = View.VISIBLE
                binding.buttonNext.visibility = View.GONE
            }
        })

        viewModel.isSubmit.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.buttonSubmit.visibility = View.GONE
                binding.buttonNext.visibility = View.VISIBLE
            }
        })


        binding.viewModel = viewModel
        return binding.root
    }
}