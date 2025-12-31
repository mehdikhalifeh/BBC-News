package com.mehdi.bbcnews.component.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mehdi.bbcnews.R
import com.mehdi.bbcnews.component.model.NewsArticleUi
import com.mehdi.bbcnews.component.ui.theme.HEADLINE_IMAGE_HEIGHT
import com.mehdi.bbcnews.component.ui.theme.HEIGHT_LINE
import com.mehdi.bbcnews.component.ui.theme.LARGE_PADDING
import com.mehdi.bbcnews.component.ui.theme.MEDIUM_PADDING
import com.mehdi.bbcnews.component.ui.theme.SPACER_HEIGHT


@Composable
fun DetailContent(
    modifier: Modifier, article: NewsArticleUi
) {
    Column(
        modifier = modifier
            .padding(all = LARGE_PADDING)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
        ) {
            HeadlineImage(
                article = article, modifier = Modifier
            )

            Text(
                text = article.title.orEmpty(),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = FontFamily.SansSerif,
                lineHeight = HEIGHT_LINE,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(SPACER_HEIGHT))
            Text(
                text = article.description.orEmpty(),
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = FontFamily.SansSerif,
                lineHeight = HEIGHT_LINE,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(SPACER_HEIGHT))
            Text(
                text = article.content.orEmpty(),
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = FontFamily.SansSerif,
                lineHeight = HEIGHT_LINE,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Composable
private fun HeadlineImage(
    modifier: Modifier, article: NewsArticleUi
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(article.urlToImage.orEmpty())
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(HEADLINE_IMAGE_HEIGHT)
            .clip(RoundedCornerShape(corner = CornerSize(MEDIUM_PADDING)))
    )
}
