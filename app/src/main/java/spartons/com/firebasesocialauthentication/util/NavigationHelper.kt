package spartons.com.firebasesocialauthentication.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import spartons.com.firebasesocialauthentication.activities.MainActivity
import spartons.com.firebasesocialauthentication.activities.social.ui.SocialActivity
import spartons.com.firebasesocialauthentication.extensions.launch


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/20/19}
 */


interface ActivityArgs {

    fun intent(context: Context): Intent

    fun launch(context: Context, options: Bundle? = null) =
        context.launch(intent = intent(context), options = options)

    fun launch(activity: Activity, options: Bundle? = null, requestCode: Int = -1) =
        activity.launch(intent = intent(activity), requestCode = requestCode, options = options)
}

class MainActivityArgs : ActivityArgs {

    override fun intent(context: Context) = Intent(context, MainActivity::class.java)
}

class SocialActivityArgs : ActivityArgs {

    override fun intent(context: Context) = Intent(context, SocialActivity::class.java)
}