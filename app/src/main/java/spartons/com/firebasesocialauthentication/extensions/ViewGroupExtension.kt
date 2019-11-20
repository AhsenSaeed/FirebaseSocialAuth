package spartons.com.firebasesocialauthentication.extensions

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.view.children
import com.google.android.material.button.MaterialButton


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/20/19}
 */

fun <T : MaterialButton> ViewGroup.applyColorToAllDescendantsAndDisableState(@ColorRes color: Int) {
    for (view in children) {
        if (view is MaterialButton) {
            view.tag = view.backgroundTintList
            view.backgroundTintList = colorStateList(color)
            view.isEnabled = false
        } else if (view is ViewGroup)
            view.applyColorToAllDescendantsAndDisableState<T>(color)
    }
}

fun <T : MaterialButton> ViewGroup.enableAllDescendantsAndApplyPreviousColor() {
    for (view in children) {
        if (view is MaterialButton) {
            val tag = view.tag ?: return
            val colorStateList: ColorStateList = tag as ColorStateList
            view.backgroundTintList = colorStateList
            view.isEnabled = true
        } else if (view is ViewGroup)
            view.enableAllDescendantsAndApplyPreviousColor<T>()
    }
}

fun ViewGroup.inflateView(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ViewGroup.addAllViews(views: List<View>) {
    for (view in views)
        addView(view)
}