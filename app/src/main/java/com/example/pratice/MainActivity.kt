package com.example.pratice

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var editTextA: EditText
    private lateinit var editTextB: EditText
    private lateinit var editTextC: EditText
    private lateinit var editTextResult: EditText
    private lateinit var buttonSolve: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bai1)
        editTextA = findViewById(R.id.editTextA)
        editTextB = findViewById(R.id.editTextB)
        editTextC = findViewById(R.id.editTextC)
        editTextResult = findViewById(R.id.editTextResult)
        buttonSolve = findViewById(R.id.buttonSolve)

        buttonSolve.setOnClickListener {
            solveQuadraticEquation()
        }

    }


    
    private fun solveQuadraticEquation() {
        val aText = editTextA.text.toString()
        val bText = editTextB.text.toString()
        val cText = editTextC.text.toString()

        if (aText.isEmpty() || bText.isEmpty() || cText.isEmpty()) {
            editTextResult.setText("Vui lòng nhập đầy đủ a, b, c.")
            return
        }

        try {
            val a = aText.toDouble()
            val b = bText.toDouble()
            val c = cText.toDouble()

            val result = when {
                a == 0.0 -> {
                    if (b == 0.0) {
                        if (c == 0.0) "Phương trình vô số nghiệm"
                        else "Phương trình vô nghiệm"
                    } else {
                        val x = -c / b
                        "Phương trình bậc nhất\nNghiệm x = $x"
                    }
                }
                else -> {
                    val delta = b * b - 4 * a * c
                    when {
                        delta < 0 -> "Phương trình vô nghiệm"
                        delta == 0.0 -> {
                            val x = -b / (2 * a)
                            "Phương trình có nghiệm kép:\nx = $x"
                        }
                        else -> {
                            val sqrtDelta = Math.sqrt(delta)
                            val x1 = (-b + sqrtDelta) / (2 * a)
                            val x2 = (-b - sqrtDelta) / (2 * a)
                            "Phương trình có 2 nghiệm:\nx1 = $x1\nx2 = $x2"
                        }
                    }
                }
            }

            editTextResult.setText(result)

        } catch (e: NumberFormatException) {
            editTextResult.setText("Hệ số phải là số hợp lệ.")
        }
    }

    // private lateinit var editTextInput: EditText
    // private lateinit var checkUppercase: CheckBox
    // private lateinit var checkLowercase: CheckBox
    // private lateinit var checkReverse: CheckBox
    // private lateinit var textViewResult: TextView

    // override fun onCreate(savedInstanceState: Bundle?) {
    //     super.onCreate(savedInstanceState)
    //     setContentView(R.layout.activity_main)

    //     editTextInput = findViewById(R.id.editTextInput)
    //     checkUppercase = findViewById(R.id.checkUppercase)
    //     checkLowercase = findViewById(R.id.checkLowercase)
    //     checkReverse = findViewById(R.id.checkReverse)
    //     textViewResult = findViewById(R.id.textViewResult)

    //     // Gắn sự kiện thay đổi
    //     val onChangeListener = {
    //         applyTransform()
    //     }

    //     editTextInput.addTextChangedListener(object : TextWatcher {
    //         override fun afterTextChanged(s: Editable?) = onChangeListener()
    //         override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    //         override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    //     })

    //     checkUppercase.setOnCheckedChangeListener { _, _ -> onChangeListener() }
    //     checkLowercase.setOnCheckedChangeListener { _, _ -> onChangeListener() }
    //     checkReverse.setOnCheckedChangeListener { _, _ -> onChangeListener() }
    // }

    // private fun applyTransform() {
    //     var result = editTextInput.text.toString()

    //     if (checkUppercase.isChecked) {
    //         result = result.uppercase()
    //     }

    //     if (checkLowercase.isChecked) {
    //         result = result.lowercase()
    //     }

    //     if (checkReverse.isChecked) {
    //         result = result.reversed()
    //     }

    //     textViewResult.text = result
    // }

}