package spartons.com.firebasesocialauthentication.activities.social.ui

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_social.*
import spartons.com.firebasesocialauthentication.R
import spartons.com.firebasesocialauthentication.activities.BaseActivity
import spartons.com.firebasesocialauthentication.activities.social.data.AuthType
import spartons.com.firebasesocialauthentication.activities.social.data.toAuthType
import spartons.com.firebasesocialauthentication.activities.social.viewModel.SocialActivityViewModel
import spartons.com.firebasesocialauthentication.activities.social.viewModel.SocialActivityViewModel.Companion.RC_GOOGLE_SIGN_IN_CODE
import spartons.com.firebasesocialauthentication.extensions.applyColorToAllDescendantsAndDisableState
import spartons.com.firebasesocialauthentication.extensions.enableAllDescendantsAndApplyPreviousColor
import spartons.com.firebasesocialauthentication.extensions.visible
import spartons.com.firebasesocialauthentication.helper.CustomMaterialDialog.showSimpleDialog
import spartons.com.firebasesocialauthentication.helper.CustomMaterialDialog.showSingleChoiceDialog
import spartons.com.firebasesocialauthentication.util.MainActivityArgs

class SocialActivity :
    BaseActivity<SocialActivityViewModel>(
        R.layout.activity_social,
        SocialActivityViewModel::class
    ) {

    private lateinit var socialIncludeLayout: ViewGroup

    override val isFullscreen: Boolean
        get() = true

    private companion object {
        private val facebook_permissions = mutableListOf("email", "public_profile")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        socialIncludeLayout = findViewById(R.id.loginSocialLayout)

        socialIncludeLayout.findViewById<MaterialButton>(R.id.socialTwitterButton)
            .setOnClickListener { doSocialAuth(AuthType.TWITTER) }

        socialIncludeLayout.findViewById<MaterialButton>(R.id.socialGoogleButton)
            .setOnClickListener { doSocialAuth(AuthType.GOOGLE) }

        socialIncludeLayout.findViewById<MaterialButton>(R.id.socialFacebookButton)
            .setOnClickListener { doSocialAuth(AuthType.FACEBOOK) }

        socialIncludeLayout.findViewById<MaterialButton>(R.id.socialGithubButton)
            .setOnClickListener { doSocialAuth(AuthType.GITHUB) }

        observe(viewModel.uiState) { authModel ->
            loginProgressBar.visible(authModel.showProgress)

            if (authModel.success) navigateActivity()
            else if (authModel.error != null && !authModel.error.consumed)
                authModel.error.consume()?.let { pair ->
                    showSimpleDialog(this, pair.second) {
                        doSocialAuth(pair.first)
                    }
                    socialIncludeLayout.enableAllDescendantsAndApplyPreviousColor<MaterialButton>()
                }
            else if (authModel.showAllLinkProvider != null && !authModel.showAllLinkProvider.consumed)
                authModel.showAllLinkProvider.consume()?.let { pair ->
                    showSingleChoiceDialog(
                        this, pair.second, pair.first,
                        negativeButtonClickListener = {
                            socialIncludeLayout.enableAllDescendantsAndApplyPreviousColor<MaterialButton>()
                        }) {
                        val authType = it.toAuthType()
                        doSocialAuth(authType)
                    }
                }
        }
    }

    private fun navigateActivity() {
        MainActivityArgs().launch(this)
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.firebaseAuth.currentUser != null)
            navigateActivity()
    }

    private fun disableButtons(views: ViewGroup) {
        views.applyColorToAllDescendantsAndDisableState<MaterialButton>(R.color.color_on_surface_emphasis_disabled)
    }

    private fun doSocialAuth(authType: AuthType) {
        when (authType) {
            AuthType.GOOGLE -> viewModel.googleSignIn().also {
                startActivityForResult(it, RC_GOOGLE_SIGN_IN_CODE)
            }
            AuthType.TWITTER -> {
                disableButtons(socialIncludeLayout)
                viewModel.doTwitterAuthentication(this)
            }
            AuthType.FACEBOOK -> {
                disableButtons(socialIncludeLayout)
                viewModel.loginManager.logInWithReadPermissions(this, facebook_permissions)
            }
            AuthType.EMAIL -> {
                disableButtons(socialIncludeLayout)
                viewModel.fetchAllProviderForEmail(authType.authValue)
            }
            AuthType.GITHUB -> {
                disableButtons(socialIncludeLayout)
                viewModel.doGithubAuthentication(this)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.handleOnActivityResult(requestCode, resultCode, data)
    }
}
