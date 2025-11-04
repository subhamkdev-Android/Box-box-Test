package com.subhamkumar.boxboxapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhamkumar.boxboxapp.data.model.Driver
import com.subhamkumar.boxboxapp.data.model.Race
import com.subhamkumar.boxboxapp.data.repository.F1Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: F1Repository
) : ViewModel() {

    private val _drivers = MutableStateFlow<List<Driver>>(emptyList())
    val drivers: StateFlow<List<Driver>> = _drivers

    private val _races = MutableStateFlow<List<Race>>(emptyList())
    val races: StateFlow<List<Race>> = _races

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val driverResponse = repository.getTopDriver()
                val raceResponse = repository.getRaceDetails()

                _drivers.value = driverResponse.drivers
                _races.value = raceResponse.schedule

            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
