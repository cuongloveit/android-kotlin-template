package com.givestech.rxfile

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.support.annotation.DrawableRes
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

object RxSaveFile {
    fun setPhotoPath(context: Context, photoPath: String) {
        SharePreferencesHelper.setPhotoPath(context, photoPath)
    }

    fun saveBitmap(fileName: String, resources: Resources, @DrawableRes resId: Int): Flowable<Boolean> {
        val path = Environment.getExternalStorageDirectory().absolutePath + "/YouKara/Videos/$fileName"
        val folder = File(path).parentFile
        if (! folder.exists()) {
            folder.mkdirs()
        }


        return Flowable.just(path)
                .flatMap { Flowable.just(BitmapFactory.decodeResource(resources, resId)) }
                .flatMap { Flowable.just(it.compress(Bitmap.CompressFormat.PNG, 100, FileOutputStream(path))) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


    }

    fun savePhoto(bitmap: Bitmap, context: Context, fileName: String): Flowable<Boolean> {
        val path = SharePreferencesHelper.getPhotoPath(context) + "/$fileName"
        val folder = File(path).parentFile
        if (! folder.exists()) {
            folder.mkdirs()
        }
        return Flowable.just(path)
                .flatMap { Flowable.just(bitmap) }
                .flatMap { Flowable.just(it.compress(getFormat(fileName), 100, FileOutputStream(path))) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


    }

    private fun getFormat(fileName: String): Bitmap.CompressFormat {
        if (fileName.endsWith(".png", true)) {
            return Bitmap.CompressFormat.PNG
        }

        return Bitmap.CompressFormat.JPEG
    }

}