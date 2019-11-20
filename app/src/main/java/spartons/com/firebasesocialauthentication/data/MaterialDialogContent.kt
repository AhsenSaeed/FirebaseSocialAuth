package spartons.com.firebasesocialauthentication.data

import androidx.annotation.StringRes


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/13/19}
 */

data class MaterialDialogContent(
    @StringRes val positiveText: Int,
    @StringRes val content: Int? = null,
    @StringRes val title: Int,
    @StringRes val negativeText: Int? = null,
    val contentText: String? = null
)