package spartons.com.firebasesocialauthentication.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/20/19}
 */

fun Context.launch(
    options: Bundle? = null,
    intent: Intent
) = startActivity(intent, options)
