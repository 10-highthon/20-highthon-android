package com.audgns.highhighthon.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.audgns.highhighthon.R
import com.audgns.highhighthon.model.Channel

val pretendardFamily = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold)
)

@Composable
internal fun MainListItem(
    modifier: Modifier = Modifier,
    item: Channel,
    isClick: Boolean,
    onDeleteClick: (String) -> Unit,
) {

    if (isClick) {

        Spacer(modifier = Modifier.height(9.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF1A1A1A),
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(
                    horizontal = 14.73.dp,
                    vertical = 11.dp
                )
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .border(
                        1.dp,
                        color = if (item.channelType == "STREAMING") Color(0xFF00FFA3) else Color(0xFF686868),
                        CircleShape
                    )
                    .padding(3.8.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = item.channelImageUrl),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.padding(end = 14.73.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = item.channelName,
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF)
                    )

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = "팔로워 ${item.followerCount}명",
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = Color(0xFFB2B2C2)
                    )
                }

                Text(
                    text = if (item.channelType == "STREAMING") "방송 진행중!" else "방송이 끝났어요..",
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color(0xFF00FFA3)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.ic_expand_all),
                contentDescription = "",
                tint = Color(0xFF00FFA3),
                modifier = Modifier.size(20.dp)
            )
        }
    } else {

        Spacer(modifier = Modifier.height(9.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF1A1A1A),
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(
                    horizontal = 14.73.dp,
                    vertical = 11.dp
                )
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .border(
                        1.dp,
                        color = if (item.channelType == "STREAMING") Color(0xFF00FFA3) else Color(0xFF686868),
                        CircleShape
                    )
                    .padding(3.8.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = item.channelImageUrl),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.padding(end = 14.73.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = item.channelName,
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF)
                    )

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = "팔로워 ${item.followerCount}명",
                        fontFamily = pretendardFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = Color(0xFFB2B2C2)
                    )
                }

                Text(
                    text = if (item.channelType == "STREAMING") "방송 진행중!" else "방송이 끝났어요..",
                    fontFamily = pretendardFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color(0xFF00FFA3)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "",
                tint = Color(0xFFFF0000),
                modifier = Modifier.size(20.dp).clickable {
                    onDeleteClick(item.channelId)
                }
            )
        }
    }
}