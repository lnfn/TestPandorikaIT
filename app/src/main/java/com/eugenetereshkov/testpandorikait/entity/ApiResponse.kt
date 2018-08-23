package com.eugenetereshkov.testpandorikait.entity


abstract class ApiResponse<T> {
    abstract val results: T
}
