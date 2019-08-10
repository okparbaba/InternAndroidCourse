package com.example.filereadwriteexternalstorage

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

import android.util.Log

class FileOperations {

    fun write(fname: String, fcontent: String): Boolean? {
        try {

            val fpath = "/sdcard/$fname.txt"

            val file = File(fpath)

            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile()
            }

            val fw = FileWriter(file.absoluteFile)
            val bw = BufferedWriter(fw)
            bw.write(fcontent)
            bw.close()

            Log.d("Suceess", "Sucess")
            return true

        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }

    }

    fun read(fname: String): String? {

        var br: BufferedReader? = null
        var response: String? = null

        try {

            val output = StringBuffer()
            val fpath = "/sdcard/$fname.txt"

            br = BufferedReader(FileReader(fpath))
            var line = ""
            do {
                line = br.readLine()
                if (line == null) break
                output.append(line + "n")
            } while (true)

            response = output.toString()

        } catch (e: IOException) {
            e.printStackTrace()
            return null

        }

        return response

    }
}