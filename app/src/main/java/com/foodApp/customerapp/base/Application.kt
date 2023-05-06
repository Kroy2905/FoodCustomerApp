package com.foodApp.customerapp.base

import android.app.Application
import android.content.Intent
import android.graphics.Typeface
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.foodApp.customerapp.BuildConfig
import com.foodApp.customerapp.R
import com.foodApp.customerapp.api.APIservice
import com.foodApp.customerapp.api.RetrofitHelper


class Application : Application() {
    var TAG = this@Application.javaClass.simpleName
    var initIntent: Intent? = null
    var versionCode = 0
    lateinit var repository: BaseRepository
    override fun onCreate() {
        super.onCreate()
        initRepo()
        versionCode = BuildConfig.VERSION_CODE
        versionName = BuildConfig.VERSION_NAME
        Log.d(TAG, "Version : $versionName")
        setDefaultFont()
    }

    private fun initRepo() {
        val logService = RetrofitHelper.getInstance().create(APIservice::class.java)
        repository = BaseRepository(logService)
    }

    private fun setDefaultFont() {
        val customFont = Typeface.createFromAsset(assets, "font/app_font1.ttf")
        val defaultFont = ResourcesCompat.getFont(this, R.font.app_font1)
        if (defaultFont != null) {
            val newFont = Typeface.create(customFont, Typeface.NORMAL)
            val newDefaultFont = Typeface.create(defaultFont, Typeface.NORMAL)
            // Set the default font for the app
            try {
                val field = Typeface::class.java.getDeclaredField("DEFAULT")
                field.isAccessible = true
                field.set(null, newDefaultFont)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            // Set the default monospace font for the app
            try {
                val field = Typeface::class.java.getDeclaredField("MONOSPACE")
                field.isAccessible = true
                field.set(null, newFont)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }





    override fun onTerminate() { //this function will not be called on actual devices
        Log.d(TAG, "Application terminating..")
        applicationContext.stopService(initIntent)
        super.onTerminate()
    } //    private void initDatabaseWithIntegrity() {

    //        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance(getApplicationContext()).getReadableDatabase();
    //        /*Runs 'pragma integrity_check' on the given database (and all the attached databases)
    //        and returns true if the given database (and all its attached databases)
    //        pass integrity_check, false otherwise.*/
    //        if(sqLiteDatabase.isDatabaseIntegrityOk()){
    //            Log.d(TAG, "SQLite Database Integrity check is successful!");
    //        } else{
    //            Log.d(TAG, "SQLite Database is corrupted!");
    //        }
    //    }
    //    private void initCameraCheckup() {
    //        PackageManager packageManager = getApplicationContext().getPackageManager();
    //        // if device supports camera?
    //        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
    //            Log.d(TAG, "CameraCheck : This device has camera!");//yes
    //        }else{
    //            Log.d(TAG, "CameraCheck : This device has no camera!");//no
    //        }
    //    }
    companion object {

        var versionName: String? = null
    }
}