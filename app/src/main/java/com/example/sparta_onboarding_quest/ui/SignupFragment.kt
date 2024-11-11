package com.example.sparta_onboarding_quest.ui

import android.app.Activity
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.sparta_onboarding_quest.R
import com.example.sparta_onboarding_quest.data.UserDataManager
import com.example.sparta_onboarding_quest.databinding.FragmentSignupBinding
import java.util.Calendar

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private var idCheck = false
    private var pwCheck = false
    private var pwckCheck = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val root : View = binding.root

        var strMbti = "ESTJ"
        var strGender = "남성"
        lateinit var strAge: String
        var strName = "이름없음"

        val datePicker: DatePicker = binding.signupAgePicker
        val calendar: Calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        datePicker.init(year, month, day, null) // DatePicker 초기화
        datePicker.maxDate = calendar.timeInMillis // 최대 날짜를 오늘로 설정
        datePicker.updateDate(year, month, day)

        binding.apply {
            signupBack.setOnClickListener {
                findNavController().navigate(R.id.move_to_home)
            }

            signupPwVisibility.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){ signupPw.inputType = 128 } // 비번 가림 해제
                else{ signupPw.inputType = 129 } //비번 가림 설정
            }
            signupPwckVisibility.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){ signupPwck.inputType = 128 } // 비번확인 가림 해제
                else{ signupPwck.inputType = 129 } //비번확인 가림 설정
            }

            signupMbtiEi.setOnClickListener {
                if(signupMbtiEi.isChecked)strMbti = strMbti.replace("E","I")
                else strMbti = strMbti.replace("I","E")
            }
            signupMbtiSn.setOnClickListener {
                if(signupMbtiSn.isChecked)strMbti = strMbti.replace("S","N")
                else strMbti = strMbti.replace("N","S")
            }
            signupMbtiTf.setOnClickListener {
                if(signupMbtiTf.isChecked)strMbti = strMbti.replace("T","F")
                else strMbti = strMbti.replace("F","T")
            }
            signupMbtiJp.setOnClickListener {
                if(signupMbtiJp.isChecked)strMbti = strMbti.replace("J","P")
                else strMbti = strMbti.replace("P","J")
            }

            signupGender.setOnClickListener {
                if (signupGender.isChecked){strGender = signupGender.textOn.toString()}
                else{strGender = signupGender.textOff.toString()}

            }
            signupSignup.setOnClickListener {
                strAge =
                signupAgePicker.year.toString() + "-" +
                signupAgePicker.month.plus(1).toString() + "-" +
                signupAgePicker.dayOfMonth.toString()
                if(signupName.text.isNotEmpty()){
                    strName = signupName.text.toString()
                }

                val userDataManager = UserDataManager(requireContext())
                userDataManager.saveUserData(
                    userId = signupId.text.toString(),
                    password = signupPw.text.toString(),
                    name = strName,
                    birthDate = strAge,
                    mbti = strMbti,
                    gender = strGender
                )
//                findNavController().navigate(R.id.move_to_signin)
                findNavController().navigate(R.id.move_to_home)
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            signupId.doAfterTextChanged { editCheck("id", signupId) }
            signupPw.doAfterTextChanged { editCheck("pw", signupPw) }
            signupPwck.doAfterTextChanged { editCheck("pwck", signupPwck) }
            signupName.doAfterTextChanged { editCheck("name", signupName) }
        }
    }

    fun editCheck(type:String, target:EditText) {
        val idRegex = Regex("^(?=.*[A-Za-z]{2,})(?=.*\\d)[A-Za-z\\d]{4,12}\$")
        val pwRegex = Regex("^(?=.*[A-Za-z]{2,})(?=.*\\d)(?=.*[!@#\$%^&*])[A-Za-z\\d!@#\$%^&*]{6,12}$")
        val nameRegex = Regex("^[a-zA-Z가-힣]{2,20}$")

        when(type){
            "id"->{
                idCheck = false
                if (target.text.isEmpty() || target.text.length < 4){
                    binding.signupIdTitle.setTextColor(Color.RED)
                    binding.signupIdTitle.text = "아이디 * 아이디가 비었거나 짧습니다."
                }else if(!target.text.matches(idRegex)) {
                    binding.signupIdTitle.setTextColor(Color.RED)
                    binding.signupIdTitle.text = "아이디 * 영문(2글자이상)+숫자 조합해 주세요."
                }else{
                    binding.signupIdTitle.setTextColor(Color.GREEN)
                    binding.signupIdTitle.text = "아이디 * ✔️"
                    idCheck = true
                }
            }
            "pw"->{
                pwCheck = false
                if (target.text.isEmpty() || target.text.length < 6){
                    binding.signupPwTitle.setTextColor(Color.RED)
                    binding.signupPwTitle.text = "비밀번호 * 비밀번호가 비었거나 짧습니다."
                }else if(!target.text.matches(pwRegex)) {
                    binding.signupPwTitle.setTextColor(Color.RED)
                    binding.signupPwTitle.text = "비밀번호 * 영문(2)+특수문자+숫자 조합해 주세요."
                }else{
                    binding.signupPwTitle.setTextColor(Color.GREEN)
                    binding.signupPwTitle.text = "비밀번호 * ✔️"
                    pwCheck = true
                }
            }
            "pwck"->{
                pwckCheck = false
               if (binding.signupPw.text?.isEmpty() == true){
                    binding.signupPwckTitle.setTextColor(Color.RED)
                    binding.signupPwckTitle.text = "비밀번호 확인 * 비밀번호 먼저 입력해 주세요"
                }else if (binding.signupPw.text.toString()?.equals(target.text.toString()) != true) {
                    binding.signupPwckTitle.setTextColor(Color.RED)
                    binding.signupPwckTitle.text = "비밀번호 확인 * 서로 맞지 않습니다."
                }else{
                    binding.signupPwckTitle.setTextColor(Color.GREEN)
                    binding.signupPwckTitle.text = "비밀번호 확인 * ✔️"
                    pwckCheck = true
                }
            }
            "name"->{
                if (target.text.isEmpty() || target.text.length < 2){
                    binding.signupNameTitle.setTextColor(Color.RED)
                    binding.signupNameTitle.text = "이름 * 이름이 비었거나 짧습니다."
                }else if(!target.text.matches(nameRegex)) {
                    binding.signupNameTitle.setTextColor(Color.RED)
                    binding.signupNameTitle.text = "이름 * 영문or한글 2자 이상 입력해 주세요"
                }else{
                    binding.signupNameTitle.setTextColor(Color.GREEN)
                    binding.signupNameTitle.text = "이름 * ✔️"
                }
            }
            else->{}
        }
        binding.signupSignup.isEnabled = idCheck && pwCheck && pwckCheck
    }

    companion object {

    }
}