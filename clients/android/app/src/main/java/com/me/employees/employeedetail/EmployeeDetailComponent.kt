package com.me.employees.employeedetail

import com.me.employees.di.ActivityScope
import dagger.Subcomponent

@Subcomponent
@ActivityScope
interface EmployeeDetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): EmployeeDetailComponent
    }

    fun inject(activity: EmployeeDetailActivity)

}