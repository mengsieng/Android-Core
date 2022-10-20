package com.msdevelop.base.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {

    open lateinit var binding: VB

    abstract fun setViewBinding(): VB
    abstract fun onCreateView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setViewBinding()
        setContentView(binding.root)
        onCreateView()
    }

}