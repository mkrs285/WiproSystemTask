package com.example.wiprosystemtask.activity

import android.os.Bundle
import com.example.wiprosystemtask.R
import com.example.wiprosystemtask.base.BaseActivity
import com.example.wiprosystemtask.fragment.FactsFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Adding Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FactsFragment.newInstance())
            .commit()
    }


}