package com.subhamkumar.boxboxapp.di

import com.subhamkumar.boxboxapp.data.network.RetrofitInstance
import com.subhamkumar.boxboxapp.data.repository.F1Repository
import com.subhamkumar.boxboxapp.data.repository.F1RepositoryImpl
import com.subhamkumar.boxboxapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitInstance.provideApi() }
    single<F1Repository> { F1RepositoryImpl(get()) }
    viewModel { HomeViewModel(get()) }
}
