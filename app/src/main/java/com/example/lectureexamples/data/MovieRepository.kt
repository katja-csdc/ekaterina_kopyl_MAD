package com.example.lectureexamples.data

import kotlinx.coroutines.flow.Flow


//create a repository p.35 S."Persistence with Room"
//Що робить репозиторій? -> йому на вхід приходить MovieDao( was wir created haben), який буде вміти працювати з [Movie::class]
//а репозиторій містить в собі MovieDao і він перенаправляє виклики
class MovieRepository( // клас репозиторій, де на вхід приходить Moviedao
    private val  movieDao: MovieDao  //передаємо сюди DAO, щоб воно працювало далі з базою даних
    ) {


    suspend fun insert(movie: Movie) = movieDao.insert(movie) //всі функції з нашого репозиторію перенаправляються на movieDao.insert

    suspend fun delete(movie: Movie) { //кажемо для repository delete, і він нас перенаправляє на movieDao.delete
        movieDao.delete(movie)
    }
    suspend fun update(movie: Movie) = movieDao.update(movie) //suspend -> може піти спати, а потім прокинувшись, далі виконувати свою дію
                                                              //можемо викликати її тільки в окремому потоці, в корутині. Треба явно вказувати,
                                                              //що у нас викликається корутіна

    fun getAll(): Flow<List<Movie>> {   // об'явлення фунції - ()-параметр {}-тіло
        return movieDao.getAll()
    }

    fun getById(id: Int): Flow<Movie> {
        return movieDao.getById(id)
    }

    fun getAllFavorite(): Flow<List<Movie>> {
        return movieDao.getAllFavorite()
    }
}