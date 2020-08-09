package com.me.employees.service

import com.me.employees.model.Employee
import com.me.employees.utils.Constants
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeService {

    @GET(Constants.URL_EMPLOYEE_LIST)
    fun getEmployees() : Observable<List<Employee>>

    @GET(Constants.URL_EMPLOYEE_DETAIL)
    fun getEmployeeDetail(@Path("id") employeeId: Int) : Observable<Employee>
}