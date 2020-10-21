package com.ess.quickquestions.ui.homeview

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.ess.quickquestions.R
import com.ess.quickquestions.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Showing the action bar
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.title = "Welcome"
        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                resources.getColor(R.color.colorPrimary)
            )
        )

        val binding = FragmentHomeBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setHasOptionsMenu(true)

        binding.viewModel = viewModel
        val homeView = inflater.inflate(R.layout.fragment_home, container, false)

        val categoryLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categoricalCardList.layoutManager = categoryLayoutManager

        val listLayoutManager = LinearLayoutManager(context)
        binding.quizList.layoutManager = listLayoutManager

        viewModel.navigateToSign.observe(viewLifecycleOwner, Observer { isNavigate ->
            if (isNavigate) {
                val navController = findNavController()
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToSignFragment())
                viewModel.onNavigatedToSign()
            }
        })

        viewModel.categoryList.observe(viewLifecycleOwner, Observer { categories ->
            val categoryAdapter = CategoryCardListAdapter(categories)
            binding.categoricalCardList.adapter = categoryAdapter

            val listAdapter = QuizListAdapter(categories, QuizListAdapter.OnClickListener {
                binding.quizList.isEnabled = false
                val navController = findNavController()
                navController?.navigate(
                    HomeFragmentDirections.actionHomeFragmentToQuestionFragment(
                        it
                    )
                )
            })
            binding.quizList.adapter = listAdapter
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { visibility ->
            if (visibility) {
                binding.progressBarCategoryLoad.visibility = View.VISIBLE
            } else {
                binding.progressBarCategoryLoad.visibility = View.GONE
            }
        })

        viewModel.isErrorFetchingModels.observe(viewLifecycleOwner, Observer { isError ->
            if (isError)
                Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show()
        })

        viewModel.isErrorFetchingUser.observe(viewLifecycleOwner, Observer { isError ->
            if (isError)
                Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show()
        })

        viewModel.highScore.observe(viewLifecycleOwner, Observer { score ->
            val sharedPref = activity?.getSharedPreferences("QQ", Context.MODE_PRIVATE)
            sharedPref?.edit()?.putInt("Score",score)?.apply()
            binding.textScore.text = getString(R.string.highest_score) + " " + score
        })

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.categoricalCardList)

        viewModel.readData()
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_out -> {
                viewModel.signOut()
            }
        }
        return true
    }
}