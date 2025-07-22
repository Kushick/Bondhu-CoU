import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bondhucou.data.BondhuModel
import com.example.bondhucou.data.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BondhuViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStoreManager = DataStoreManager(application)

    private var _isLoggedIn = MutableStateFlow<Boolean?>(null)
    var isLoggedIn: StateFlow<Boolean?> = _isLoggedIn

    private val _bondhu = MutableStateFlow<BondhuModel?>(null)
    val bondhu: StateFlow<BondhuModel?> = _bondhu

    val isChecked = mutableStateOf(false)


    val name = mutableStateOf("")
    val department = mutableStateOf("")
    val session = mutableStateOf("")
    val bloodGroup = mutableStateOf("")
    val contactNumber = mutableStateOf("")


    val isOpened = mutableStateOf(false)
    var isDonated = mutableStateOf(false)
    var donateDate = mutableStateOf("")

    var selectedBloodGroup = mutableStateOf("")

    var isLoading = mutableStateOf(true)



    init {

        viewModelScope.launch {
            val storedIsDonated = dataStoreManager.isDonated.first()
            val storedDate=dataStoreManager.donateDate.first()
            if(storedDate.isNotEmpty()){
                isDonated.value=true
                donateDate.value=storedDate
                isLoading.value=false
            }else {
                isDonated.value = dataStoreManager.isDonated.first()
                donateDate.value = ""
            }
            _isLoggedIn.value = dataStoreManager.isLoggedIn.first()
            isLoading.value = false
        }

        viewModelScope.launch {
            isDonated.value = dataStoreManager.isDonated.first()
            donateDate.value = dataStoreManager.donateDate.first()
            _isLoggedIn.value = dataStoreManager.isLoggedIn.first()
            isLoading.value=false
        }

//        viewModelScope.launch {
//            dataStoreManager.saveDonationStatus(false, "")
//            isDonated.value = false
//            donateDate.value = ""
//        }


        viewModelScope.launch {
            dataStoreManager.name.collect { name.value = it }
        }
        viewModelScope.launch {
            dataStoreManager.department.collect { department.value = it }
        }
        viewModelScope.launch {
            dataStoreManager.session.collect { session.value = it }
        }
        viewModelScope.launch {
            dataStoreManager.bloodGroup.collect { bloodGroup.value = it }
        }
        viewModelScope.launch {
            dataStoreManager.contactNumber.collect { contactNumber.value = it }
        }
        viewModelScope.launch {
            dataStoreManager.donateDate.collect { donateDate.value = it }
        }

        viewModelScope.launch {
            dataStoreManager.isLoggedIn.collect {
                _isLoggedIn.value = it
                isLoading.value=false
            }
        }

        viewModelScope.launch {
            dataStoreManager.isDonated.collect { isDonated ->
                this@BondhuViewModel.isDonated.value = isDonated
                isLoading.value=false
            }

        }
        viewModelScope.launch {
            dataStoreManager.donateDate.collect { donateDate ->
                this@BondhuViewModel.donateDate.value = donateDate
            }
        }

        isLoading.value=false
    }


    fun setLoggedIn() {
        _isLoggedIn.value = true
        viewModelScope.launch {
            dataStoreManager.saveLoginState(true)
        }
    }

    fun logout() {
        _isLoggedIn.value = false
        viewModelScope.launch {
            dataStoreManager.clearLoginState()
            resetDonateDate()
            clearUserData()
        }

    }

    fun clearUserData() {
        name.value = ""
        session.value = ""
        department.value = ""
        bloodGroup.value = ""
        contactNumber.value = ""
        donateDate.value=""
        updateBondhuModel()
    }


    fun updateBondhuModel(){
        _bondhu.value= BondhuModel(
            name = name.value,
            department = department.value,
            session = session.value,
            bloodGroup = bloodGroup.value,
            contactNumber = contactNumber.value
        )
    }



    fun updateDonateDate() {
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        donateDate.value = formatter.format(Date())
        isDonated.value = true

        viewModelScope.launch {
            dataStoreManager.saveDonationStatus(isDonated.value, donateDate.value)
        }
    }

    fun resetDonateDate() {
        donateDate.value = ""
        isDonated.value = false
        viewModelScope.launch {
            dataStoreManager.saveDonationStatus(false,"")
        }
    }

    fun saveUserInfo() {
        viewModelScope.launch {
            dataStoreManager.saveUserInfo(
                name.value,
                department.value,
                session.value,
                bloodGroup.value,
                contactNumber.value
            )
        }
    }



}