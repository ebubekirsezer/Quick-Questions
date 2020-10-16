package com.ess.quickquestions.ui.questionview

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ess.quickquestions.R
import com.ess.quickquestions.databinding.FragmentQuestionBinding
import com.ess.quickquestions.model.Question


class QuestionFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = ""

        val binding = FragmentQuestionBinding.inflate(inflater)

        val category= QuestionFragmentArgs.fromBundle(requireArguments()).Category

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

        viewModel.question.observe(viewLifecycleOwner, Observer {
            binding.questionText.text = it.question
            binding.optionText.text = it.option_1
            binding.optionText2.text = it.option_2
            binding.optionText3.text = it.option_3
            binding.optionText4.text = it.option_4
        })

        setOptionClicked(binding)

        binding.viewModel = viewModel

        return binding.root
    }

    //Button click event
    private fun setOptionClicked(binding: FragmentQuestionBinding) {
        binding.optionCard1.setOnClickListener {
            binding.buttonOption.backgroundTintList = resources.getColorStateList(R.color.colorPrimary)
            binding.buttonOption2.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
            binding.buttonOption3.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
            binding.buttonOption4.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
        }

        binding.optionCard2.setOnClickListener {
            binding.buttonOption.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
            binding.buttonOption2.backgroundTintList = resources.getColorStateList(R.color.colorPrimary)
            binding.buttonOption3.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
            binding.buttonOption4.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
        }
        binding.optionCard3.setOnClickListener {
            binding.buttonOption.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
            binding.buttonOption2.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
            binding.buttonOption3.backgroundTintList = resources.getColorStateList(R.color.colorPrimary)
            binding.buttonOption4.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
        }
        binding.optionCard4.setOnClickListener {
            binding.buttonOption.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
            binding.buttonOption2.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
            binding.buttonOption3.backgroundTintList = resources.getColorStateList(R.color.textGreyColor)
            binding.buttonOption4.backgroundTintList = resources.getColorStateList(R.color.colorPrimary)
        }
    }
}