package com.q8intouch.ecovve.util.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

fun <I,O> LiveData<I>.map(mapFunction: (I)->O):LiveData<O>{
    return Transformations.map(this,mapFunction)
}

fun <I,O> LiveData<I>.switchMap(mapFunction: (I)->LiveData<O>):LiveData<O>{
    return Transformations.switchMap(this,mapFunction)
}

fun <T> MutableLiveData<T>.refresh(){
    this.value = value
}