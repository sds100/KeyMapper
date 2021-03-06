package io.github.sds100.keymapper.util

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import androidx.annotation.RequiresPermission
import androidx.preference.PreferenceManager
import io.github.sds100.keymapper.Constants

/**
 * Created by sds100 on 31/12/2018.
 */

val Context.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)

/**
 * @return If the setting can't be found, it returns null
 */
inline fun <reified T> Context.getSystemSetting(name: String): T? {
    return try {
        when (T::class) {

            Int::class -> Settings.System.getInt(contentResolver, name) as T?
            String::class -> Settings.System.getString(contentResolver, name) as T?
            Float::class -> Settings.System.getFloat(contentResolver, name) as T?
            Long::class -> Settings.System.getLong(contentResolver, name) as T?

            else -> {
                throw Exception("Setting type ${T::class} is not supported")
            }
        }
    } catch (e: Settings.SettingNotFoundException) {
        null
    }
}

/**
 * @return If the setting can't be found, it returns null
 */
inline fun <reified T> Context.getSecureSetting(name: String): T? {
    return try {
        when (T::class) {

            Int::class -> Settings.Secure.getInt(contentResolver, name) as T?
            String::class -> Settings.Secure.getString(contentResolver, name) as T?
            Float::class -> Settings.Secure.getFloat(contentResolver, name) as T?
            Long::class -> Settings.Secure.getLong(contentResolver, name) as T?

            else -> {
                throw Exception("Setting type ${T::class} is not supported")
            }
        }
    } catch (e: Settings.SettingNotFoundException) {
        null
    }
}

/**
 * @return If the setting can't be found, it returns null
 */
inline fun <reified T> Context.getGlobalSetting(name: String): T? {
    return try {
        when (T::class) {

            Int::class -> Settings.Global.getInt(contentResolver, name) as T?
            String::class -> Settings.Global.getString(contentResolver, name) as T?
            Float::class -> Settings.Global.getFloat(contentResolver, name) as T?
            Long::class -> Settings.Global.getLong(contentResolver, name) as T?

            else -> {
                throw Exception("Setting type ${T::class} is not supported")
            }
        }
    } catch (e: Settings.SettingNotFoundException) {
        null
    }
}

/**
 * @return whether the setting was changed successfully
 */
@RequiresPermission(Manifest.permission.WRITE_SETTINGS)
inline fun <reified T> Context.putSystemSetting(name: String, value: T): Boolean {

    return when (T::class) {

        Int::class -> Settings.System.putInt(contentResolver, name, value as Int)
        String::class -> Settings.System.putString(contentResolver, name, value as String)
        Float::class -> Settings.System.putFloat(contentResolver, name, value as Float)
        Long::class -> Settings.System.putLong(contentResolver, name, value as Long)

        else -> {
            throw Exception("Setting type ${T::class} is not supported")
        }
    }
}

/**
 * @return whether the setting was changed successfully
 */
@RequiresPermission(Manifest.permission.WRITE_SECURE_SETTINGS)
inline fun <reified T> Context.putSecureSetting(name: String, value: T): Boolean {

    return when (T::class) {
        Int::class -> Settings.Secure.putInt(contentResolver, name, value as Int)
        String::class -> Settings.Secure.putString(contentResolver, name, value as String)
        Float::class -> Settings.Secure.putFloat(contentResolver, name, value as Float)
        Long::class -> Settings.Secure.putLong(contentResolver, name, value as Long)

        else -> {
            throw Exception("Setting type ${T::class} is not supported")
        }
    }
}

/**
 * @return whether the setting was changed successfully
 */
@RequiresPermission(Manifest.permission.WRITE_SECURE_SETTINGS)
inline fun <reified T> Context.putGlobalSetting(name: String, value: T): Boolean {

    return when (T::class) {
        Int::class -> Settings.Global.putInt(contentResolver, name, value as Int)
        String::class -> Settings.Global.putString(contentResolver, name, value as String)
        Float::class -> Settings.Global.putFloat(contentResolver, name, value as Float)
        Long::class -> Settings.Global.putLong(contentResolver, name, value as Long)

        else -> {
            throw Exception("Setting type ${T::class} is not supported")
        }
    }
}

fun Context.sendPackageBroadcast(action: String, extras: Bundle = Bundle.EMPTY) =
    Intent(action).apply {
        setPackage(Constants.PACKAGE_NAME)
        putExtras(extras)

        sendBroadcast(this)
    }