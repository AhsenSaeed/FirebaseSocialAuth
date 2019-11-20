package spartons.com.firebasesocialauthentication.koinDI

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/20/19}
 */

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
}