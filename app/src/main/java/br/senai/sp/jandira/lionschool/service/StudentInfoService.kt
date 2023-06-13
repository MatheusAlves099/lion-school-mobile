package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentInfo
import br.senai.sp.jandira.lionschool.model.StudentInfoList
import br.senai.sp.jandira.lionschool.model.StudentList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentInfoService {
    @GET("alunos/{matricula}")
    fun getStudentByMatricula(@Path("matricula") matricula: String): Call<StudentInfoList>
}