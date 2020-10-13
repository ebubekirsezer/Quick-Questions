package com.ess.quickquestions.ui.homeview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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



        println(viewModel.categoryList)
        viewModel.categoryList.observe(viewLifecycleOwner, Observer {categories ->
            val categoryAdapter = CategoryCardListAdapter(categories)
            binding.categoricalCardList.adapter = categoryAdapter

            val listAdapter =QuizListAdapter(categories)
            binding.quizList.adapter = listAdapter
        })

        viewModel.readData()
        // Inflate the layout for this fragment
        return binding.root
    }
}