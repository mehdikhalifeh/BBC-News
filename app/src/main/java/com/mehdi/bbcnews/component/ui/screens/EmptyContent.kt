package com.mehdi.bbcnews.component.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mehdi.bbcnews.R
import com.mehdi.bbcnews.component.ui.theme.EMPTY_CONTENT_ICON_SIZE

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Icon(
            modifier = Modifier.size(EMPTY_CONTENT_ICON_SIZE),
            painter = painterResource(id = R.drawable.ic_sad),
            contentDescription = stringResource(id = R.string.sad_face_icon),
            tint = MaterialTheme.colorScheme.inverseOnSurface
        )
        Text(
            text = stringResource(id = R.string.empty_content),
            color = MaterialTheme.colorScheme.inverseOnSurface,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview
@Composable
fun EmptyContentPreview() {
    EmptyContent()
}
