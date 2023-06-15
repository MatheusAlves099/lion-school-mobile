package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentInfoList
import br.senai.sp.jandira.lionschool.model.StudentNotes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NotesService {
    @GET("alunos/{matricula}")
    fun getStudentByMatriculaNotes(@Path("matricula") matricula: String): Call<StudentNotes>
}