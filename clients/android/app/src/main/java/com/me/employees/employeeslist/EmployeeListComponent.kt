package com.me.employees.employeeslist

import com.me.employees.di.ActivityScope
import dagger.Subcomponent

@Subcomponent
@ActivityScope
interface EmployeeListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): EmployeeListComponent
    }

    fun inject(activity: EmployeesListActivity)

    fun inject(adapter: EmployeeAdapter)
}