package com.mertyigit0.viewmodel

// SwitchViewModel.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SwitchViewModel : ViewModel() {
    // Ego switch başlangıçta açık (true)
    private val _isEgoChecked = MutableLiveData<Boolean>(true)
    val isEgoChecked: LiveData<Boolean> get() = _isEgoChecked

    private val _isGivingChecked = MutableLiveData<Boolean>(false)
    val isGivingChecked: LiveData<Boolean> get() = _isGivingChecked

    private val _isHappinessChecked = MutableLiveData<Boolean>(false)
    val isHappinessChecked: LiveData<Boolean> get() = _isHappinessChecked

    private val _isKindnessChecked = MutableLiveData<Boolean>(false)
    val isKindnessChecked: LiveData<Boolean> get() = _isKindnessChecked

    private val _isRespectChecked = MutableLiveData<Boolean>(false)
    val isRespectChecked: LiveData<Boolean> get() = _isRespectChecked

    private val _isOptimismChecked = MutableLiveData<Boolean>(false)
    val isOptimismChecked: LiveData<Boolean> get() = _isOptimismChecked

    // Switch'lerin durumunu güncelleyen fonksiyonlar
    fun setEgoChecked(isChecked: Boolean) {
        _isEgoChecked.value = isChecked
        if (isChecked) {
            // Diğer switch'leri kapat
            _isGivingChecked.value = false
            _isHappinessChecked.value = false
            _isKindnessChecked.value = false
            _isRespectChecked.value = false
            _isOptimismChecked.value = false
        }
    }

    fun setGivingChecked(isChecked: Boolean) {
        _isGivingChecked.value = isChecked
    }

    fun setHappinessChecked(isChecked: Boolean) {
        _isHappinessChecked.value = isChecked
    }

    fun setKindnessChecked(isChecked: Boolean) {
        _isKindnessChecked.value = isChecked
    }

    fun setRespectChecked(isChecked: Boolean) {
        _isRespectChecked.value = isChecked
    }

    fun setOptimismChecked(isChecked: Boolean) {
        _isOptimismChecked.value = isChecked
    }
}
