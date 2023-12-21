package com.miso.presentation.ui.inquiry.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.bottomsheet.BaseBottomSheetComponent
import com.miso.design_system.icon.BottomSheetCameraIcon
import com.miso.design_system.icon.GalleryIcon
import com.miso.design_system.theme.MisoTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectPhotoPathBottomSheetComponent(
    bottomSheetState: ModalBottomSheetState,
    onGalleryLaunchButtonClick: () -> Unit,
    onCameraLaunchButtonClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    MisoTheme { colors, typography ->
        Column(
            modifier = Modifier.navigationBarsPadding()
        ) {
            Spacer(modifier = Modifier.size(24.dp))
            BaseBottomSheetComponent(
                coroutineScope = coroutineScope,
                bottomSheetState = bottomSheetState,
                leftIcon = {
                    GalleryIcon(
                        modifier = Modifier.padding(12.dp)
                    )
                },
                text = "앨범에서 가져오기",
                textStyle = typography.buttonSmall,
                textColor = Color(0xFF313235),
                fontWeight = FontWeight.Normal,
                onClick = onGalleryLaunchButtonClick
            )
            Spacer(modifier = Modifier.size(8.dp))
            BaseBottomSheetComponent(
                coroutineScope = coroutineScope,
                bottomSheetState = bottomSheetState,
                leftIcon = {
                    BottomSheetCameraIcon(
                        modifier = Modifier.padding(12.dp)
                    )
                },
                text = "카메라에서 촬영",
                textStyle = typography.buttonSmall,
                textColor = Color(0xFF313235),
                fontWeight = FontWeight.Normal,
                onClick = onCameraLaunchButtonClick
            )
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}