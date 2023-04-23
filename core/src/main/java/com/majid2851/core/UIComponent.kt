package com.majid2851.core

sealed class UIComponent
{
    data class Dialog(
        val title:String,
        val description:String
    ):UIComponent()

    data class None(
        val message:String
    ):UIComponent()







}