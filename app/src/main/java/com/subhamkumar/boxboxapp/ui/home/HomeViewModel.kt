package com.subhamkumar.boxboxapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhamkumar.boxboxapp.data.model.Driver
import com.subhamkumar.boxboxapp.data.model.Race
import com.subhamkumar.boxboxapp.data.repository.F1Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"

class HomeViewModel(
    private val repository: F1Repository
) : ViewModel() {

    private val _drivers = MutableStateFlow<List<Driver>>(emptyList())
    val drivers: StateFlow<List<Driver>> = _drivers

    private val _topDriver = MutableStateFlow<Driver?>(null)
    val topDriver: StateFlow<Driver?> = _topDriver

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
                Log.d(TAG, "Fetching driver & race data...")
                val driverResponse = repository.getTopDriver()
                val raceResponse = repository.getRaceDetails()

                Log.d(TAG, "Driver response size = ${driverResponse.drivers.size}")
                driverResponse.drivers.forEach { d ->
                    Log.d(TAG, "Driver: id=${d.driverId}, name=${d.fullName}, position=${d.position}, points=${d.points}")
                }

                _drivers.value = driverResponse.drivers
                _races.value = raceResponse.schedule

                // If your repository returned multiple drivers, pick the one with position == 1
                val found = driverResponse.drivers.find { it.position == 1 }
                if (found != null) {
                    _topDriver.value = found
                    Log.d(TAG, "Top driver found: ${found.firstName}")
                } else {
                    // fallback: if repo already returns 1 driver only, take first
                    val fallback = driverResponse.drivers.firstOrNull()
                    _topDriver.value = fallback
                    Log.w(TAG, "No driver with position==1 found; falling back to first: ${fallback?.fullName}")
                    if (fallback == null) {
                        _error.value = "No driver data returned from API"
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching data", e)
                _error.value = "Failed to load data: ${e.message}"
                _drivers.value = emptyList()
                _races.value = emptyList()
                _topDriver.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}
