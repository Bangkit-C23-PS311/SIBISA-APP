package com.coding.sibisa.ui.materi

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coding.sibisa.R
import com.coding.sibisa.data.response.DataItemItem
import com.coding.sibisa.ui.belajarhuruf.DetailActivity

class HurufAdapter(var context: Context) : RecyclerView.Adapter<HurufAdapter.ViewHolder>() {

    private var dataList = emptyList<DataItemItem>()

    internal fun setDataList(dataList: List<DataItemItem>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var image : ImageView
        var title : TextView

        init {
            image = itemView.findViewById(R.id.iv_huruf)
            title = itemView.findViewById(R.id.tv_huruf)
        }



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.card_huruf,parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = dataList[position]

        holder.title.text = data.title
        Glide.with(holder.itemView)
            .load(data.imageUrl)
            .centerCrop()
            .into(holder.image)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.KONCIAN, data as Parcelable)
            }
            it.context.startActivity(intent)
        }


    }


    override fun getItemCount() = dataList.size

}