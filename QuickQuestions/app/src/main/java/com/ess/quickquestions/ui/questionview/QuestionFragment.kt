package com.ess.quickquestions.ui.questionview

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ess.quickquestions.R
import com.ess.quickquestions.databinding.FragmentQuestionBinding
import com.ess.quickquestions.model.Question


class QuestionFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel
    var optionIndex: Int = 0

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

        viewModel.isCorrect.observe(viewLifecycleOwner, Observer {isCorrect ->
            if(isCorrect){
                when(optionIndex){
                    1 -> {
                        binding.buttonOption.backgroundTintList = resources.getColorStateList(R.color.correctGreen)
                        binding.buttonOption.setImageResource(R.drawable.ic_true_answer)
                    }
                    2 -> {
                        binding.buttonOption2.backgroundTintList = resources.getColorStateList(R.color.correctGreen)
                        binding.buttonOption2.setImageResource(R.drawable.ic_true_answer)
                    }
                    3 -> {
                        binding.buttonOption3.backgroundTintList = resources.getColorStateList(R.color.correctGreen)
                        binding.buttonOption3.setImageResource(R.drawable.ic_true_answer)
                    }
                    4 -> {
                        binding.buttonOption4.backgroundTintList = resources.getColorStateList(R.color.correctGreen)
                        binding.buttonOption4.setImageResource(R.drawable.ic_true_answer)
                    }
                }
            } else {
                when(optionIndex){
                    1 -> {
                        binding.buttonOption.backgroundTintList = resources.getColorStateList(R.color.wrongRed)
                        binding.buttonOption.setImageResource(R.drawable.ic_wrong_answer)
                    }
                    2 -> {
                        binding.buttonOption2.backgroundTintList = resources.getColorStateList(R.color.wrongRed)
                        binding.buttonOption2.setImageResource(R.drawable.ic_wrong_answer)
                    }
                    3 -> {
                        binding.buttonOption3.backgroundTintList = resources.getColorStateList(R.color.wrongRed)
                        binding.buttonOption3.setImageResource(R.drawable.ic_wrong_answer)
                    }
                    4 -> {
                        binding.buttonOption4.backgroundTintList = resources.getColorStateList(R.color.wrongRed)
                        binding.buttonOption4.setImageResource(R.drawable.ic_wrong_answer)
                    }
                }
            }
        })

        viewModel.isNotSelectedOption.observe(viewLifecycleOwner, Observer {isNotSelected ->
            if(isNotSelected)
                Toast.makeText(context,"You have to select an Option!!", Toast.LENGTH_SHORT).show()
        })

        viewModel.isTestFinish.observe(viewLifecycleOwner, Observer { isFinish ->
            if(isFinish){
                val navController = findNavController()
                navController.navigate(QuestionFragmentDirections.actionQuestionFragmentToFinishFragment(viewModel.score))
            }
        })


        setOptionClicked(binding)

        binding.buttonNext.setOnClickListener {
            resetOptions(binding)
            viewModel.next()
        }

        binding.buttonSubmit.setOnClickListener {
            optionIndex = checkSelectedButton(binding)
            viewModel.submit(optionIndex)
        }

        binding.viewModel = viewModel

        return binding.root
    }

    private fun resetOptions(binding: FragmentQuestionBinding) {

        binding.optionCard1.isClickable = true
        binding.optionCard2.isClickable = true
        binding.optionCard3.isClickable = true
        binding.optionCard4.isClickable = true

        binding.buttonOption.setImageResource(android.R.color.transparent)
        binding.buttonOption.backgroundTintList =resources.getColorStateList(R.color.textGreyColor)

        binding.buttonOption2.setImageResource(android.R.color.transparent)
        binding.buttonOption2.backgroundTintList =resources.getColorStateList(R.color.textGreyColor)

        binding.buttonOption3.setImageResource(android.R.color.transparent)
        binding.buttonOption3.backgroundTintList =resources.getColorStateList(R.color.textGreyColor)

        binding.buttonOption4.setImageResource(android.R.color.transparent)
        binding.buttonOption4.backgroundTintList =resources.getColorStateList(R.color.textGreyColor)
    }

    private fun checkSelectedButton(binding: FragmentQuestionBinding): Int {

        binding.optionCard1.isClickable = false
        binding.optionCard2.isClickable = false
        binding.optionCard3.isClickable = false
        binding.optionCard4.isClickable = false

        if(binding.buttonOption.backgroundTintList == resources.getColorStateList(R.color.colorPrimary)){
            return 1
            println("Option 1")
        }
        else if(binding.buttonOption2.backgroundTintList == resources.getColorStateList(R.color.colorPrimary)){
            return 2
            println("Option 2")
        }
        else if(binding.buttonOption3.backgroundTintList == resources.getColorStateList(R.color.colorPrimary)){
            return 3
            println("Option 3")
        }
        else if(binding.buttonOption4.backgroundTintList == resources.getColorStateList(R.color.colorPrimary)){
            return 4
            println("Option 4")
        } else{
            binding.optionCard1.isClickable = true
            binding.optionCard2.isClickable = true
            binding.optionCard3.isClickable = true
            binding.optionCard4.isClickable = true
            return 0
            println("No option")
        }
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