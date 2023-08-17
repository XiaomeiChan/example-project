package com.example.exampleproject.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.exampleproject.R
import com.example.exampleproject.databinding.ActivityTaskDetailBinding
import com.example.exampleproject.ui.list.TaskActivity
import com.example.exampleproject.utils.TASK_ID
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskDetailBinding
    private val viewModel: DetailTaskViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_task_detail)
        val taskId = intent.getIntExtra(TASK_ID, -1)
        viewModel.setTaskId(taskId)
        viewModel.task.observe(this) { task ->
            task?.let {
                val tvTitle: TextView = findViewById(R.id.detail_ed_title)
                val tvDescription: EditText = findViewById(R.id.detail_ed_description)
                val tvDueDate: EditText = findViewById(R.id.detail_ed_due_date)

                tvTitle.text = task.title
                tvDescription.setText(task.description)
                tvDueDate.setText(
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
                        Date(
                            task.dueDateMillis
                        )
                    )
                )
            }
            val btnDelete: Button = findViewById(R.id.btn_delete_task)
            btnDelete.setOnClickListener {
                viewModel.deleteTask()
                val intent = Intent(this@DetailTaskActivity, TaskActivity::class.java)
                startActivity(intent)
            }
        }
    }
}