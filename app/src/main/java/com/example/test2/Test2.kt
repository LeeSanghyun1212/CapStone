import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Test2 : AppCompatActivity() {
    data class Subject(val name: String, val credit: String, val status: String)

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        val data = mutableListOf(
            Subject("전산수학", "1학점", "selected"),
            Subject("공학설계", "2학점", "unselected")
        )
        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = TestAdapter(data)
        recyclerView.adapter = adapter
    }
}
