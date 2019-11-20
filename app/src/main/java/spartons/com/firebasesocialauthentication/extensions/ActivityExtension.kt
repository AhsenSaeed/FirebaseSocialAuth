package spartons.com.firebasesocialauthentication.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/20/19}
 */

fun Activity.launch(
    requestCode: Int = -1,
    options: Bundle? = null,
    intent: Intent
) = startActivityForResult(intent, requestCode)