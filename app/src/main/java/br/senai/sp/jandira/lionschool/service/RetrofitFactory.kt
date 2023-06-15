package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentNotes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val BASE_URL = "https://lion-school.cyclic.app/v1/lion-school/"
    private val BASE_URL_SECOND = "https://api-lion-school-2023.cyclic.app/v1/lion-school/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitFactoryNotes = Retrofit
        .Builder()
        .baseUrl(BASE_URL_SECOND)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCourseService(): CourseService {
        return retrofitFactory.create(CourseService::class.java)
    }

    fun getStudentService(): StudentService {
        return retrofitFactory.create(StudentService::class.java)
    }

    fun getStudentMatricula(): StudentInfoService {
        return retrofitFactory.create(StudentInfoService::class.java)
    }

    fun getStudentNotes(): NotesService {
        return retrofitFactoryNotes.create((NotesService::class.java))
    }
}