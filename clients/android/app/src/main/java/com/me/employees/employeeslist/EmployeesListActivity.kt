package com.me.employees.employeeslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.me.employees.EmployeeApplication
import com.me.employees.R
import kotlinx.android.synthetic.main.employee_list.*
import javax.inject.Inject

class EmployeesListActivity : AppCompatActivity(), EmployeeListView {

    @Inject
    lateinit var employeeAdapter: EmployeeAdapter

    @Inject
    lateinit var presenter: EmployeeListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as EmployeeApplication).appComponent.employeeListComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.employee_list)
        swiperefresh.setOnRefreshListener { getEmployees()
        }
        setRecyclerView()
        presenter.setView(this)
        getEmployees()
    }

    private fun getEmployees() {
        displaySwipeRefresh()
        presenter.getEmployees()
    }

    private fun setRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = employeeAdapter
        }
    }

    override fun updateAdapter() {
        employeeAdapter.notifyDataSetChanged()
    }

    override fun displayError() {
        var alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle(getString(R.string.error_title))
        alertBuilder.setMessage(getString(R.string.error_message))
        alertBuilder.setPositiveButton(getString(R.string.ok_button), null)
        alertBuilder.show()
    }

    private fun displaySwipeRefresh(){
        swiperefresh.isRefreshing = true
    }

    override fun dismissSwipeRefresh(){
        swiperefresh.isRefreshing = false
    }


}