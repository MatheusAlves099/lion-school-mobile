package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.theme.LionSchoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                HomeScreen()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_header),
                contentDescription = "logo Lion School",
                modifier = Modifier.size(240.dp),
            )
            Divider(
                modifier = Modifier
                    .size(height = 1.dp, width = 300.dp),
                color = Color(254, 193, 62, 255)
            )
            Text(text = stringResource(id = R.string.text_home),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color(51, 71, 176, 255)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = stringResource(id = R.string.text_home_description),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(200.dp),
                color = Color(51, 71, 176, 255)
            )

            Spacer(modifier = Modifier.height(80.dp))

            Button(
                onClick = {
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .size(width = 128.dp, height = 46.dp),
                colors = ButtonDefaults.buttonColors(Color(51, 71, 176, 255)),
                border = BorderStroke(width = 1.dp, color = Color.White)
            ) {
                Text(
                    text = stringResource(id = R.string.text_button_lets_go),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
            }
        }
    }
}