package com.example.pratice

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val currencyList = listOf("USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "KRW", "VND")
    private val rates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.9,
        "JPY" to 150.0,
        "GBP" to 0.8,
        "AUD" to 1.4,
        "CAD" to 1.35,
        "CHF" to 0.95,
        "CNY" to 7.2,
        "KRW" to 1350.0,
        "VND" to 25000.0
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val spinnerFrom = findViewById<Spinner>(R.id.spinnerFrom)
        val spinnerTo = findViewById<Spinner>(R.id.spinnerTo)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        var isEditingFrom = false
        var isEditingTo = false

        val editFrom = findViewById<EditText>(R.id.editFrom)
        val editTo = findViewById<EditText>(R.id.editTo)


        editFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isEditingTo) return
                isEditingFrom = true
                val from = spinnerFrom.selectedItem.toString()
                val to = spinnerTo.selectedItem.toString()
                val amountText = editFrom.text.toString()
                val amount = amountText.toDoubleOrNull() ?: return
                val result = convertCurrency(amount, from, to)
                val formattedResult = String.format("%.2f", result)

                editTo.setText(formattedResult)

                isEditingFrom = false
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })


        editTo.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isEditingFrom) return
                isEditingTo = true
                val from = spinnerTo.selectedItem.toString()
                val to = spinnerFrom.selectedItem.toString()
                val amountText = editTo.text.toString()
                val amount = amountText.toDoubleOrNull() ?: return
                val result = convertCurrency(amount, from, to)
                val formattedResult = String.format("%.2f", result)
                editFrom.setText(formattedResult)
                isEditingTo = false
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }


    private fun convertCurrency(amount: Double, from: String, to: String): Double {
        val fromRate = rates[from] ?: 1.0
        val toRate = rates[to] ?: 1.0
        return (amount / fromRate) * toRate
    }



}