package com.akingyin.androidjetpackdemo.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.akingyin.androidjetpackdemo.R
import com.akingyin.androidjetpackdemo.databinding.ItemUserBinding
import com.akingyin.androidjetpackdemo.entity.User
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @ Description:
 * @author king
 * @ Date 2019/1/14 11:15
 * @version V1.0
 */
class UserListAdapter constructor(layoutResId :Int) :BaseQuickAdapter<User,UserListAdapter.userViewHolder>(layoutResId) {


    override fun convert(helper: userViewHolder?, item: User?) {
        var  viewHolder  = helper?.getDataBind()
        viewHolder?.itemuser = item
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {
        val binding = DataBindingUtil.inflate<ItemUserBinding>(mLayoutInflater, layoutResId, parent, false)
            ?: return super.getItemView(layoutResId, parent)
        val view = binding.root
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        return view
    }

    class  userViewHolder(view: View) : BaseViewHolder(view){

        fun  getDataBind() : ItemUserBinding{
            return  itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ItemUserBinding
        }
    }
}