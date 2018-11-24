package com.example.deepak.albumlist

import android.os.Bundle
import com.example.deepak.albumlist.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init() {
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, HomeFragment::class.simpleName).commit()

    }
}
