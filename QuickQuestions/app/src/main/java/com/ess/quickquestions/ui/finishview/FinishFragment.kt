package com.ess.quickquestions.ui.finishview

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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

        if (score < 60) {
            (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
                ColorDrawable(
                    resources.getColor(R.color.wrongRed)
                )
            )
            binding.viewCurvedRectangle.backgroundTintList =
                resources.getColorStateList(R.color.wrongRed)
            binding.textResult.text = "Upps !!"
            binding.textScore.text = "Your Score: " + score
            binding.textSuggestion.visibility = View.VISIBLE
        } else {
            (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
                ColorDrawable(
                    resources.getColor(R.color.correctGreen)
                )
            )
            binding.viewCurvedRectangle.backgroundTintList =
                resources.getColorStateList(R.color.correctGreen)
            binding.textResult.text = "Congrats !!"
            binding.textScore.text = "Your Score: " + score
            binding.textSuggestion.visibility = View.GONE
        }

        val sharedPref = activity?.getSharedPreferences("QQ", Context.MODE_PRIVATE)
        var highestScore = sharedPref?.getInt("Score", 15)

        viewModel = ViewModelProvider(this).get(FinishViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.isError.observe(viewLifecycleOwner, Observer { isError ->
            if (isError)
                Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show()
            else{
                binding.buttonTakeQuiz.setOnClickListener {
                    binding.buttonTakeQuiz.isEnabled = false
                    val navController = findNavController()
                    navController.navigate(FinishFragmentDirections.actionFinishFragmentToHomeFragment())
                }
            }
        })

        viewModel.isUpdated.observe(viewLifecycleOwner, Observer { isUpdate ->
            binding.buttonTakeQuiz.isEnabled = isUpdate
        })

        viewModel.loadingProcess.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading)
                binding.loadingAnimationInclude.visibility = View.VISIBLE
            else
                binding.loadingAnimationInclude.visibility = View.GONE
        })

        viewModel.updateScore(score, highestScore)

        return binding.root
    }

}