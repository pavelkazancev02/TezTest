package com.pavelkazancev02.teztest.ui.subscriptions.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pavelkazancev02.teztest.data.room_db.SubscribedAccount
import com.pavelkazancev02.teztest.databinding.ListItemSubscribedAccountBinding


class SubscribedAccountsAdapter(val infoClickListener: InfoClickListener, val unsubscribeClickListener: UnsubscribeClickListener ) :
    androidx.recyclerview.widget.ListAdapter<SubscribedAccount, SubscribedAccountsAdapter.ViewHolder>(
        SubscribedAccountsDiffCallback()
    ) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, infoClickListener, unsubscribeClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListItemSubscribedAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: SubscribedAccount, infoClickListener: InfoClickListener, unsubscribeClickListener: UnsubscribeClickListener
        ) {
            binding.account = item
            if (item.accountAddress.get(0)=='K')
            binding.subscriptionsAccountTitle.text = "Smart Contract"
            else
                    binding.subscriptionsAccountTitle.text = "User Account"
            binding.subscriptionsAccountAddress.text = item.accountAddress
            binding.infoClickListener = infoClickListener
            binding.unsubscribeClickListener = unsubscribeClickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemSubscribedAccountBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SubscribedAccountsDiffCallback : DiffUtil.ItemCallback<SubscribedAccount>() {
    override fun areItemsTheSame(
        oldItem: SubscribedAccount,
        newItem: SubscribedAccount
    ): Boolean {
        return oldItem.accountId == newItem.accountId
    }

    override fun areContentsTheSame(
        oldItem: SubscribedAccount,
        newItem: SubscribedAccount
    ): Boolean {
        return oldItem == newItem
    }

}

class InfoClickListener(val clickListener: (accountAddress: String) -> Unit) {
    fun onInfoClick(account: SubscribedAccount) = clickListener(account.accountAddress)
}
class UnsubscribeClickListener(val clickListener: (accountAddress: String) -> Unit) {
    fun onUnsubscribeClick(account: SubscribedAccount) = clickListener(account.accountAddress)
}


