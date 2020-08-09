package com.me.employees.employeeslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.me.employees.R
import com.me.employees.model.Employee
import com.me.employees.utils.ImageUtil
import kotlinx.android.synthetic.main.employee.view.*
import javax.inject.Inject


class EmployeeAdapter @Inject constructor (private val presenter: EmployeeListPresenter) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    var onItemClick: ((Employee) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return presenter.employeeList?.count() ?: 0
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bindFilter(presenter.getEmployeeAtPosistion(position))
    }

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindFilter(employee: Employee) {
            itemView.nameTextView.text = employee.name
            itemView.genderTextView.text = employee.genderVal
            itemView.dobTextView.text = employee.birthDate
            employee.base64ThumbImageString?.let {
                itemView.imageView.setImageBitmap(ImageUtil.getImageBitmap(employee.base64ThumbImageString))
            }

            itemView.setOnClickListener{
                onItemClick?.invoke(employee)
            }
        }



    }
}