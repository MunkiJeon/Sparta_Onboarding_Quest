package com.example.sparta_onboarding_quest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sparta_onboarding_quest.R
import com.example.sparta_onboarding_quest.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root : View = binding.root

        binding.homeSignIn.setOnClickListener {
            findNavController().navigate(R.id.move_to_signin)
        }
        binding.homeSignUp.setOnClickListener {
            findNavController().navigate(R.id.move_to_signup)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
    }
}