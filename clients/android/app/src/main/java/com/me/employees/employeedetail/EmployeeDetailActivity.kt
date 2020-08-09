package com.me.employees.employeedetail

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.me.employees.EmployeeApplication
import com.me.employees.R
import com.me.employees.utils.ImageUtil
import kotlinx.android.synthetic.main.employee_detail.*
import javax.inject.Inject

class EmployeeDetailActivity: AppCompatActivity(), EmployeeDetailView {

    @Inject
    lateinit var presenter: EmployeeDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as EmployeeApplication).appComponent.employeeDetailComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.employee_detail)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.setView(this)

        val employeeId : Int =intent.getIntExtra("emp", 0)
        presenter.getEmployeeDetail(employeeId)
    }


    override fun updateImage(imageString: String?) {
        imageString?.let {
            employeeImageView.setImageBitmap(ImageUtil.getImageBitmap(imageString))
        }
    }

    override fun displayError() {
        var alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle(getString(R.string.error_title))
        alertBuilder.setMessage(getString(R.string.error_message))
        alertBuilder.setPositiveButton(getString(R.string.ok_button), null)
        alertBuilder.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

