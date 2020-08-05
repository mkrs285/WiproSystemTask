package com.example.wiprosystemtask.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.wiprosystemtask.R
import com.example.wiprosystemtask.WiproSystemTaskApplication
import com.example.wiprosystemtask.base.BaseActivity
import com.example.wiprosystemtask.dialog.CustomDialog
import com.example.wiprosystemtask.fragment.FactsFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity(),HasAndroidInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    lateinit var dialog: AlertDialog

    override fun androidInjector() = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialog = CustomDialog.getProgressDialog(this, resources.getString(R.string.loader))

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FactsFragment.newInstance())
            .commit()
    }


}