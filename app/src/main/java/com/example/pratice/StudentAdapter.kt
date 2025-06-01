package com.example.pratice

import Student
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private var students: List<Student>,
    private val onItemClick: (Student) -> Unit,
    private val onUpdateClick: (Student) -> Unit,
    private val onDeleteClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.txtId)
        val tvName: TextView = itemView.findViewById(R.id.txtName)
        val btnUpdate: Button = itemView.findViewById(R.id.btnUpdate)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

        init {
            itemView.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)

//                onItemClick(students[adapterPosition])
            }

            btnUpdate.setOnClickListener {
                onUpdateClick(students[adapterPosition])
            }
            btnDelete.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onDeleteClick(students[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.tvId.text = student.id
        holder.tvName.text = student.name

        holder.btnUpdate.visibility = if (position == selectedPosition) View.VISIBLE else View.GONE
        holder.btnDelete.visibility = if (position == selectedPosition) View.VISIBLE else View.GONE

    }

    override fun getItemCount(): Int = students.size

    fun updateData(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }
}