package com.example.firstapp.adapters
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.example.firstapp.R



class MyAdapter (public val data :List<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textview = view.findViewById<TextView>(R.id.textviewName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        val holder = MyViewHolder(layout)
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textview.text = data[position].toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

