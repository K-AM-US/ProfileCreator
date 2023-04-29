package com.example.profilecreator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.profilecreator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var spinner: Spinner
    private var today = Calendar.getInstance()
    private val sdf = SimpleDateFormat("MM/dd/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        spinner = binding.spinner

        ArrayAdapter.createFromResource(
            this,
            R.array.spinnerOpt,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun click(view: View) {

        val bundle = Bundle()
        val intent = Intent(this, ShowData::class.java)

        val todayFormat: Date? = sdf.parse(
            "${(today.get(Calendar.MONTH) + 1).toString()}/${
                today.get(Calendar.DAY_OF_MONTH).toString()
            }/${today.get(Calendar.YEAR).toString()}"
        )

        val birthFormat: Date? =
            sdf.parse("${(binding.datePicker.month + 1).toString()}/${(binding.datePicker.dayOfMonth).toString()}/${binding.datePicker.year.toString()}")


        if (binding.name.text.isNotEmpty()) {
            if (binding.lastName.text.isNotEmpty()) {
                if (binding.email.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString())
                        .matches()
                ) {
                    if (binding.number.text.isNotEmpty() && binding.number.text.length == 9) {

                        if (todayFormat!!.after(birthFormat)) {
                            bundle.putString("name", binding.name.text.toString())
                            bundle.putString("lastName", binding.lastName.text.toString())
                            bundle.putString("email", binding.email.text.toString())
                            bundle.putString("number", binding.number.text.toString())
                            bundle.putInt("age", getAge())
                            bundle.putString("zodiac", getZodiac(birthFormat))
                            bundle.putString("chinese", getChinese(binding.datePicker.year))
                            bundle.putString("element", chineseElement(binding.datePicker.year))
                            bundle.putInt("engineering", binding.spinner.selectedItemId.toInt())

                            intent.putExtras(bundle)

                            startActivity(intent)

                        } else {
                            Toast.makeText(
                                this,
                                resources.getString(R.string.birthWarning),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        binding.number.setError(resources.getString(R.string.numberWarning))
                    }
                } else {
                    binding.email.setError(resources.getString(R.string.emailWarning))
                }
            } else {
                binding.lastName.setError(resources.getString(R.string.lastNameWarning))
            }
        } else {
            binding.name.setError(resources.getString(R.string.nameWarning))
        }
    }

    fun getAge(): Int {
        return if(today.get(Calendar.MONTH) < binding.datePicker.month)
            today.get(Calendar.YEAR) - binding.datePicker.year.toString().toInt() - 1
        else if(today.get(Calendar.MONTH) > binding.datePicker.month)
            today.get(Calendar.YEAR) - binding.datePicker.year.toString().toInt()
        else{
            if(today.get(Calendar.DAY_OF_MONTH) > binding.datePicker.dayOfMonth.toString().toInt())
                today.get(Calendar.YEAR) - binding.datePicker.year.toString().toInt()
            else
                today.get(Calendar.YEAR) - binding.datePicker.year.toString().toInt() -1
        }
    }

    fun getZodiac(birth: Date?): String {

        val aries: Date? = sdf.parse("3/21/${binding.datePicker.year}")
        val taurus: Date? = sdf.parse("4/20/${binding.datePicker.year}")
        val gemini: Date? = sdf.parse("5/21/${binding.datePicker.year}")
        val cancer: Date? = sdf.parse("6/21/${binding.datePicker.year}")
        val leo: Date? = sdf.parse("7/23/${binding.datePicker.year}")
        val virgo: Date? = sdf.parse("8/23/${binding.datePicker.year}")
        val libra: Date? = sdf.parse("9/23/${binding.datePicker.year}")
        val scorpio: Date? = sdf.parse("10/23/${binding.datePicker.year}")
        val sagittarius: Date? = sdf.parse("11/22/${binding.datePicker.year}")
        val capricorn: Date? = sdf.parse("12/22/${binding.datePicker.year}")
        val aquarius: Date? = sdf.parse("1/20/${binding.datePicker.year}")
        val pisces: Date? = sdf.parse("2/19/${binding.datePicker.year}")

        val endYear: Date? = sdf.parse("12/31/${binding.datePicker.year}")
        val startYear: Date? = sdf.parse("1/1/${binding.datePicker.year}")

        if (birth!!.equals(aries) || birth.after(aries) && birth.before(taurus))
            return resources.getString(R.string.aries)
        else if (birth.equals(taurus) || birth.after(taurus) && birth.before(gemini))
            return resources.getString(R.string.taurus)
        else if (birth.equals(gemini) || birth.after(gemini) && birth.before(cancer))
            return resources.getString(R.string.gemini)
        else if (birth.equals(cancer) || birth.after(cancer) && birth.before(leo))
            return resources.getString(R.string.cancer)
        else if (birth.equals(leo) || birth.after(leo) && birth.before(virgo))
            return resources.getString(R.string.leo)
        else if (birth.equals(virgo) || birth.after(virgo) && birth.before(libra))
            return resources.getString(R.string.virgo)
        else if (birth.equals(libra) || birth.after(libra) && birth.before(scorpio))
            return resources.getString(R.string.libra)
        else if (birth.equals(scorpio) || birth.after(scorpio) && birth.before(sagittarius))
            return resources.getString(R.string.scorpio)
        else if (birth.equals(sagittarius) || birth.after(sagittarius) && birth.before(capricorn))
            return resources.getString(R.string.sagittarius)
        else if (birth.equals(capricorn) || birth.after(capricorn) && birth.before(endYear) || birth.after(
                startYear
            ) && birth.before(aquarius)
        )
            return resources.getString(R.string.capricorn)
        else if (birth.equals(aquarius) || birth.after(aquarius) && birth.before(pisces))
            return resources.getString(R.string.aquarius)
        else if (birth.equals(pisces) || birth.after(pisces) && birth.before(aries))
            return resources.getString(R.string.pisces)
        else
            return resources.getString(R.string.wrongDate)

    }

    fun getChinese(year: Int): String {

        return when((year - 1900) % 12){
            0 -> resources.getString(R.string.rat)
            1 -> resources.getString(R.string.ox)
            2 -> resources.getString(R.string.tiger)
            3 -> resources.getString(R.string.rabbit)
            4 -> resources.getString(R.string.dragon)
            5 -> resources.getString(R.string.snake)
            6 -> resources.getString(R.string.horse)
            7 -> resources.getString(R.string.goat)
            8 -> resources.getString(R.string.monkey)
            9 -> resources.getString(R.string.rooster)
            10 -> resources.getString(R.string.dog)
            11 -> resources.getString(R.string.pig)
            else -> resources.getString(R.string.wrongChinese)
        }
    }

    fun chineseElement(year: Int): String {
        val element: Int = year % 10
        return when(element){
            0, 1 -> resources.getString(R.string.metal)
            2, 3 -> resources.getString(R.string.water)
            4, 5 -> resources.getString(R.string.wood)
            6, 7 -> resources.getString(R.string.fire)
            8, 9 -> resources.getString(R.string.earth)
            else -> resources.getString(R.string.wrongElement)
        }
    }
}