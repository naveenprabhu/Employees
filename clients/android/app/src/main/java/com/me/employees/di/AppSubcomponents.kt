package com.me.employees.di

import com.me.employees.employeeslist.EmployeeListComponent
import dagger.Module

@Module(
    subcomponents = [
    EmployeeListComponent::class
    ]
)
class AppSubcomponents