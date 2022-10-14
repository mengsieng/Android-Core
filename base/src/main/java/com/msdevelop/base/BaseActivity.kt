package com.msdevelop.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {

    open lateinit var binding: VB

    abstract fun setViewBing(): VB
    abstract fun onCreateView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setViewBing()
        setContentView(binding.root)
        onCreateView()
    }

}