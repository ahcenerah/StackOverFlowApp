package fr.mastersid.stackoverflow.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import fr.mastersid.stackoverflow.R

@Composable
fun SendButton (
                modifier: Modifier,
                 @RequiresPermission( value = "android.permission.SEND_SMS")
                onPermissionGranted : () -> Unit ,
                onPermissionNotGranted : () -> Unit ,
                onPermissionNeedsExplanation : ( requestAgain : () -> Unit ) -> Unit
){
    val requestPermissionLauncher =
        rememberLauncherForActivityResult (
            ActivityResultContracts.RequestPermission()
        ) { isGranted : Boolean ->
            if ( isGranted ) {
                onPermissionGranted ()
            } else {
                onPermissionNotGranted ()
            }
        }
    val context = LocalContext.current
    IconButton(onClick ={
        when {
        ContextCompat.checkSelfPermission (
            context,Manifest.permission.SEND_SMS
        ) == PackageManager.PERMISSION_GRANTED
        -> {
            onPermissionGranted ()
        }
        ActivityCompat.shouldShowRequestPermissionRationale (
            context.getActivity(),Manifest.permission.SEND_SMS
        ) -> {
            onPermissionNeedsExplanation {
                requestPermissionLauncher.launch(Manifest.permission.SEND_SMS )
            }
        }
        else -> {
            requestPermissionLauncher.launch(Manifest.permission.SEND_SMS  )
        }
    } }, modifier = modifier) {
        Icon(painter = painterResource(id = R.drawable.baseline_send_24) ,
            contentDescription = "send Sms",
            modifier =Modifier,)
    }
}


 fun Context.getActivity () : Activity {
    var currentContext = this
    while ( currentContext is ContextWrapper) {
        if ( currentContext is Activity ) {
            return currentContext
        }
        currentContext =currentContext.baseContext
    }
    throw IllegalStateException (" Permissions should be called in the context of an Activity ")
}