package com.dasial.travelspend

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dasial.travelspend.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var distanceField: TextInputLayout
    private lateinit var priceField: TextInputLayout
    private lateinit var autonomyField: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        distanceField = binding.textFieldDistance
        priceField = binding.textFieldPrice
        autonomyField = binding.textFieldAutonomy


        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener(this)

    }


    override fun onClick(v: View) {
        if (v.id == R.id.btn_calculate) {
            calculate()
        }
    }

    private fun isFieldsValid(): Boolean {
        return (
                binding.textFieldDistance.editText?.text.toString() != "" && binding.textFieldPrice.editText?.text
                    .toString() != "" && binding.textFieldAutonomy.editText?.text.toString() != "" && binding
                    .textFieldAutonomy.editText?.text.toString().toFloat() != 0f

                )
    }

    private fun resetErrors() {
        priceField.error = ""
        distanceField.error = ""
        autonomyField.error = ""
    }

    private fun calculate() {
        resetErrors()

        if (isFieldsValid()) {
            val distance = binding.textFieldDistance.editText?.text.toString()
            val price = binding.textFieldPrice.editText?.text.toString()
            val autonomy = binding.textFieldAutonomy.editText?.text.toString()

            val totalValue = (distance.toFloat() * price.toFloat()) / autonomy.toFloat()
            binding.textTotalValue.text = "R$ ${"%.2f".format(totalValue)}"
        } else {
            if (distanceField.editText?.text.toString() == "") {
                distanceField.error = "Campo Obrigatório"
            }
            if (priceField.editText?.text.toString() == "") {
                priceField.error = "Campo Obrigatório"

            }
            if (autonomyField.editText?.text.toString() == "") {
                autonomyField.error = "Campo Obrigatório"
            }


        }


    }


}