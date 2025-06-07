package com.example.pratice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: StudentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        viewModel = ViewModelProvider(requireActivity())[StudentViewModel::class.java]

        adapter = StudentAdapter(
            students = emptyList(),
            onItemClick = {},
            onUpdateClick = { student ->
                val intent = Intent(requireContext(), UpdateStudentActivity::class.java)
                intent.putExtra("student", student)
                startActivity(intent)
            },
            onDeleteClick = { student ->
                viewModel.deleteStudent(student)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.students.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }
}