package com.audgns.highhighthon.ui.qr

import android.Manifest
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.audgns.highhighthon.R
import com.audgns.highhighthon.util.QrScanModel
import com.audgns.highhighthon.util.QrcodeScanView
import com.audgns.highhighthon.util.parseQrScanModel
import com.audgns.highhighthon.util.saveQrScanModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun QrLoginScreen(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    navController: NavController,
    context: Context = LocalContext.current // Context를 Composable에서 사용할 수 있도록

) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    var qrScanModel: QrScanModel? = null

    Box(contentAlignment = Alignment.Center) {

        QrcodeScanView(
            onQrcodeScan = { scannedQrData ->
                qrScanModel = scannedQrData.parseQrScanModel()
                qrScanModel?.let {
                    saveQrScanModel(context, it)
                    navController.navigate("main")
                }
            },
            lifecycleOwner = lifecycleOwner
        )

        QrGuideImage()

        Column(
            modifier = modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            ExpoTopBar(
                startIcon = {
                    Icon(painter = painterResource(id = R.drawable.ic_left_arrow), contentDescription = "", tint = Color.White, modifier = Modifier
                        .size(20.dp)
                        .clickable { navController.popBackStack() })
                }
            )
        }
    }
}

@Composable
fun QrGuideImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.qr_guide),
        contentDescription = "",
        modifier = modifier
    )
}

@Composable
fun ExpoTopBar(
    modifier: Modifier = Modifier,
    startIcon: @Composable () -> Unit,
    endIcon: @Composable () -> Unit = { Spacer(modifier = Modifier.size(24.dp)) }
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        startIcon()
        Text(
            text = "되돌아가기",
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            fontSize = 20.sp,
        )
        endIcon()
    }
}

val pretendardFamily = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold)
)