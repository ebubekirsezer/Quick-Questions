package com.ess.quickquestions.ui.signviews

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ess.quickquestions.R
import com.ess.quickquestions.databinding.FragmentSignBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_sign.*

class SignFragment : Fragment() {

    private lateinit var viewModel: SignViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSignBinding.inflate((inflater))
        viewModel = ViewModelProvider(this).get(SignViewModel::class.java)

        auth = Firebase.auth

        binding.viewModel = viewModel

        //Input text click listener
        viewModel.onTextKeyListener(
            binding.signInEmailText,
            binding.signInEmailInput,
            binding.signInPasswordText,
            binding.signInPasswordInput,
            binding.signUpEmailText,
            binding.signUpEmailInput,
            binding.signUpPasswordText,
            binding.signUpPasswordInput,
            binding.signUpRepasswordText,
            binding.signUpRepasswordInput
        )


        //Sign In Button Click Listener
        binding.signInButton.setOnClickListener {
            viewModel.onSignInClicked(
                binding.signInEmailText,
                binding.signInEmailInput,
                binding.signInPasswordText,
                binding.signInPasswordInput,
            )
        }

        //Sign Up Button Click Listener
        binding.signUpButton.setOnClickListener {
/*            auth.createUserWithEmailAndPassword("ebubekirssezer@gmail.com","12345678").addOnCompleteListener {
                if (it.isSuccessful){
                    println("Başarılı")
                }else {
                    println("hata")
                }
            }*/

            viewModel.onSignUpClicked(
                binding.signUpEmailText,
                binding.signUpEmailInput,
                binding.signUpPasswordText,
                binding.signUpPasswordInput,
                binding.signUpRepasswordText,
                binding.signUpRepasswordInput
            )
        }

        viewModel.loadingProcess.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.loadingAnimationInclude.visibility = View.VISIBLE
            } else{
                binding.loadingAnimationInclude.visibility = View.GONE
            }

        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onTabSignClick()
        auth = Firebase.auth
        super.onViewCreated(view, savedInstanceState)
    }

    //Sign In and Sign Up tab click events
    private fun onTabSignClick() {
        button_sign_in.setOnClickListener {
            button_sign_in.setTextColor(resources.getColor(R.color.textColorPrimary))
            horizontal_sign_in_line.setBackgroundColor(resources.getColor(R.color.textColorPrimary))
            button_sign_up.setTextColor(resources.getColor(R.color.textGreyColor))
            horizontal_sign_up_line.setBackgroundColor(resources.getColor(R.color.textGreyColor))

            linear_layout_sign_in.visibility = View.VISIBLE
            linear_layout_sign_up.visibility = View.GONE
        }

        button_sign_up.setOnClickListener {
            button_sign_in.setTextColor(resources.getColor(R.color.textGreyColor))
            horizontal_sign_in_line.setBackgroundColor(resources.getColor(R.color.textGreyColor))
            button_sign_up.setTextColor(resources.getColor(R.color.textColorPrimary))
            horizontal_sign_up_line.setBackgroundColor(resources.getColor(R.color.textColorPrimary))

            linear_layout_sign_in.visibility = View.GONE
            linear_layout_sign_up.visibility = View.VISIBLE
        }
    }


    private fun isPasswordMatch(password: Editable?, repassword: Editable?): Boolean {
        return password == repassword
    }
}