import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coding.sibisa.R
import com.coding.sibisa.data.response.DataItem
import com.coding.sibisa.databinding.ActivityDetailBinding
import com.coding.sibisa.databinding.RowBelajarBinding

class MateriAdapter(private val dataList: List<DataItem>) : RecyclerView.Adapter<MateriAdapter.ViewHolder>() {

    // Inner class ViewHolder to hold references to the views in each item
    class ViewHolder(private val binding: RowBelajarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stories: DataItem){
            with(binding){
                judul.text = stories.name
                tvTagline.text = stories.tagline
                tvTime.text = stories.time
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowBelajarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
        // Bind the data to the views in each item
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}