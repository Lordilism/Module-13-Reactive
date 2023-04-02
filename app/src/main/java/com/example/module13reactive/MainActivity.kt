package com.example.module13reactive

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.toObservable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Just
        Observable.just(getNumberFromOneToTen())
            .subscribe {
                Log.d("just",it.toString())
            }

        //FromIterable
        Observable.fromIterable(getNumberFromOneToTen())
            .subscribe{
                Log.d("FromIterable",it.toString())
            }

        //FromCallable
        Observable.fromCallable{getNumberFromOneToTen()}
            .subscribe {
                Log.d("FromCallable",it.toString())
            }

        //RxKotlin
        getNumberFromOneToTen().toObservable()
            .subscribe {
                Log.d("toObservable",it.toString())
            }

        //Filter
        getNumberFromOneToTen().toObservable()      //observable->operator-> subscribe
            .filter{it/2==0}
            .subscribe{
                Log.d("Filter",it.toString())
            }

        //Take
        getNumberFromOneToTen().toObservable()
            .take(5)
            .subscribe{
                Log.d("Take",it.toString())
            }

        //Skip
        getNumberFromOneToTen().toObservable()
            .skip(3)
            .subscribe {
                Log.d("Skip",it.toString())
            }

        //Distinct until changed
        getDuplicateNumbersWithOneTOTen().toObservable()
            .distinctUntilChanged()
            .subscribe {
                Log.d("Distinct",it.toString())
            }

        //Transforming Operators
        //map and flatMap
        getNumberFromOneToTen().toObservable()
            .map { it*2 }
            .subscribe{
                Log.d("map",it.toString())
            }

        //Any
        getNumberFromOneToTen().toObservable()
            .any { it ==10 }
            .toObservable()
            .subscribe {
                Log.d("any",it.toString())
            }

        //Reduce
        getNumberFromOneToTen().toObservable()
            .reduce { first, second -> first+second }
            .subscribe {
                Log.d("reduce",it.toString())
            }

        //Combining operator
        Observable.combineLatest(
            listOf(getNumberFromOneToTen().toObservable(),
                getHundred().toObservable())
        ){
            it.map {
                it.toString()
            }.joinToString(",")
        }.subscribe {
            Log.d("Combining Operator",it)
        }

        //zip
        Observable.zip(
            listOf(
                getNumberFromOneToTen().toObservable(),
                getHundred().toObservable()
            )
        ){
            it.map { it.toString() }.joinToString(",")
        }.subscribe {
            Log.d("zip",it)
        }






    }

    private fun getNumberFromOneToTen(): List<Int> {
        return (1..10).toList()
    }

    private fun getDuplicateNumbersWithOneTOTen():List<Int>{
        return arrayListOf(1,2,2,3,4,5,5,6,6,7,8,9,9,2,6,)
    }

    private fun getHundred():List<Int>{
        return arrayListOf(100,200,300,400,500,600,700,800,900,1000)
    }


}