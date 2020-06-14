package com.pavelkazancev02.teztest.ui.account_info.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pavelkazancev02.teztest.data.value_object.account_op.Op
import com.pavelkazancev02.teztest.databinding.ListItemOperationBinding
import com.pavelkazancev02.teztest.ui.formatAddress
import com.pavelkazancev02.teztest.ui.formatTime


class OperationsAdapter() : androidx.recyclerview.widget.ListAdapter<Op, OperationsAdapter.ViewHolder>(
        OperationsDiffCallback()
    ) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListItemOperationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Op
        ) {
            binding.operation = item
            binding.operationsSender.text = formatAddress(item.sender.toString())
            binding.operationsReceiver.text = formatAddress(item.receiver.toString())
            binding.operationsTime.text = formatTime(item.time.toString())
            binding.operationsValue.text = item.volume.toString()+" ꜩ"
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemOperationBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class OperationsDiffCallback : DiffUtil.ItemCallback<Op>() {
    override fun areItemsTheSame(
        oldItem: Op,
        newItem: Op
    ): Boolean {
        return oldItem.rowId == newItem.rowId
    }

    override fun areContentsTheSame(
        oldItem: Op,
        newItem: Op
    ): Boolean {
        return oldItem == newItem
    }

}



