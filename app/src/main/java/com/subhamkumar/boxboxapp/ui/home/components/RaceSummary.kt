package com.subhamkumar.boxboxapp.ui.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.subhamkumar.boxboxapp.R
import com.subhamkumar.boxboxapp.data.model.Race
import com.subhamkumar.boxboxapp.data.model.Session


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RaceSummaryRow(
    race: Race?, session: Session?, onClickRaceInfo: () -> Unit
) {
    if (race == null || session == null) return

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        RaceSessionCard(
            modifier = Modifier
                .weight(1f)
                .height(132.dp),
            session = session,
            onClick = onClickRaceInfo
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .height(132.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                label = "7015.3",
                unit = "km",
                imageRes = R.drawable.ic_distance_icon,
                progress = 0.38f,
                animateProgress = true
            )

            LinkCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                iconRes = R.drawable.ic_medium,
                titleTop = "Formula 1",
                titleBottom = "Education",
                url = "https://blog.boxbox.club/tagged/beginners-guide",
                topRightIcon = R.drawable.ic_arrow_right
            )
        }
    }
}