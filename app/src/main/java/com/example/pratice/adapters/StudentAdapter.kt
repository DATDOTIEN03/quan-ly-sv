package com.example.pratice.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pratice.models.Student

class StudentAdapter(
    private var items: List<Student> = listOf(),
    private val onClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var selectedId: Int? = null

    fun setData(newItems: List<Student>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun setSelected(student: Student) {
        selectedId = student.id
        notifyDataSetChanged()
    }

    inner class StudentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return StudentViewHolder(v)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = items[position]
        holder.text.text = "${student.name} - ${student.mssv}"
        holder.view.setBackgroundColor(
            if (student.id == selectedId) Color.LTGRAY else Color.TRANSPARENT
        )
        holder.view.setOnClickListener {
            onClick(student)
            setSelected(student)
        }
    }
}
