package ru.myitschool.work.ui.screen.main


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.myitschool.work.R
import ru.myitschool.work.core.TestIds

@Composable
fun MainScreen() {
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.fillMaxWidth()) {
            // Image() ФОТО ДОЛЖНО ПРИХОДИТЬ С СЕРВАКА
            Column(Modifier.weight(1f)) {
                Text(stringResource(R.string.main_welcome))
                Text(
                    "Lorem ipsum",
                    modifier = Modifier.testTag(TestIds.Main.PROFILE_NAME)
                ) // ИМЯ ЗДЕСЬ С СЕРВЕРА
            }
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.refresh_btn),
                    contentDescription = "refresh",
                    modifier = Modifier.testTag(TestIds.Main.REFRESH_BUTTON)
                )
            }
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.logout_btn),
                    contentDescription = "refresh",
                    modifier = Modifier.testTag(TestIds.Main.REFRESH_BUTTON)
                )
            }
        }
    }
}