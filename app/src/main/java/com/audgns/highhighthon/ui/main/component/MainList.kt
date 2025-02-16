package com.audgns.highhighthon.ui.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.audgns.highhighthon.model.Channel
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
internal fun MainList(
    modifier: Modifier = Modifier,
    item: List<Channel>,
    isClick: Boolean,
    onDeleteClick: (String) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(bottom = 9.dp)
    ) {
        items(item) { item ->
            MainListItem(
                item = item,
                isClick = isClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}