import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TestAdapter(private val dataSet: MutableList<Test2.Subject>) :
    RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewItem: TextView = view.findViewById(R.id.TestText)
        val checkBoxItem: CheckBox = view.findViewById(R.id.TestCheck)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.test2_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataSet[position]
        holder.textViewItem.text = "${currentItem.name} : ${currentItem.credit}"
        holder.checkBoxItem.isChecked = currentItem.status == "selected"

        holder.checkBoxItem.setOnCheckedChangeListener { _, isChecked ->
            dataSet[position] =
                dataSet[position].copy(status = if (isChecked) "selected" else "unselected")
        }
    }

    override fun getItemCount() = dataSet.size

    fun updateData(newData: MutableList<Test2.Subject>) {
        dataSet.clear()
        dataSet.addAll(newData)
        notifyDataSetChanged()
    }
}
