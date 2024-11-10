package com.example.sparta_onboarding_quest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sparta_onboarding_quest.R
import com.example.sparta_onboarding_quest.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val root : View = binding.root

        binding.apply {
            signupBack.setOnClickListener {
                findNavController().navigate(R.id.move_to_home)
            }

            signupPwVisibility.setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("비번가림누름", "${isChecked} : ${binding.signupPw.inputType}")
                if (isChecked){ signupPw.inputType = 128 } // 비번 가림 해제
                else{ signupPw.inputType = 129 } //비번 가림 설정
            }
            signupPwckVisibility.setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("비번확인 가림누름", "${isChecked} : ${binding.signupPwck.inputType}")
                if (isChecked){ signupPwck.inputType = 128 } // 비번확인 가림 해제
                else{ signupPwck.inputType = 129 } //비번확인 가림 설정
            }
        }
        return root
    }

    companion object {
    }
}