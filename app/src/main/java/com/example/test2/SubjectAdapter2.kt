package com.example.test2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.databinding.Subject2ItemBinding

class SubjectAdapter2(private val subjects: List<Subject2>) : RecyclerView.Adapter<SubjectAdapter2.Subject2ViewHolder>() {
    companion object {
        private const val TAG = "Subject2Adapter"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Subject2ViewHolder {
        val binding = Subject2ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Subject2ViewHolder(binding).also { holder ->
            binding.completedCheckBox2.setOnCheckedChangeListener { _, isChecked ->
                subjects.getOrNull(holder.adapterPosition)?.completed2 =isChecked
            }
        }
    }

    override fun onBindViewHolder(holder: Subject2ViewHolder, position: Int) {
        val subject2 = subjects[position]
        holder.bind(subject2)
        holder.binding.completedCheckBox2.isChecked = subject2.completed2
        holder.binding.completedCheckBox2.setOnCheckedChangeListener { _, isChecked ->
            subject2.completed2 = isChecked
        }
    }


    override fun getItemCount(): Int {
        return subjects.size
    }

    class Subject2ViewHolder(val binding: Subject2ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subject: Subject2) {
            binding.SubjectName2.text = subject.title2
            binding.SubjectScore2.text = "[${subject.score2}]"
            // 여기에 필요한 다른 바인딩 수행
        }
    }
}
