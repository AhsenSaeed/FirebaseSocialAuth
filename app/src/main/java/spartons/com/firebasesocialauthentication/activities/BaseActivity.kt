package spartons.com.firebasesocialauthentication.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.DefinitionParameters
import org.koin.core.parameter.emptyParametersHolder
import kotlin.reflect.KClass


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 10/17/19}
 */

@SuppressLint("Registered")
abstract class BaseActivity<out VM : ViewModel>(
    private val layoutResourceId: Int,
    viewModelClass: KClass<VM>,
    viewmodelParameters: DefinitionParameters = emptyParametersHolder()
) : AppCompatActivity() {

    protected open val viewModel: VM by viewModel(clazz = viewModelClass)

    protected open val isFullscreen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isFullscreen) setFullScreenWindow()
        setContentView(layoutResourceId)
    }

    protected fun <T, LD : LiveData<T>> observe(liveData: LD, onChanged: (T) -> Unit) {
        liveData.observe(this, Observer {
            it?.let(onChanged)
        })
    }

    private fun setFullScreenWindow() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}