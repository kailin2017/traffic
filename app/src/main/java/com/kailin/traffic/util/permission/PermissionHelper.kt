package com.kailin.traffic.util.permission

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class PermissionHelper {

    fun checkPermission(
        activity: FragmentActivity,
        permissions: Array<String>,
        grantedCallback: (() -> Unit)? = null,
        deniedCallback: (() -> Unit)? = null
    ) {
        if (permissions.all {
                ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED
            }) {
            grantedCallback?.let { it() }
        } else {
            activity.supportFragmentManager
                .beginTransaction()
                .add(
                    PermissionFragment(grantedCallback, deniedCallback, permissions),
                    "PermissionFragment"
                )
                .commit()
        }
    }

    companion object {
        val instance: PermissionHelper by lazy { PermissionHelper() }
    }
}