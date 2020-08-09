package com.me.employees.di

import android.content.Context
import com.me.employees.employeeslist.EmployeeListComponent
import com.me.employees.interceptor.ForceCacheInterceptor
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppSubcomponents::class, NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun employeeListComponent() : EmployeeListComponent.Factory

    fun forceCacheInterceptor() : ForceCacheInterceptor

}
