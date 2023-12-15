package com.miso.design_system.component.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miso.design_system.icon.CameraIcon
import com.miso.design_system.icon.InquiryIcon
import com.miso.design_system.icon.NavBackgroundIcon
import com.miso.design_system.icon.SearchIcon
import com.miso.design_system.icon.SettingIcon
import com.miso.design_system.icon.ShopIcon
import com.miso.design_system.theme.MisoTheme

@Composable
fun MisoBottomNavigationBar(
    modifier: Modifier,
    currentRoute: String,
    onSearchClick: () -> Unit,
    onShopClick: () -> Unit,
    onCameraClick: () -> Unit,
    onInquiryClick: () -> Unit,
    onSettingClick: () -> Unit,
) {
    MisoTheme { colors, typography ->
        Box(
            modifier = modifier.background(Color.Transparent),
            contentAlignment = Alignment.BottomCenter
        ) {
            NavBackgroundIcon()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(colors.WHITE),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onSearchClick() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SearchIcon(isClick = currentRoute == "Search")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "검색",
                        style = typography.captionLarge,
                        color = if (currentRoute == "Search") colors.PRIMARY else colors.GREYSCALE3
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onShopClick() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ShopIcon(isClick = currentRoute == "Shop")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "상점",
                        style = typography.captionLarge,
                        color = if (currentRoute == "Shop") colors.PRIMARY else colors.GREYSCALE3
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {}
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onInquiryClick() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InquiryIcon(isClick = currentRoute == "Inquiry")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "문의",
                        style = typography.captionLarge,
                        color = if (currentRoute == "Inquiry") colors.PRIMARY else colors.GREYSCALE3
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onSettingClick() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SettingIcon(isClick = currentRoute == "Setting")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "설정",
                        style = typography.captionLarge,
                        color = if (currentRoute == "Setting") colors.PRIMARY else colors.GREYSCALE3
                    )
                }
            }
            Column(
                modifier = Modifier
                    .width(64.dp)
                    .height(80.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onCameraClick() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                CameraIcon()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "카메라",
                    style = typography.captionLarge,
                    color = colors.GREYSCALE3
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BottomNavigationPreView() {
    MisoBottomNavigationBar(
        modifier = Modifier,
        currentRoute = "",
        onSearchClick = {},
        onShopClick = {},
        onCameraClick = {},
        onInquiryClick = {},
        onSettingClick = {}
    )
}