package com.example.instagramprofilecard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramprofilecard.R
import com.example.instagramprofilecard.domain.InstagramModel

@Composable
fun InstagramProfileCard(
    model: InstagramModel,
    onFollowedButtonClickListener: (InstagramModel) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(
            topStart = 4.dp,
            topEnd = 4.dp
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Image(
                    painter = painterResource(id = R.drawable.instagram_icon),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .size(60.dp)
                        .padding(8.dp),
                )

                UserStatistics(title = "Posts", value = "6.950")
                UserStatistics(title = "Followers", value = "436M")
                UserStatistics(title = "Following", value = "76")

            }
            Text(
                text = "Instagram ${model.id}",
                fontFamily = FontFamily.Cursive,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "#${model.title}",
                fontSize = 14.sp,
            )
            Text(
                text = "www.facebook.com/emotional_health",
                fontSize = 14.sp,
            )
            FollowButton(isFollowed = model.isFollowed) {
                onFollowedButtonClickListener(model)
            }
        }
    }
}

@Composable
private fun FollowButton(
    isFollowed: Boolean,
    clickListener: () -> Unit
) {
    Button(
        onClick = {
            clickListener()
        },
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFollowed) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colorScheme.primaryContainer
            }
        )
    ) {
        val text = if (isFollowed) {
            "Unfollow"
        } else {
            "Follow"
        }
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun UserStatistics(
    title: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
    }
}