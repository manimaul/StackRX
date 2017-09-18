@file:JvmName("Injector")
package com.example.injection

import com.example.injection.component.ApplicationComponent
import com.example.injection.component.DaggerApplicationComponent
import com.example.injection.module.AndroidModule
import com.example.injection.module.ServicesModule
import com.example.main.application.StackRXApp

private var applicationComponent: ApplicationComponent? = null

/**
 * Get the application scope Dagger2 component injector.
 *
 * @return the application scope injector.
 */
val applicationScope: ApplicationComponent get() = applicationComponent!!

/**
 * Build the Dagger Application (Singleton) Scope injection component.
 *
 * @param application the application.
 */
fun buildApplicationComponent(application: StackRXApp) {
    applicationComponent = DaggerApplicationComponent.builder()
            .androidModule(AndroidModule(application))
            .servicesModule(ServicesModule())
            .build()
}
