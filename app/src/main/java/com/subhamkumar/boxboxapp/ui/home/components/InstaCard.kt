package com.subhamkumar.boxboxapp.ui.home.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.subhamkumar.boxboxapp.R

@Composable
fun InstagramCard(imageRes: Int = R.drawable.image_insta) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                val intent = Intent(
                    Intent.ACTION_VIEW, "https://www.instagram.com/boxbox_club/".toUri()
                )
                context.startActivity(intent)
            }, shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "F1 25 Instagram card",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.FillWidth
            )

            Image(
                painter = painterResource(id = R.drawable.ic_insta),
                contentDescription = "Instagram logo",
                modifier = Modifier
                    .size(28.dp)
                    .padding(end = 10.dp, bottom = 10.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

