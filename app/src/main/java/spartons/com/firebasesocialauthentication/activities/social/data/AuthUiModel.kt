package spartons.com.firebasesocialauthentication.activities.social.data

import spartons.com.firebasesocialauthentication.data.MaterialDialogContent
import spartons.com.firebasesocialauthentication.util.Event


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/13/19}
 */

data class AuthUiModel(
    val showProgress: Boolean,
    val error: Event<Pair<AuthType, MaterialDialogContent>>?,
    val success: Boolean,
    val showAllLinkProvider: Event<Pair<List<String>, MaterialDialogContent>>?
)