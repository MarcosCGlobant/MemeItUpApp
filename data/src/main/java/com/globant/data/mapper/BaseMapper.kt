package com.globant.data.mapper

interface BaseMapper<E, D> {

    fun transformMeme(type: E): D
    fun transformToData(type: D): E
}