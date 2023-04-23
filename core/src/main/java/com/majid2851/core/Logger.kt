package com.majid2851.core


class Logger(
    private val tag:String,
    private val isDebug:Boolean=false
)
{
    fun log(msg:String)
    {
        if (!isDebug){

        }else{
            printLogD(tag,msg)
        }
    }
    companion object Factory{
        fun buildDebug(tag:String): Logger {
            return Logger(tag=tag, isDebug = true)
        }
        fun buildRelease(tag:String): Logger {
            return Logger(tag=tag, isDebug = false)
        }
    }

    private fun printLogD(tag: String, msg: String) {
        println("$tag:$msg")
    }


}