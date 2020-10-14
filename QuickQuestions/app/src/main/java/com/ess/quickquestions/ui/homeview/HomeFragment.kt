package com.ess.quickquestions.ui.homeview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val binding = FragmentHomeBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.viewModel = viewModel
        val homeView =  inflater.inflate(R.layout.fragment_home, container, false)

        (activity as AppCompatActivity).setSupportActionBar(binding.root.findViewById(R.id.home_toolbar))


        val categoryLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categoricalCardList.layoutManager = categoryLayoutManager

        val listLayoutManager = LinearLayoutManager(context)
        binding.quizList.layoutManager = listLayoutManager

        viewModel.navigateToSign.observe(viewLifecycleOwner, Observer { isNavigate ->
            if(isNavigate){
                val navController = findNavController()
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToSignFragment())
                viewModel.onNavigatedToSign()
            }
        })

        viewModel.categoryList.observe(viewLifecycleOwner, Observer {categories ->
            val categoryAdapter = CategoryCardListAdapter(categories)
            binding.categoricalCardList.adapter = categoryAdapter

            val listAdapter =QuizListAdapter(categories,QuizListAdapter.OnClickListener{
                val navController = findNavController()
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToQuestionFragment(it))
            })
            binding.quizList.adapter = listAdapter
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { visibility ->
            if(visibility){
                binding.progressBarCategoryLoad.visibility = View.VISIBLE
            } else {
                binding.progressBarCategoryLoad.visibility = View.GONE
            }
        })

        viewModel.isErrorFetchingModels.observe(viewLifecycleOwner, Observer {isError ->
            if(isError)
                Toast.makeText(context,"Error occured",Toast.LENGTH_SHORT).show()
        })

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.categoricalCardList)

        viewModel.readData()
        // Inflate the layout for this fragment
        return binding.root
    }
}