package com.android_dev_challenge.sliide.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android_dev_challenge.sliide.BR
import com.android_dev_challenge.sliide.R
import com.android_dev_challenge.sliide.remote_repository.webservice.get_user.GetUserApiResponse

class UserAdapter(val onLongItemClickListener: (user: GetUserApiResponse.User) -> Boolean) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var userList: List<GetUserApiResponse.User> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.adapter_list_item_user, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: GetUserApiResponse.User = userList[position]

        holder.run {
            bind(user)
            itemView.setOnLongClickListener{onLongItemClickListener(user)}
        }
    }

    override fun getItemCount() = userList.size

    override fun getItemViewType(position: Int) = R.layout.adapter_list_item_user

    class UserViewHolder(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: GetUserApiResponse.User) {
            binding.setVariable(BR.user, user)
            Log.i("TAG", "Binding Result: " + binding.setVariable(BR.user, user).toString())
            binding.executePendingBindings()
        }
    }
}