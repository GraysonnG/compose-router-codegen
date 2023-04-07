package com.blanktheevil.router

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ComposableRoute(val routeId: String)