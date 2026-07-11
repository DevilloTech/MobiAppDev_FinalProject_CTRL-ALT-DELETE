package com.example.technicalsumm2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicalsumm2.databinding.ItemMemberBinding

class MemberAdapter(
    private val members: List<Member>
) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    inner class MemberViewHolder(val binding: ItemMemberBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {

        val binding = ItemMemberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {

        val member = members[position]

        holder.binding.headerImage.setImageResource(member.image)
        holder.binding.title.text = member.name
        holder.binding.subhead.text = member.role
        holder.binding.body.text = member.description
    }

    override fun getItemCount(): Int = members.size
}