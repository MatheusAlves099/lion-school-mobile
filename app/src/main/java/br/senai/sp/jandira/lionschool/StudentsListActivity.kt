package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
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
                val siglaCurso = intent.getStringExtra("sigla")
                val nomeCurso = intent.getStringExtra("nome")
                StudentsListScreen(siglaCurso.toString(), nomeCurso.toString())
            }
        }
    }
}


@Composable
fun StudentsListScreen(curso: String, nomeCurso: String) {

    val context = LocalContext.current

    var listStudent by remember {
        mutableStateOf(listOf<Student>())
    }

    var nameCourse by remember {
        mutableStateOf("")
    }

    val call = RetrofitFactory().getStudentService().getStudentByCourse(curso)

    //Executa a chamada
    call.enqueue(object : Callback<StudentList> {
        override fun onResponse(
            call: Call<StudentList>,
            response: Response<StudentList>
        ) {
            listStudent = response.body()!!.curso
        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {

        }
    })

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(colors = listOf(Color(0,0,0), Color(0,200,0)))
            )
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

                Text(text = nomeCurso,
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
                items(listStudent) {
                    var backgroundCardColor = Color(0,0,0)
                    if (it.status == "Finalizado") {
                        backgroundCardColor = Color(254, 193, 62, 255)
                    } else {
                        backgroundCardColor = Color(51, 71, 176, 255)
                    }


                    Card(
                        modifier = Modifier
                            .size(width = 300.dp, height = 150.dp)
                            .padding(8.dp)
                            .clickable {
                                var openPerformanceStudents = Intent(context, StudentsListActivity::class.java)

                                context.startActivity(openPerformanceStudents)
                            },
                        backgroundColor = backgroundCardColor,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(2.dp, color = Color(104, 104, 104, 255))
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
                                text = it.nome.uppercase(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                                color = Color(250, 250, 250, 255)
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
                                    text = it.status,
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