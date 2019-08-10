package com.softwarefactorymm.loginregisterretrofit.models

class User {
    var name: String? = null
    var email: String? = null
    val unique_id: String? = null
    private var password: String? = null
    private var old_password: String? = null
    private var new_password: String? = null
    private var code:String? = null
    fun setCode(code: String) {
        this.code = code
    }


    fun setPassword(password: String) {
        this.password = password
    }

    fun setOld_password(old_password: String) {
        this.old_password = old_password
    }

    fun setNew_password(new_password: String) {
        this.new_password = new_password
    }

}