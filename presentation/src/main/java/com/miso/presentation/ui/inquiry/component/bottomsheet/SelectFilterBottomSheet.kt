package com.miso.presentation.ui.inquiry.component.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miso.design_system.component.button.ButtonState
import com.miso.design_system.component.button.MisoButton
import com.miso.design_system.component.modifier.misoClickable
import com.miso.design_system.icon.HideButtonIcon
import com.miso.design_system.theme.MisoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectFilterBottomSheet(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    onWaitClick: () -> Unit,
    onCompleteClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .navigationBarsPadding()
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "문의 상태",
                    style = typography.textMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.BLACK
                )
                Box(
                    modifier = Modifier.misoClickable {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }
                    }
                ) {
                    HideButtonIcon()
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MisoButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    state = ButtonState.OutLine,
                    text = "검토중"
                ) {
                    onWaitClick()
                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                MisoButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    text = "답변 완료"
                ) {
                    onCompleteClick()
                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}