package com.example.profilecreator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.profilecreator.databinding.ActivityShowDataBinding

class ShowData : AppCompatActivity() {

    private lateinit var binding: ActivityShowDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_data)

        val bundle = intent.extras

        if(bundle != null){
            binding.nameResult.text = getString(R.string.fullName, bundle.getString("name", ""), bundle.getString("lastName", ""))
            binding.emailResult.text = getString(R.string.emailResult, bundle.getString("email",""))
            binding.numberResult.text = getString(R.string.numberResult,bundle.getString("number", ""))
            binding.age.text = getString(R.string.age, bundle.getInt("age", -1))
            binding.zodiacResult.text = getString(R.string.zodiacResult, bundle.getString("zodiac", ""))
            binding.chineseResult.text = getString(R.string.chineseResult, bundle.getString("chinese", ""))
            binding.elementResult.text = getString(R.string.elementResult, bundle.getString("element", ""))
            binding.bgImageSpinner.setImageResource(selectBackgroundImage(bundle.getInt("engineering", -1)))
        }
    }

    fun selectBackgroundImage(image: Int): Int{

        return when(image){
            0 -> R.drawable.aeroespacial
            1 -> R.drawable.ambiental
            2 -> R.drawable.civil
            3 -> R.drawable.computacion
            4 -> R.drawable.electrica
            5 -> R.drawable.geofisica
            6 -> R.drawable.geologica
            7 -> R.drawable.geomatica
            8 -> R.drawable.industrial
            9 -> R.drawable.mecanica
            10 -> R.drawable.mecatronica
            11 -> R.drawable.minas
            12 -> R.drawable.petrolera
            13 -> R.drawable.telecomunicaciones
            14 -> R.drawable.biomedicos
            else -> R.drawable.blank
        }
    }
}