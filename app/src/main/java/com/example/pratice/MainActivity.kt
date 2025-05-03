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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pratice.adapters.StudentAdapter
import com.example.pratice.viewmodels.StudentViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.list_view_test)
        val nameEdit = findViewById<EditText>(R.id.nameEditText)
        val mssvEdit = findViewById<EditText>(R.id.mssvEditText)
        val addBtn = findViewById<Button>(R.id.addButton)
        val updateBtn = findViewById<Button>(R.id.updateButton)
        val deleteBtn = findViewById<Button>(R.id.deleteButton)
        val recycler = findViewById<RecyclerView>(R.id.studentRecyclerView)

        viewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        adapter = StudentAdapter(onClick = { student ->
            viewModel.selectedStudent = student
            nameEdit.setText(student.name)
            mssvEdit.setText(student.mssv)
        })

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)

        viewModel.students.observe(this) {
            adapter.setData(it)
        }

        addBtn.setOnClickListener {
            viewModel.addStudent(nameEdit.text.toString(), mssvEdit.text.toString())
            nameEdit.text.clear()
            mssvEdit.text.clear()
        }

        updateBtn.setOnClickListener {
            viewModel.updateStudent(nameEdit.text.toString(), mssvEdit.text.toString())
            adapter.notifyDataSetChanged()
        }

        deleteBtn.setOnClickListener {
            viewModel.deleteStudent()
        }


    }

}