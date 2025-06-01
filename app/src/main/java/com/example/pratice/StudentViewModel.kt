package com.example.pratice

import Student
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    private val _students = MutableLiveData<List<Student>>()
    val students: LiveData<List<Student>> get() = _students

    init {
        // Khởi tạo dữ liệu mẫu ban đầu (có thể để rỗng)
        _students.value = listOf(
            Student("20210202P", "Nguyễn Văn A"),
            Student("210202P", "Trần Thị B")
        )
    }

    fun deleteStudent(student: Student) {
        val currentList = _students.value ?: return
        _students.value = currentList.filter { it != student }
    }

    fun updateStudent(updated: Student) {
        val currentList = _students.value ?: return
        _students.value = currentList.map {
            if (it.id == updated.id) updated else it
        }
    }
    fun addStudent(student: Student) {
        val currentList = _students.value ?: emptyList()
        _students.value = currentList + student
    }
}