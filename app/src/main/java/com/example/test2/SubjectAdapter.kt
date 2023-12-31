package com.example.test2
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.databinding.SubjectItemBinding
class SubjectAdapter(private val subjects: List<Subject>) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {
    companion object {
        private const val TAG = "SubjectAdater"
    }

    // ViewHolder 생성하는 함수, 최소 생성 횟수만큼만 호출됨 (계속 호출 X)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        val binding =  SubjectItemBinding.inflate(
            LayoutInflater.from(parent.context), // layoutInflater 를 넘기기위해 함수 사용, ViewGroup 는 View 를 상속하고 View 는 이미 Context 를 가지고 있음
            parent, // 부모(리싸이클러뷰 = 뷰그룹)
            false   // 리싸이클러뷰가 attach 하도록 해야함 (우리가 하면 안됨)
        )
        return SubjectViewHolder(binding).also { holder ->
            binding.completedCheckBox.setOnCheckedChangeListener { _, isChecked ->
                subjects.getOrNull(holder.adapterPosition)?.completed = isChecked
            }
        }
    }

    // 만들어진 ViewHolder에 데이터를 바인딩하는 함수
    // position = 리스트 상에서 몇번째인지 의미
    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: $position")
        holder.bind(subjects[position])
    }

    override fun getItemCount(): Int = subjects.size
    class SubjectViewHolder(private val binding: SubjectItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subject: Subject) {
            binding.SubjectName.text = subject.title
            binding.SubjectScore.text = "[${subject.score}]"
            binding.completedCheckBox.isChecked = subject.completed
        }
    }
}