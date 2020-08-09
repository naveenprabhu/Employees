package com.me.employees

import android.app.Application
import com.me.employees.di.AppComponent
import com.me.employees.di.DaggerAppComponent
import timber.log.Timber

class EmployeeApplication : Application(){

   val appComponent: AppComponent by lazy { initalizeAppComponent() }

   private fun initalizeAppComponent(): AppComponent {
      return DaggerAppComponent.factory().create(applicationContext)
   }

   override fun onCreate() {
      super.onCreate()
      if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
   }
}