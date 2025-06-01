package com.example.pratice

import Student
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpdateStudentActivity : AppCompatActivity() {

    private lateinit var edtId: EditText
    private lateinit var edtName: EditText
    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_student)

        edtId = findViewById(R.id.edtId)
        edtName = findViewById(R.id.edtName)
        btnUpdate = findViewById(R.id.btnUpdate)

        @Suppress("DEPRECATION")
        val student = intent.getSerializableExtra("student") as? Student
        student?.let {
            edtId.setText(it.id)
            edtName.setText(it.name)
        }

        btnUpdate.setOnClickListener {
            val newId = edtId.text.toString().trim()
            val newName = edtName.text.toString().trim()

            if (newId.isEmpty() || newName.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedStudent = Student(newId, newName)
            val resultIntent = Intent().apply {
                putExtra("updated_student", updatedStudent)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}