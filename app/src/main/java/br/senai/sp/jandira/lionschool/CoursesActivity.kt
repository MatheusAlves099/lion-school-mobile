package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.CourseList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoursesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                CoursesScreen()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CoursesScreen() {

    val context = LocalContext.current

    var listCourse by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Course>())
    }

    var searchState by remember {
        mutableStateOf(value = "")
    }

    var clickState by remember {
        mutableStateOf(value = "")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_header),
                contentDescription = "logo Lion School",
                modifier = Modifier.size(120.dp),
            )
            Divider(
                modifier = Modifier
                    .size(height = 2.dp, width = 300.dp),
                color = Color(3, 52, 123, 255)
            )
            OutlinedTextField(
                value = searchState,
                onValueChange = {searchState = it
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp),
                label = { (Text(
                    text = stringResource(id = R.string.text_outlined_courses)
                ))},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    Color(0, 0, 0, 255)
                ),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Divider(
                modifier = Modifier
                    .size(height = 2.dp, width = 300.dp),
                color = Color(254, 193, 62, 255)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .padding(end = 185.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.book_icon),
                    contentDescription = "",
                    tint = Color(254, 193, 62, 255)
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(text = stringResource(id = R.string.text_all_courses),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color(51, 71, 176, 255)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
            ) {

                val call = RetrofitFactory().getCourseService().getCourses()

                //Executa a chamada
                call.enqueue(object : Callback<CourseList> {
                    override fun onResponse(
                        call: Call<CourseList>,
                        response: Response<CourseList>
                    ) {
                        listCourse = response.body()!!.cursos
                    }

                    override fun onFailure(call: Call<CourseList>, t: Throwable) {

                    }
                })
                items(listCourse) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                var showStudents =
                                    Intent(context, StudentsListActivity::class.java)
                                context.startActivity(showStudents)
                                showStudents.putExtra("sigla", it.sigla)
                            }
                            .size(width = 300.dp, height = 130.dp),
                        backgroundColor = Color(51, 71, 176, 255),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(2.dp, color = Color(254, 193, 62, 255))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center
                            ) {
                                AsyncImage(
                                    model = it.icone,
                                    contentDescription = "Course logo",
                                    modifier = Modifier
                                        .size(48.dp)
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = it.sigla,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 40.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(255, 255, 255, 255)
                                )
                            }

                            Text(
                                text = it.nome,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                                color = Color(255, 255, 255, 255)
                            )

                            Row(
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.clock_icon), contentDescription = "",
                                    modifier = Modifier
                                        .size(15.dp)
                                )

                                Spacer(modifier = Modifier.width(2.dp))

                                Text(
                                    text = it.carga,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(255, 255, 255, 255)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}