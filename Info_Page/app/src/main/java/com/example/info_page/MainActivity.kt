package com.example.info_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var saveData = SaveData(this)


        var etFullName:EditText = findViewById(R.id.etFullName)
        var etUserName:EditText = findViewById(R.id.etUserName)
        var etEmail:EditText = findViewById(R.id.etEmail)
        var etPassword:EditText = findViewById(R.id.etPassword)
        var etReTypePass:EditText = findViewById(R.id.etRetypePassword)
        var btnRegister:Button = findViewById(R.id.btnRegister)
        var btnShowInfo:Button = findViewById(R.id.btnShowInfo)
        var radioGroupGender:RadioGroup = findViewById(R.id.radioGroup_gender)
        var gender = ""
        var layoutInfo:ConstraintLayout = findViewById(R.id.layout_Info)
        var btnHideInfo:Button = findViewById(R.id.btnHideInfo)
        var tvFullName:TextView = findViewById(R.id.tv_FullName)
        var tvUserName:TextView = findViewById(R.id.tv_UserName)
        var tvEmail:TextView = findViewById(R.id.tv_Email)
        var tvPassword:TextView = findViewById(R.id.tv_Password)
        var tvGender:TextView = findViewById(R.id.tv_Gender)


        fun clearData(){
            if (etFullName.text.isNotEmpty() && etUserName.text.isNotEmpty() &&
                etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty() &&
                etReTypePass.text.isNotEmpty()){

                etFullName.text.clear()
                etUserName.text.clear()
                etEmail.text.clear()
                etPassword.text.clear()
                etReTypePass.text.clear()
                radioGroupGender.clearCheck()
            }

        }

        fun getInputData(){
            saveData.saveUserInfo(etFullName.text.toString()
                ,etUserName.text.toString(),etEmail.text.toString()
                ,etPassword.text.toString(),gender)
        }
        fun setInputData(){
            tvFullName.text = saveData.getFullName()
            tvUserName.text = saveData.getUserName()
            tvEmail.text = saveData.getEmail()
            tvPassword.text = saveData.getPassword()
            tvGender.text = saveData.getGender()
        }
        fun checkInput(){
            if (etPassword.text.toString() != etReTypePass.text.toString()){
                Toast.makeText(this, "Not Equal", Toast.LENGTH_LONG).show()
            }else if (etPassword.text.toString() == etReTypePass.text.toString() &&
                etFullName.text.isNotEmpty() && etUserName.text.isNotEmpty() && etEmail.text.isNotEmpty()){
                getInputData()
                Toast.makeText(this, "Is Done", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Fill Info", Toast.LENGTH_LONG).show()
            }
        }

        radioGroupGender.setOnCheckedChangeListener { radioGroup,_ ->
            gender = if (radioGroup.checkedRadioButtonId == R.id.rbtnMale){
                "Male"
            } else {
                "Female"
            }
        }

        btnShowInfo.setOnClickListener {
            setInputData()
            clearData()
            layoutInfo.visibility = View.VISIBLE
        }
        btnHideInfo.setOnClickListener {
            layoutInfo.visibility = View.GONE
        }
        btnRegister.setOnClickListener {
            checkInput()
        }

    }


}