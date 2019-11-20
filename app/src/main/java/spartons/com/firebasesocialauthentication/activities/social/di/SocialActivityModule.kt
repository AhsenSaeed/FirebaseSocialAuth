package spartons.com.firebasesocialauthentication.activities.social.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import spartons.com.firebasesocialauthentication.activities.social.viewModel.SocialActivityViewModel
import spartons.com.firebasesocialauthentication.backend.MyCustomApp


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/20/19}
 */

val socialActivityModule = module {
    viewModel { SocialActivityViewModel(androidApplication() as MyCustomApp, get()) }
}