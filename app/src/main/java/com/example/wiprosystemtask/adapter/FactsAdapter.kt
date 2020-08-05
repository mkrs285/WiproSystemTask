package com.example.wiprosystemtask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.wiprosystemtask.R
import com.example.wiprosystemtask.repo.factsResponse.RowsItem


class FactsAdapter(items: List<RowsItem>, ctx: Context) :
    ArrayAdapter<RowsItem>(ctx, R.layout.facts_item_layout, items) {

    internal class ViewHolder(view: View) {
        init {
            ButterKnife.bind(this, view)
        }

        @BindView(R.id.tvTitle)
        lateinit var tvTitle: TextView

        @BindView(R.id.tvSubTitle)
        lateinit var tvSubTitle: TextView

        @BindView(R.id.ivImage)
        lateinit var ivImage: ImageView


    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder
        if (view != null) {
            holder = view.tag as ViewHolder
        } else {
            view = LayoutInflater.from(context).inflate(
                R.layout.facts_item_layout, parent, false
            )
            holder = ViewHolder(view)
            view.tag = holder
        }

        var rowsItem = getItem(position)


        holder.tvTitle.text = rowsItem?.title?.let { it }
        holder.tvSubTitle.text = rowsItem?.description?.let { it }
        if (rowsItem != null) {
            if (rowsItem.imageHref != null) {
                val circularProgressDrawable = CircularProgressDrawable(context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()

                val requestOption: RequestOptions = RequestOptions()
                    .placeholder(circularProgressDrawable)
                    .error(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .dontAnimate()
                    .dontTransform()

                Glide.with(holder.ivImage.context)
                    .load(rowsItem.imageHref)
                    .apply(requestOption)
                    .into(holder.ivImage)

            } else {
                holder.ivImage.setImageResource(R.mipmap.ic_launcher)
            }
        }
        if (view != null) {
            view.tag = holder
        }
        return view!!
    }


}
