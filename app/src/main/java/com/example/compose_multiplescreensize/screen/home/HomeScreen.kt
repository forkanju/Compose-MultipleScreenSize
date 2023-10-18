package com.example.compose_multiplescreensize.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.compose_multiplescreensize.WindowSize
import com.example.compose_multiplescreensize.WindowType

@Composable
fun HomeScreen(
    windowSize: WindowSize,
    homeViewModel: HomeViewModel = viewModel()
) {
    val items = homeViewModel.items

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = items, key = { it.id }) {
            AdaptableItem(data = it, windowSize = windowSize)
        }
    }
}


@Composable
fun AdaptableItem(data: CustomData, windowSize: WindowSize) {
    val maxLines by remember(key1 = windowSize) {
        mutableIntStateOf(if (windowSize.width == WindowType.Compact) 4 else 10)
    }

    when (windowSize.height) {
        WindowType.Expanded -> {
            Column {
                ColumnContent(
                    data = data,
                    windowSize = windowSize,
                    maxLines = maxLines
                )
            }
        }

        else -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RowContent(
                    data = data,
                    windowSize = windowSize,
                    maxLines = maxLines
                )
            }
        }
    }
}

@Composable
fun ColumnContent(
    data: CustomData,
    windowSize: WindowSize,
    maxLines: Int
) {
    val showIcons by remember(key1 = windowSize) {
        mutableStateOf(windowSize.height == WindowType.Expanded)
    }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )

    Column {
        Text(
            text = data.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize =
                when (windowSize.height) {
                    WindowType.Expanded -> MaterialTheme.typography.bodyLarge.fontSize
                    else -> MaterialTheme.typography.bodySmall.fontSize
                },
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = data.description,
            modifier = Modifier.alpha(ContentAlpha.disabled),
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize =
                when (windowSize.height) {
                    WindowType.Expanded -> MaterialTheme.typography.bodyMedium.fontSize
                    else -> MaterialTheme.typography.bodySmall.fontSize
                }
            )
        )
        if (showIcons) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                data.icons.forEach {
                    Icon(
                        imageVector = it,
                        contentDescription = "Icon",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.RowContent(
    data: CustomData,
    windowSize: WindowSize,
    maxLines: Int
) {
    val showIcons by remember(key1 = windowSize) {
        mutableStateOf(windowSize.width == WindowType.Expanded)
    }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data = data.image)
            .crossfade(enable = true)
            .build(),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .weight(1f)
    )

    Column(modifier = Modifier.weight(1f)) {
        Text(
            text = data.title, maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize =
                when (windowSize.width) {
                    WindowType.Expanded -> MaterialTheme.typography.headlineMedium.fontSize
                    WindowType.Medium -> MaterialTheme.typography.titleLarge.fontSize
                    else -> MaterialTheme.typography.titleMedium.fontSize
                },
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = data.description,
            modifier = Modifier.alpha(ContentAlpha.disabled),
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize =
                when (windowSize.width) {
                    WindowType.Expanded -> MaterialTheme.typography.titleLarge.fontSize
                    WindowType.Medium -> MaterialTheme.typography.titleMedium.fontSize
                    else -> MaterialTheme.typography.bodySmall.fontSize
                }
            )
        )

        if (showIcons) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                data.icons.forEach {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = it,
                        contentDescription = "Icon"
                    )
                }
            }
        }
    }
}