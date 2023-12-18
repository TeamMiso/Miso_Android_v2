package com.miso.design_system.icon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.miso.design_system.R

@Composable
fun MisoColorIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_miso_color),
        contentDescription = "Miso Color Icon",
        modifier = modifier
    )
}

@Composable
fun DeleteButtonIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_delete_btn),
        contentDescription = "Delete Button Icon",
        modifier = modifier
    )
}

@Composable
fun HideButtonIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_hide_button),
        contentDescription = "Hide Button Icon",
        modifier = modifier
    )
}

@Composable
fun VisibilityIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_visibility),
        contentDescription = "Visibility Icon",
        modifier = modifier
    )
}

@Composable
fun VisibilityOffIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_visibility_off),
        contentDescription = "Visibility Off Icon",
        modifier = modifier
    )
}

@Composable
fun SearchIcon(
    modifier: Modifier = Modifier,
    isClick: Boolean = false
) {
    if (!isClick) {
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search Icon",
            modifier = modifier
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.ic_search_click),
            contentDescription = "Search Click Icon",
            modifier = modifier
        )
    }
}

@Composable
fun ShopIcon(
    modifier: Modifier = Modifier,
    isClick: Boolean = false
) {
    if (!isClick) {
        Image(
            painter = painterResource(id = R.drawable.ic_shop),
            contentDescription = "Shop Icon",
            modifier = modifier
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.ic_shop_click),
            contentDescription = "Shop Click Icon",
            modifier = modifier
        )
    }
}

@Composable
fun CameraIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_camera),
        contentDescription = "Camera Icon",
        modifier = modifier
    )
}

@Composable
fun InquiryIcon(
    modifier: Modifier = Modifier,
    isClick: Boolean = false
) {
    if (!isClick) {
        Image(
            painter = painterResource(id = R.drawable.ic_inquiry),
            contentDescription = "Inquiry Icon",
            modifier = modifier
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.ic_inquiry_click),
            contentDescription = "Inquiry Click Icon",
            modifier = modifier
        )
    }
}

@Composable
fun SettingIcon(
    modifier: Modifier = Modifier,
    isClick: Boolean = false
) {
    if (!isClick) {
        Image(
            painter = painterResource(id = R.drawable.ic_setting),
            contentDescription = "Setting Icon",
            modifier = modifier
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.ic_setting_click),
            contentDescription = "Setting Click Icon",
            modifier = modifier
        )
    }
}

@Composable
fun NavBackgroundIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_nav_background),
        contentDescription = "Nav Background Icon",
        modifier = modifier
    )
}

@Composable
fun GalleryIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_gallery_bottom_sheet),
        contentDescription = "Gallery Icon Button",
        modifier = modifier
    )
}

@Composable
fun BottomSheetCameraIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_camera_bottm_sheet),
        contentDescription = "Camera Icon",
        modifier = modifier
    )
}