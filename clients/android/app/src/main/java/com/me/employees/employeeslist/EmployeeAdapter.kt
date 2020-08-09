package com.me.employees.employeeslist

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.me.employees.R
import com.me.employees.model.Employee
import kotlinx.android.synthetic.main.employee.view.*
import javax.inject.Inject


class EmployeeAdapter @Inject constructor (private val presenter: EmployeeListPresenter) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

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

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindFilter(employee: Employee) {
            itemView.nameTextView.text = employee.name
            itemView.genderTextView.text = employee.genderVal
            itemView.dobTextView.text = employee.birthDate
            itemView.imageView.setImageBitmap(getThumbImageBitmap(employee.base64ImageString))
        }

        fun getThumbImageBitmap(base64String: String): Bitmap {
            var imageBytes = Base64.decode(base64String, Base64.DEFAULT)
            var decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            return decodedImage
        }

    }
}