package com.q8intouch.ecovve.ui.login.register

import android.os.CountDownTimer
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.github.leonardoxh.livedatacalladapter.Resource
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.data.repo.UserDataRepo
import com.q8intouch.ecovve.network.model.EcovveActiveCode
import com.q8intouch.ecovve.network.model.EcovveDelete
import com.q8intouch.ecovve.network.model.EcovveForgetPassPhone
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.extension.map
import kotlinx.android.synthetic.main.fragment_verfication_code.*
import timber.log.Timber
import javax.inject.Inject

class CodeVerficationViewModel @Inject constructor(private val userDataRepo: UserDataRepo) : ViewModel() {

   private var currentResetStep = MutableLiveData<String>()

    private var phoneOrEmail = ""
    private var resetCode = ""
    var textForCurrentStep = MutableLiveData<String>()
    var timerMaxSeconds = MutableLiveData<Int>()
    var currentSeconds= MutableLiveData<Int>()
    var currentSecondsAsString = MediatorLiveData<String>()

    lateinit var countDownTimer: CountDownTimer

    init {
        currentResetStep.value = STEP_RESET
        textForCurrentStep.value = phoneOrEmail
        timerMaxSeconds.value = 60 * 1000
        currentSeconds.value = 60 * 1000
        currentSecondsAsString.addSource(currentSeconds) {seconds: Int? -> currentSecondsAsString.value =(seconds!! /1000).toString() }
    }



    fun setStepBackToReset() {
        currentResetStep.value = STEP_RESET
        textForCurrentStep.value = phoneOrEmail
        resetCode = ""
    }
    fun setStepToCode()  {
        currentResetStep.value = STEP_CODE
        phoneOrEmail =  textForCurrentStep.value!!
        textForCurrentStep.value = resetCode
        Timber.w(phoneOrEmail)
    }


    fun submitResetCode():LiveData<Unit>{

        currentResetStep.value = STEP_RESET
        resetCode = textForCurrentStep.value!!
        Timber.wtf("%s %s", resetCode, phoneOrEmail)
        return MutableLiveData<Unit>().apply { value = Unit }
    }

    fun getCurrentResetStep():LiveData<String> = currentResetStep

    fun startTimer(){
        countDownTimer = object : CountDownTimer(timerMaxSeconds.value!!.toLong(),1){
            override fun onTick(millisUntilFinished: Long) {
                currentSeconds.value = millisUntilFinished.toInt()
            }

            override fun onFinish() {
                currentResetStep.value = STEP_RESET
            }

        }.start()
    }
    fun stopTimer(){
        countDownTimer.cancel()
    }

    companion object {
        const val STEP_RESET = "reset"
        const val STEP_CODE = "code"
    }

    fun activeMobile(id:String): LiveData<Resource<EcovveActiveCode>> {
        return  userDataRepo.activeMobile(id).map {
            it
        }
    }

    fun resendMobile(phone:String): LiveData<Resource<EcovveDelete>> {
        return  userDataRepo.resendMobile(phone).map {
            it
        }
    }

    fun resendPassCode(phone:String): LiveData<Resource<EcovveForgetPassPhone>> {
        return  userDataRepo.resendPassCode(phone).map {
            it
        }
    }
    fun resendPassCodeEmail(phone:String): LiveData<Resource<EcovveDelete>> {
        return  userDataRepo.resendPassCodeEmail(phone).map {
            it
        }
    }

}
