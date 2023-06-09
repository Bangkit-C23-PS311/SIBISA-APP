package com.coding.sibisa.materi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coding.sibisa.R

class HurufAdapter(var context: Context) : RecyclerView.Adapter<HurufAdapter.ViewHolder>() {

    var dataList = emptyList<HurufDataModel>()

    internal fun setDataList(dataList: List<HurufDataModel>){
        this.dataList = dataList
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
        holder.image.setImageResource(data.image)
    }

    override fun getItemCount() = dataList.size

}