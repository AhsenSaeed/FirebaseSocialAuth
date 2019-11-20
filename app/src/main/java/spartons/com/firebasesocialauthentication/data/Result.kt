package spartons.com.firebasesocialauthentication.data


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/14/19}
 */

sealed class Result<out T : Any> {

    data class Failed(val message: String) : Result<String>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failed -> "Failed[Message=[$message]"
            is Error -> "Error[exception=$exception]"
        }

    }
}