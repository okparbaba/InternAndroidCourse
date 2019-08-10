package com.softwarefactorymm.loginregisterretrofit.server

import com.softwarefactorymm.loginregisterretrofit.models.User

class ServerRequest {

    private var operation: String? = null
    private var user: User? = null

    fun setOperation(operation: String) {
        this.operation = operation
    }

    fun setUser(user: User) {
        this.user = user
    }
}