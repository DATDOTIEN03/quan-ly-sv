package com.example.pratice

import Student
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: StudentAdapter

    private lateinit var updateStudentLauncher: ActivityResultLauncher<Intent>

    private lateinit var addStudentLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = StudentAdapter(
            students = emptyList(),
            onItemClick = {},
            onUpdateClick = { student ->
                val intent = Intent(this, UpdateStudentActivity::class.java)
                intent.putExtra("student", student)
                updateStudentLauncher.launch(intent)
            },
            onDeleteClick = { student ->
                viewModel.deleteStudent(student)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.students.observe(this) { students ->
            adapter.updateData(students)
        }
        val btnAddStudent = findViewById<Button>(R.id.btnAdd)
        btnAddStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            addStudentLauncher.launch(intent)
        }

        updateStudentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val updatedStudent = result.data?.getSerializableExtra("updated_student") as? Student
                updatedStudent?.let {
                    viewModel.updateStudent(it)
                }
            }
        }

        addStudentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val newStudent = result.data?.getSerializableExtra("new_student") as? Student
                newStudent?.let {
                    viewModel.addStudent(it)
                }
            }
        }
    }
}