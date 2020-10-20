package com.ess.quickquestions.ui.finishview

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ess.quickquestions.R
import com.ess.quickquestions.databinding.FragmentFinishBinding
import kotlinx.android.synthetic.main.fragment_finish.*

class FinishFragment : Fragment() {

    private lateinit var viewModel: FinishViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = ""

        val binding = FragmentFinishBinding.inflate(inflater)
        val score = FinishFragmentArgs.fromBundle(requireArguments()).score

        if(score < 60){
            (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.wrongRed)))
            binding.viewCurvedRectangle.backgroundTintList = resources.getColorStateList(R.color.wrongRed)
            binding.textResult.text = "Upps !!"
            binding.textScore.text = "Your Score: " + score
            binding.textSuggestion.visibility = View.VISIBLE
        } else {
            (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.correctGreen)))
            binding.viewCurvedRectangle.backgroundTintList = resources.getColorStateList(R.color.correctGreen)
            binding.textResult.text = "Congrats !!"
            binding.textScore.text = "Your Score: " + score
            binding.textSuggestion.visibility = View.GONE
        }

        val sharedPref = activity?.getSharedPreferences("QQ", Context.MODE_PRIVATE)
        var highestScore = sharedPref?.getInt("Score",15)


        binding.buttonTakeQuiz.setOnClickListener {
            val navController = findNavController()
            navController.navigate(FinishFragmentDirections.actionFinishFragmentToHomeFragment())
        }

        viewModel = ViewModelProvider(this).get(FinishViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.updateScore(score,highestScore)

        return binding.root
    }

}