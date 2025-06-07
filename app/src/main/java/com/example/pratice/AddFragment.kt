package com.example.pratice

import Student
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class AddFragment : Fragment() {

    private lateinit var viewModel: StudentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val edtId = view.findViewById<EditText>(R.id.edtId)
        val edtName = view.findViewById<EditText>(R.id.edtName)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        viewModel = ViewModelProvider(requireActivity())[StudentViewModel::class.java]

        btnAdd.setOnClickListener {
            val id = edtId.text.toString().trim()
            val name = edtName.text.toString().trim()

            if (id.isEmpty() || name.isEmpty()) {
                Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.addStudent(Student(id, name))
            edtId.text.clear()
            edtName.text.clear()
            Toast.makeText(context, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show()
        }
    }
}