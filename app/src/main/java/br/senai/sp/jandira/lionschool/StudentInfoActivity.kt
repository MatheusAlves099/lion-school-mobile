package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.StudentNotes
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentInfo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val matriculaAluno = intent.getStringExtra("matricula")
                    val nomeAluno = intent.getStringExtra("nome")
                    StudentInfoScreen(matriculaAluno.toString(), nomeAluno.toString())
                }
            }
        }
    }
}

@Composable
fun StudentInfoScreen(matricula: String, nome: String) {

    var studentNotes by remember {
        mutableStateOf(StudentNotes("","","","", emptyList()))
    }

    val call = RetrofitFactory().getStudentNotes().getStudentByMatriculaNotes(matricula)

    call.enqueue(object : Callback<StudentNotes> {
        override fun onResponse(
            call: Call<StudentNotes>,
            response: Response<StudentNotes>
        ) {
            if(response.isSuccessful){
                val studentResponse = response.body()
                if(studentResponse != null){
                    studentNotes = studentResponse
                }
            }

        }
        override fun onFailure(call: Call<StudentNotes>, t: Throwable) {

        }
    })

    fun getColorNotes (nota: Double): Color {
        return if (nota > 69) {
            Color(50, 70, 170)
        } else if (nota> 49 && nota < 70) {
            Color(220, 180, 80)
        } else {
            Color(190, 10, 10)
        }
    }
    
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
                    painter = painterResource(id = R.drawable.person_icon),
                    contentDescription = "",
                    tint = Color(51, 71, 176, 255)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = nome.uppercase(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = Color(51, 71, 176, 255)
                )
            }

            Divider(
                modifier = Modifier
                    .size(height = 2.dp, width = 300.dp),
                color = Color(254, 193, 62, 255)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Column() {
                var backgroundCardColor = Color(0,0,0)
                if (studentNotes.status == "Finalizado") {
                    backgroundCardColor = Color(254, 193, 62, 255)
                } else {
                    backgroundCardColor = Color(51, 71, 176, 255)
                }
                Card(
                    modifier = Modifier
                        .size(width = 300.dp, height = 150.dp),
                    backgroundColor = backgroundCardColor,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(
                        2.dp,
                        Brush.verticalGradient(
                            listOf(
                                Color(51, 71, 176, 255),
                                Color(254, 193, 62, 255)
                            )
                        ))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        AsyncImage(
                            model = studentNotes.foto,
                            contentDescription = "Student photo",
                            modifier = Modifier
                                .size(80.dp)
                        )

                        Text(text = studentNotes.nome.uppercase(),
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

                            Text(text = studentNotes.status,
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

            Spacer(modifier = Modifier.height(15.dp))

            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 400.dp),
                backgroundColor = Color(229, 228, 228, 255),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(
                    width = 2.dp,
                    Brush.verticalGradient(
                        listOf(
                            Color(51, 71, 176, 255),
                            Color(254, 193, 62, 255)
                        )
                    )
                )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(studentNotes.disciplinas) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = it.sigla,
                                color = getColorNotes(it.media.toDouble()),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(25.dp))
                            Surface(

                            ) {
                                Card(
                                    modifier = Modifier
                                        .height(10.dp)
                                        .width(100.dp),
                                    backgroundColor = Color.DarkGray
                                ) {
                                }
                                Card(
                                    modifier = Modifier
                                        .height(10.dp)
                                        .width(it.media.toDouble().dp),
                                backgroundColor = getColorNotes(it.media.toDouble())
                                ) {
                                }
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = it.media,
                                color = getColorNotes(it.media.toDouble()),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }