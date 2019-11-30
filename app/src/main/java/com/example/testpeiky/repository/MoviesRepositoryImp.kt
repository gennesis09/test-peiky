package com.example.testpeiky.repository

import android.util.Log
import android.widget.Toast
import com.example.testpeiky.models.MoviesResponse
import com.example.testpeiky.rest.API_KEY
import com.example.testpeiky.rest.ApiConn
import com.example.testpeiky.rest.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Response

class MoviesRepositoryImp: MoviesRepository {

    private val compositeDisposable = CompositeDisposable()
    private val apiService: ApiService = ApiConn.getInstance().create(ApiService::class.java)
    private val moviesSbj: PublishSubject<MoviesResponse> = PublishSubject.create()
    private val errorSbj: PublishSubject<String> = PublishSubject.create()

    override fun observableMovies(): Observable<MoviesResponse> {
        return moviesSbj.observeOn(AndroidSchedulers.mainThread())
    }

    override fun observableMoviesError(): Observable<String> {
        return errorSbj.observeOn(AndroidSchedulers.mainThread())
    }

    override fun getPopularMovies(
        language: String,
        page: Int
    ) {
        compositeDisposable.add(apiService.getPopularMovies(API_KEY, language, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onSuccess, this::onError))
    }

    private fun onSuccess(response: Response<MoviesResponse>) {
        if (response.code() == 200) {
            response.body()?.let {
                moviesSbj.onNext(it)
            }
        } else {
            errorSbj.onNext("Error al obtener la lista")
        }
        response.body()
    }

    private fun onError(throwable: Throwable?) {
        throwable?.let {
            it.printStackTrace()
            it.message?.let { mess ->
                errorSbj.onNext(mess)
            }
        }
    }
}