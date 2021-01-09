package com.kailin.traffic.util.permission

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment

class PermissionFragment(
    private val grantedCallback: (() -> Unit)? = null,
    private val deniedCallback: (() -> Unit)? = null,
    private val permissions: Array<String>
) : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requestPermissions(permissions, 0)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            grantedCallback?.invoke()
        } else {
            deniedCallback?.invoke()
        }
    }
}