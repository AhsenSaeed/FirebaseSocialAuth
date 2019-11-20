package spartons.com.firebasesocialauthentication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import spartons.com.firebasesocialauthentication.R
import spartons.com.firebasesocialauthentication.util.SocialActivityArgs


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/20/19}
 */

class MainActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logoutButton.setOnClickListener {
            firebaseAuth.signOut()
            SocialActivityArgs().launch(this)
            finish()
        }
    }
}