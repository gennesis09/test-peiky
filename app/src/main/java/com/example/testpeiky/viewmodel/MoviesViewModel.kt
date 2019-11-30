package com.example.testpeiky.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testpeiky.models.Movies
import com.example.testpeiky.models.MoviesResponse
import com.example.testpeiky.repository.MoviesRepository
import com.example.testpeiky.repository.MoviesRepositoryImp
import io.reactivex.disposables.CompositeDisposable

class MoviesViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val moviesRepository: MoviesRepository
    private var actualPage = 1

    private val _moviesLiveData = MutableLiveData<List<Movies>>()
    val moviesLiveData: LiveData<List<Movies>>
        get() = _moviesLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    init {
        moviesRepository = MoviesRepositoryImp()
        compositeDisposable.add(moviesRepository.observableMovies()
            .subscribe(this::onDatafetched))
        compositeDisposable.add(moviesRepository.observableMoviesError()
            .subscribe(this::onError))
    }

    fun fetchData() {
        moviesRepository.getPopularMovies("es", actualPage)
    }

    private fun onDatafetched(moviesResponse: MoviesResponse) {
        _moviesLiveData.value = moviesResponse.results
    }

    private fun onError(msg: String) {
        _errorLiveData.value = msg
    }
}