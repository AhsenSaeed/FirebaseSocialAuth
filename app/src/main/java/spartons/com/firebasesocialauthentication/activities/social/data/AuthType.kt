package spartons.com.firebasesocialauthentication.activities.social.data


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 11/13/19}
 */

fun String.toAuthType(): AuthType {
    return when (this) {
        AuthType.FACEBOOK.authValue -> AuthType.FACEBOOK
        AuthType.TWITTER.authValue -> AuthType.TWITTER
        AuthType.GOOGLE.authValue -> AuthType.GOOGLE
        AuthType.GITHUB.authValue -> AuthType.GITHUB
        else -> AuthType.EMAIL
    }
}

enum class AuthType(var authValue: String) {
    FACEBOOK("facebook.com"),
    GOOGLE("google.com"),
    GITHUB("github.com"),
    TWITTER("twitter.com"),
    EMAIL("")
}