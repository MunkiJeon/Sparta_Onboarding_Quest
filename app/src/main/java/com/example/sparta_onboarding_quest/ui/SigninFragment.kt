package com.example.sparta_onboarding_quest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sparta_onboarding_quest.R
import com.example.sparta_onboarding_quest.databinding.FragmentSigninBinding

class SigninFragment : Fragment() {
    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        val root : View = binding.root

        binding.apply {
            signinBack.setOnClickListener {
                findNavController().navigate(R.id.move_to_home)
            }


            signinPwVisibility.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){ signinPw.inputType = 128 } // 비번 가림 해제
                else{ signinPw.inputType = 129 } //비번 가림 설정
            }

            signinLogin.setOnClickListener {
                findNavController().navigate(R.id.move_to_home)
            }
        }

        return root
    }

    companion object {

    }
}