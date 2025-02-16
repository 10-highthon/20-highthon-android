package com.audgns.highhighthon.ui.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.audgns.highhighthon.R
import com.audgns.highhighthon.ui.main.component.MainList
import com.audgns.highhighthon.ui.main.viewModel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.audgns.highhighthon.model.DeleteFavoriteModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: MainViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getFavorite()
    }

    val uiState by viewModel.mainUiState.collectAsStateWithLifecycle()

    val refreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()

    var isClick by rememberSaveable { mutableStateOf(true) }

    val pretendardFamily = FontFamily(
        Font(R.font.pretendard_regular, FontWeight.Normal),
        Font(R.font.pretendard_semibold, FontWeight.SemiBold)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 73.dp
            )
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "",
            modifier = Modifier
                .width(150.dp)
                .height(34.dp)
        )

        Spacer(modifier = Modifier.padding(bottom = 51.dp))

        Text(
            text = if (isClick) "편집" else "돌아가기",
            color = Color(0xFF00FFA3),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { isClick = !isClick }
        )

        SwipeRefresh(
            state = SwipeRefreshState(isRefreshing = refreshing),
            onRefresh = {
                viewModel.getFavorite()
            },
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    contentColor = Color(0xFF00FFA3)
                )
            }
        ) {
            MainList(
                item = uiState.mainList.channels,
                isClick = isClick,
                onDeleteClick = { channelId ->
                    viewModel.deleteFavorite(
                        body = DeleteFavoriteModel(
                            channelId = listOf(channelId)
                        )
                    )
                }
            )
        }
    }
}