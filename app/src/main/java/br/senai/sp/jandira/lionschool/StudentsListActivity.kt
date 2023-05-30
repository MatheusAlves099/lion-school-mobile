package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import br.senai.sp.jandira.lionschool.model.Course
import br.senai.sp.jandira.lionschool.model.CourseList
import br.senai.sp.jandira.lionschool.model.Student
import br.senai.sp.jandira.lionschool.model.StudentList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                StudentsListScreen()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun StudentsListScreen() {

    var listStudent by remember {
        mutableStateOf(listOf<Student>())
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

            Row(

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ds_icon),
                    contentDescription = "",
                    tint = Color(51, 71, 176, 255)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = stringResource(id = R.string.text_ds_title),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color(51, 71, 176, 255)
                )
            }

            Divider(
                modifier = Modifier
                    .size(height = 2.dp, width = 300.dp),
                color = Color(254, 193, 62, 255)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .width(300.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .size(width = 90.dp, height = 40.dp),
                    colors = ButtonDefaults.buttonColors(Color(51, 71, 176, 255))
                ) {
                    Text(
                        text = stringResource(id = R.string.text_all_students),
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(254, 193, 62, 255)
                    )
                }

                Spacer(modifier = Modifier.width(2.dp))

                Button(
                    onClick = {
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .size(width = 100.dp, height = 40.dp),
                    colors = ButtonDefaults.buttonColors(Color(51, 71, 176, 255))
                ) {
                    Text(
                        text = stringResource(id = R.string.text_studying),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(254, 193, 62, 255)
                    )
                }

                Spacer(modifier = Modifier.width(2.dp))

                Button(
                    onClick = {
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .size(width = 100.dp, height = 40.dp),
                    colors = ButtonDefaults.buttonColors(Color(254, 193, 62, 255))
                ) {
                    Text(
                        text = stringResource(id = R.string.text_finished),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(51, 71, 176, 255)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            LazyColumn(
            ) {

                val call = RetrofitFactory().getStudentService().getStudent()

                //Executa a chamada
                call.enqueue(object : Callback<StudentList> {
                    override fun onResponse(
                        call: Call<StudentList>,
                        response: Response<StudentList>
                    ) {
                        listStudent = response.body()!!.alunos
                    }

                    override fun onFailure(call: Call<StudentList>, t: Throwable) {

                    }
                })

                items(listStudent) {
                    Card(
                        modifier = Modifier
                            .size(width = 300.dp, height = 150.dp)
                            .padding(8.dp),
                        backgroundColor = Color(254, 193, 62, 255),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(2.dp, color = Color(51, 71, 176, 255))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            AsyncImage(
                                model = it.foto,
                                contentDescription = "Course logo",
                                modifier = Modifier
                                    .size(80.dp)
                            )

                            Text(
                                text = it.nome,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                                color = Color(51, 71, 176, 255)
                            )

                            Row(
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.clock_icon_blue), contentDescription = "",
                                    modifier = Modifier
                                        .size(15.dp)
                                )

                                Spacer(modifier = Modifier.width(2.dp))

                                Text(
                                    text = it.status,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(51, 71, 176, 255)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}