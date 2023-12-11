package com.miso.design_system.icon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.miso.design_system.R

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