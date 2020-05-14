package com.example.memeitupapp.data.mapper

interface BaseMapper<E, D> {

    fun transformMeme(type: E): D

}