package com.oop_task.presentation

import androidx.fragment.app.Fragment

interface NavigationController {
    fun navigate(fragment: Fragment)
    fun back()
}