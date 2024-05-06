package com.q8intouch.ecovve.core

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay

import io.reactivex.Observable


class Store<T> {

    private val storeSubject: Relay<T>

    constructor() {
        this.storeSubject = BehaviorRelay.create<T>().toSerialized()
    }

    constructor(defaultValue: T) {
        this.storeSubject = BehaviorRelay.createDefault(defaultValue).toSerialized()
    }

    fun observe(): Observable<T> {
        return storeSubject.hide()
            .distinctUntilChanged()
    }

    fun publish(value: T) {
        storeSubject.accept(value)
    }
}