package com.example.injection.component

import com.example.injection.module.AndroidModule
import com.example.injection.module.ServicesModule
import com.example.main.activity.StackRXActivity
import com.example.main.fragment.QuestionsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Dependency injection component for the Singleton / Application scope
 */
@Singleton
@Component(modules = arrayOf(AndroidModule::class, ServicesModule::class))
interface ApplicationComponent {
    fun inject(stackRXActivity: StackRXActivity)
    fun inject(questionsFragment: QuestionsFragment)
}
