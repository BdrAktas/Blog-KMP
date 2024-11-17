package org.aktasbedir.blogmultiplatform.utils

object Constants {
    const val FONT_FAMILY = "Roboto"
    const val SIDE_PANEL_WIDTH = 250
    const val PAGE_MAX_WIDTH = 1920
    const val COLLAPSED_PANEL_HEIGHT = 100
    //api-key parametresini hardcoded olarak ekledik bunu sonra local ya da environment variable cevirebiliriz
    // ayrica max-length parametresini ekledik bunu direk and operatoru(&) ile ekleyebiliriz -> 180 character ile sinirladik
    const val HUMOR_API_URL = "https://api.humorapi.com/jokes/random?api-key=f0cac1365ead42e58b2ee94684b45b56&max-length=180"
}

object Id {
    const val userNameInput = "usernameInput"
    const val passwordInput = "passwordInput"
    const val svgParent = "svgParent"
    const val vectorIcon = "vectorIcon"
    const val navigationText = "navigationText"
}