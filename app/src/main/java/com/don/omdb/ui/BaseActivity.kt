package com.don.omdb.ui

import android.content.DialogInterface
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.don.omdb.R
import com.google.android.material.snackbar.Snackbar

/**
 * Created by gideon on 02,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
abstract class BaseActivity : AppCompatActivity() {


    fun showBackArrow() {
        val toolbar = supportActionBar
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true)
        } else {
            showErrorAlert("Toolbar is not set")
        }
    }

    fun hideBackArrow() {
        val toolbar = supportActionBar
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(false)
        } else {
            showErrorAlert("Toolbar is not set")
        }
    }


    //base activity
    fun setToolbarTitle(title: String) {
        val toolbar = supportActionBar
        if (toolbar != null) {
            toolbar.title = title
            toolbar.elevation = 0f
        } else {
            showErrorAlert("Toolbar is not set")
        }
    }

    fun setToolbarClose(title: String) {
        val toolbar = supportActionBar
        if (toolbar != null) {
            toolbar.title = title
            toolbar.elevation = 0f
            toolbar.setDisplayHomeAsUpEnabled(true)
            toolbar.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        } else {
            showErrorAlert("Toolbar is not set")
        }
    }


    fun setToolbarTitle(
            tvTitle: TextView,
            tvSubTitle: TextView,
            title: String,
            subTitle: String
    ) {
        val toolbar = supportActionBar
        if (toolbar != null) {

            tvTitle.text = title
            tvSubTitle.text = subTitle

        } else {
            showErrorAlert("Toolbar is not set")
        }
    }

    fun showErrorAlert(message: String) {
        val alertDialog = AlertDialog.Builder(this)
                .setTitle(R.string.alert_title)
                .setMessage(message)
                .setPositiveButton(
                        R.string.alert_close,
                        DialogInterface.OnClickListener { dialog, which ->
                            if (message.contains("404")) {
//                        logout()
                            } else {
                                dialog.dismiss()
                            }
                        })
                .create()
        alertDialog.show()

        //set dialog button color
        //get color
        val buttonColor = resources.getColor(R.color.colorAccent)
        //get the positive button
        val pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        //set the color to the buttton
        pbutton.setTextColor(buttonColor)
    }


    fun showSnackBar(string: String) {
        Snackbar.make(findViewById(android.R.id.content), string, Snackbar.LENGTH_LONG)
                .show()
    }

    fun showTopSnackBar(string: String) {
        val snackBar =
                Snackbar.make(findViewById(android.R.id.content), string, Snackbar.LENGTH_LONG)
        val view = snackBar.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
        view.layoutParams = params
        snackBar.show()
    }
}