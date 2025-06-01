package com.example.pratice

import Student
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity


class AddStudentActivity : AppCompatActivity() {

    private lateinit var edtId: EditText
    private lateinit var edtName: EditText
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        edtId = findViewById(R.id.edtId)
        edtName = findViewById(R.id.edtName)
        btnAdd = findViewById(R.id.btnAdd) // Nút thêm (text "Thêm")

        btnAdd.setOnClickListener {
            val newId = edtId.text.toString().trim()
            val newName = edtName.text.toString().trim()

            if (newId.isEmpty() || newName.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newStudent = Student(newId, newName)
            val resultIntent = Intent()
            resultIntent.putExtra("new_student", newStudent)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}