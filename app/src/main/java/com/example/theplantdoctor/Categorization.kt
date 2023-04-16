package com.example.theplantdoctor

import android.content.res.AssetManager
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class Categorization(assetManager: AssetManager, modelPath: String, labelPath: String, inputSize: Int) {
    private val GVN_INP_SZ: Int = inputSize
    private val PHOTO_SDEVIATE=255.0f
    private val GREAT_OUTCOME_MXX = 3
    private val PITNR: Interpreter
    private var ROW_LINE: List<String>
    private val IMAGE_PXL_SZ: Int = 3
    private val PHOTO_MEN = 0
    private val POINT_THRHLDD=0.4f

    data class Categorization (
        var id: String = "",
        var title: String = "",
        var confidence: Float = 0F
    ){
        override fun toString(): String {
            return "Infection: $title, Confidence = $confidence"
        }
    }
    init {
        PITNR = Interpreter(loadModelFile(assetManager, modelPath))
        ROW_LINE = loadLabelList(assetManager, labelPath)
    }

    private fun loadLabelList(assetManager: AssetManager, labelPath: String): List<String> {
        return assetManager.open(labelPath).bufferedReader().useLines { it.toList() }
    }

    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength

        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    fun recognizeImage(bitmap: Bitmap): List<com.example.theplantdoctor.Categorization.Categorization> {
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, GVN_INP_SZ, GVN_INP_SZ, false)
        val byteBuffer = convertBitmapToByteBuffer(scaledBitmap)
        val result = Array(1){FloatArray(ROW_LINE.size)}
        PITNR.run(byteBuffer, result)

        return  getSortedResult(result)
    }

    private fun getSortedResult(result: Array<FloatArray>): List<com.example.theplantdoctor.Categorization.Categorization> {
        val pq = PriorityQueue(
            GREAT_OUTCOME_MXX,
            kotlin.Comparator<com.example.theplantdoctor.Categorization.Categorization> {
                    (_, _, confidence1), (_, _, confidence2)
                -> java.lang.Float.compare(confidence1, confidence2)* -1 }
        )
        for ( i in ROW_LINE.indices){
            val confidence = result[0][i]
            if(confidence >= POINT_THRHLDD){
                pq.add(com.example.theplantdoctor.Categorization.Categorization(
                    ""+i,
                    if (ROW_LINE.size>i) ROW_LINE[i] else "Unknown", confidence
                ))
            }
        }

        val recognitions = ArrayList<com.example.theplantdoctor.Categorization.Categorization>()
        val recognitionSize = Math.min(pq.size, GREAT_OUTCOME_MXX)
        for(i in 0 until recognitionSize){
            recognitions.add(pq.poll())
        }
        return recognitions
    }

    private fun convertBitmapToByteBuffer(scaledBitmap: Bitmap?): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4*GVN_INP_SZ*GVN_INP_SZ*IMAGE_PXL_SZ)
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValue = IntArray(GVN_INP_SZ*GVN_INP_SZ)

        scaledBitmap!!.getPixels(intValue, 0, scaledBitmap.width,0,0,scaledBitmap.width,scaledBitmap.height)
        var pixel = 0
        for(i in 0 until GVN_INP_SZ) {
            for(j in 0 until GVN_INP_SZ) {
                val `val` = intValue[pixel++]

                byteBuffer.putFloat((((`val`.shr(16) and 0xFF)-PHOTO_MEN)/PHOTO_SDEVIATE))
                byteBuffer.putFloat((((`val`.shr(8) and 0xFF)-PHOTO_MEN)/PHOTO_SDEVIATE))
                byteBuffer.putFloat((((`val` and 0xFF)-PHOTO_MEN)/PHOTO_SDEVIATE))
            }
        }
        return  byteBuffer
    }


}