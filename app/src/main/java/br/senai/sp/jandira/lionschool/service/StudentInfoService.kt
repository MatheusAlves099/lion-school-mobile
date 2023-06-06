package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentInfoService {
    @GET("alunos")
    fun getStudentByCourse(@Query("matricula") matricula: String): Call<StudentList>
}