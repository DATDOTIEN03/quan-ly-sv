package com.example.pratice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pratice.models.Student

class StudentViewModel: ViewModel() {
    private val _students = MutableLiveData<List<Student>>(emptyList())
    val students: LiveData<List<Student>> = _students
    private var nextId = 1
    var selectedStudent: Student? = null


    fun addStudent(name: String, mssv: String) {
        if (name.isBlank() || mssv.isBlank()) {
            return
        }

        // Kiểm tra MSSV trùng
        val exists = _students.value?.any { it.mssv == mssv } == true
        if (exists) {
            return
        }
        val newStudent = Student(nextId++, name, mssv)
        _students.value = _students.value?.plus(newStudent)
    }

    fun updateStudent(name: String, mssv: String) {
        selectedStudent?.let { student ->
            student.name = name
            student.mssv = mssv
            _students.value = _students.value?.map {
                if (it.id == student.id) student else it
            }
        }
    }

    fun deleteStudent() {
        selectedStudent?.let { student ->
            _students.value = _students.value?.filter { it.id != student.id }
            selectedStudent = null
        }
    }
}