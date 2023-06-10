import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.sibisa.R
import com.coding.sibisa.data.response.DataItem

class MateriAdapter(private val dataList: List<DataItem>) : RecyclerView.Adapter<MateriAdapter.ViewHolder>() {

    // Inner class ViewHolder to hold references to the views in each item
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Declare the views here
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_belajar, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        // Bind the data to the views in each item
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}