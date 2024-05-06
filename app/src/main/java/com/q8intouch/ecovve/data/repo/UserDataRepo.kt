package com.q8intouch.ecovve.data.repo



import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.data.AppDatabase
import com.q8intouch.ecovve.data.model.SavedUserData
import com.q8intouch.ecovve.network.EcovveAllStatus
import com.q8intouch.ecovve.network.EcovveGiftCardCheckout
import com.q8intouch.ecovve.network.EcovveNetworkInterceptor
import com.q8intouch.ecovve.network.apis.AuthAPI
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.ui.HomeActivity
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.extension.errorResponse
import com.q8intouch.ecovve.util.extension.map
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.MultipartBody
import okhttp3.RequestBody


@Singleton
class UserDataRepo @Inject constructor (private val appDatabase: AppDatabase,
                                        private val authAPI: AuthAPI,
                                        private val ecovveNetworkInterceptor: EcovveNetworkInterceptor) {

    public var ecovveUser: EcovveUser? = null
    private  var _savedUserData:SavedUserData? = null
    var savedUserData: LiveData<SavedUserData>
        get() = MutableLiveData<SavedUserData>().apply { value = _savedUserData }
        set(value) {
           if (_savedUserData != null) appDatabase.userDao().save(value.value!!)
            _savedUserData = value.value
        }

   init {
       savedUserData =  appDatabase.userDao().get
   }
   fun isLoggedIn(): Boolean {
       if (_savedUserData?.accessToken != null){
           ecovveNetworkInterceptor.accessToken = _savedUserData!!.accessToken
           return true
       }
       else
       return false
   }

    fun getUserInfo():LiveData<EcovveUser?>  {
        return MutableLiveData<EcovveUser?>().apply { value = ecovveUser }
    }

    fun saveUserData (loginResponse: LoginResponse,rememberPassword:Boolean){
        _savedUserData = SavedUserData(0,
            loginResponse.data.id!!,
            loginResponse.data.email!!,
            "",
            null,
            if (rememberPassword) loginResponse.accessToken else null ,
            loginResponse.expiresAt)
    }

    fun login(email:String?, phone:String?, password:String,mobile_id:String, rememberPassword:Boolean): LiveData<Resource<LoginResponse>> {
       return  authAPI.login(email,phone,password,mobile_id).map {
           Log.v("logni",it.toString())
            if (it.isSuccess) {
               // ecovveUser = it.resource!!.userData
                ecovveNetworkInterceptor.accessToken = it.resource!!.accessToken
                saveUserData(it.resource!!,rememberPassword)
            }
           return@map it
        }
    }
    fun login(provider : String,id:String,token:String,context:Context,mobile_id:String, findNavController: NavController): LiveData<Resource<LoginResponse>> {
       if (!provider.contains("facebook") && !provider.contains("google"))
        return  authAPI.loginSocial("twitter",id,token,provider,mobile_id).map {
           Log.e("logni",it.toString())
            if (it.isSuccess) {
               // ecovveUser = it.resource!!.userData
                ecovveNetworkInterceptor.accessToken = it.resource!!.accessToken
                saveUserData(it.resource!!,false)
                Log.e("logni",it.resource!!.toString())
                val sharedPreference: Shared = Shared(context!!)
                sharedPreference.save("id", it.resource!!.data.id!!)
                sharedPreference.save("token", "" + it.resource!!.accessToken)
                var bundle = bundleOf("amount" to "" + it.resource!!.data.id!!)
                var intext = Intent(context,HomeActivity::class.java)
                intext.putExtra("amount",""+it.resource!!.data.id!!)
                context.startActivity(intext)

            }
           else {
                Log.e("logni",it.error!!.localizedMessage!!.toString())
            }
           return@map it
        }
        else {
           return  authAPI.loginSocial(provider,id,token,null,mobile_id).map {
               Log.e("logni",it.toString())
               if (it.isSuccess) {
                   // ecovveUser = it.resource!!.userData
                   ecovveNetworkInterceptor.accessToken = it.resource!!.accessToken
                   saveUserData(it.resource!!,false)
                   Log.e("logni",it.resource!!.toString())
                   val sharedPreference: Shared = Shared(context!!)
                   sharedPreference.save("id", it.resource!!.data.id!!)
                   sharedPreference.save("token", "" + it.resource!!.accessToken)
                   var bundle = bundleOf("amount" to "" + it.resource!!.data.id!!)
//                findNavController.navigate(R.id.action_loginFragment_to_homeActivity, bundle)
                   var intext = Intent(context,HomeActivity::class.java)
                   intext.putExtra("amount",""+it.resource!!.data.id!!)
//                intext.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                   context.startActivity(intext)

               }
               else {
                   Log.e("logni",it.error!!.localizedMessage!!.toString())
               }
               return@map it
           }
       }
    }

    fun searchLatLon(cat:String ,lat:String?, lon:String?, boundries:String,context: Context,page: Int): LiveData<Resource<EcovveLatLonSearch>> {

        return  authAPI.SearchLatLon(page ,cat,lat,lon,boundries,getMap(context, false, "")).map {
            it
        }
    }
    fun searchLatLon(lat:String?, lon:String?, boundries:String,context: Context,page: Int): LiveData<Resource<EcovveLatLonSearch>> {

        return  authAPI.SearchLatLon(page ,lat,lon,"500",getMap(context, false, "")).map {
           it
        }
    }

    fun changePass(old:String?, newpass:String?, conPass:String): LiveData<Resource<EcovveDelete>> {

        return  authAPI.changePassword(old ,newpass,conPass).map {
            it
        }
    }

    fun searchLatLon(caty:String,context:Context,page: Int): LiveData<Resource<EcovveLatLonSearch>> {
        return  authAPI.SearchLatLonCategory(page ,getMap(context, true, caty)).map {
            it
        }
    }

    fun searchLatLon(city:String,context:Context,page: Int,nooo:String): LiveData<Resource<EcovveLatLonSearch>> {
        return  authAPI.SearchLatLonCity(page ,city,getMap(context, false, "")).map {
            it
        }
    }

    fun searchLatLon(caty:String,city:String,context:Context,page: Int): LiveData<Resource<EcovveLatLonSearch>> {
        return  authAPI.SearchLatLonAll(page ,city,getMap(context,true,caty)).map {
            it
        }
    }

    private fun getMap(context: Context, b: Boolean, caty: String) : HashMap<String,String>{
        val sharedPreference: Shared = Shared(context)
        var newcafes =    sharedPreference.getValueString("newcafes")
        var mastercase =    sharedPreference.getValueString("mastercase")
        var knetcase =   sharedPreference.getValueString("knetcase")
        var opencase =   sharedPreference.getValueString("opencase")
        var freedeliverycase =    sharedPreference.getValueString("freedeliverycase")
        var offerscase =    sharedPreference.getValueString("offerscase")
        var sort =    sharedPreference.getValueString("sort")
        var map2 = HashMap<String, String>()
        if (b){
            map2.put("has__brand;category;id__",caty)
        }
        if (sort != null)
            map2.put("sort_by",sort)

        if (knetcase != null)
            map2.put("has__brand;supports_knet__","1")

        if (mastercase!=null)
            map2.put("has__brand;supports_cc__","1")

        if (opencase!=null)
            map2.put("is_open","1")

        if (freedeliverycase!=null)
            map2.put("avg_delivery_fee__","0")
        return map2
    }

    fun searchLatLon(context:Context,url : String,page: Int): LiveData<Resource<EcovveLatLonSearch>> {
        return  authAPI.SearchLatLon(page ,getMap(context, false, "")).map {
            it
        }
    }

    fun allCategories(): LiveData<Resource<EcovveAllCategoryResponse>> {
        return  authAPI.allCategories().map {
            it
        }
    }

    fun randomAD(): LiveData<Resource<EcovveRandomAD>> {
        return  authAPI.randomAD().map {
            it
        }
    }

    fun deleteAddress(id:String): LiveData<Resource<EcovveDelete>> {
        return  authAPI.deleteAddress(id).map {
            it
        }
    }

     fun activeMobile(id:String): LiveData<Resource<EcovveActiveCode>> {
        return  authAPI.activeMobile(id).map {
            it
        }
     }

    fun cancelOrder(id:String): LiveData<Resource<EcovveCancelOrder>> {
        return  authAPI.cancelOrder(id).map {
            it
        }
    }

    fun resendMobile(phone:String): LiveData<Resource<EcovveDelete>> {
        return  authAPI.resendCode(phone).map {
            it
        }
    }

    fun resendPassCode(phone:String): LiveData<Resource<EcovveForgetPassPhone>> {
        return  authAPI.resendPassCode(phone).map {
            it
        }
    }

    fun resendPassCodeEmail(phone:String): LiveData<Resource<EcovveDelete>> {
        return  authAPI.resendPassCodeEmail(phone).map {
            it
        }
    }

    fun resetPassByPhone(token:String,phone:String,password: String,confirmpass: String): LiveData<Resource<EcovveDelete>> {
        return  authAPI.resetPassByPhone(token,phone,password,confirmpass).map {
            it
        }
    }

    fun resetPassByEmail(token:String,phone:String,password: String,confirmpass: String): LiveData<Resource<EcovveDelete>> {
        return  authAPI.resetPassByEmail(token,phone,password,confirmpass).map {
            it
        }
    }

    fun prevOrders( id : String ): LiveData<Resource<EcovveUserPrevOrders>> {
        return  authAPI.prevOrders(id).map {
            if (it.isSuccess) {
                // ecovveUser = it.resource!!.userData
//                saveUserData(it.resource!!,rememberPassword)
            }
            return@map it
        }
    }

    fun allPromotions(page : Int): LiveData<Resource<EcovveAllPromotion>> {
        return  authAPI.allPromotions(page).map {
            if (it.isSuccess) {
                // ecovveUser = it.resource!!.userData
//                saveUserData(it.resource!!,rememberPassword)
            }
            return@map it
        }
    }

    fun getPromotionById(promotionId:String) = authAPI.getPromotionById(promotionId).map { it }

    fun latestCafes(page: Int): LiveData<Resource<EcovveNewBrand>> {
        return  authAPI.latestCafes(page).map {
           it
        }
    }

    fun userInfo(id: String): LiveData<Resource<EcovveUser>> {
        return  authAPI.getUserData(id).map {
            it
        }
    }

    fun topCafes(page: Int): LiveData<Resource<EcovveTopCafes>> {
        return  authAPI.topCafes(page).map {
           it
        }
    }
    fun allOutlets(page: Int): LiveData<Resource<EcovveALLOUTLETS>> {
        return  authAPI.allOutlet(page).map {
           it
        }
    }

    fun cafeInfo(id: String): LiveData<Resource<EcovveCafeInfo>> {
        return  authAPI.cafeInfo(id).map {
           it
        }
    }

    fun showOrderReview(id: String): LiveData<Resource<EcovveOrderReviews>>
        =  authAPI.showOrderReview(id).map {
            it
        }


    fun userFavorites(): LiveData<Resource<EcovveUserFavorites>> {
        return  authAPI.userFavorites().map {
            it
        }
    }
    fun logout(): LiveData<Resource<LogoutResponse>> {
        return  authAPI.logout().map {
           it
        }
    }
    fun userOrders(id: String,delivery__:String): LiveData<Resource<EcovveUserOrders>> {
        return  authAPI.userOrders(id,delivery__).map {
            if (it.isSuccess) {
                // ecovveUser = it.resource!!.userData
//                saveUserData(it.resource!!,rememberPassword)
            }
            return@map it
        }
    }

    fun userHabits(id: String): LiveData<Resource<EcovveAllHabits>> {
        return  authAPI.userHabits(id).map {
            if (it.isSuccess) {

            }
            return@map it
        }
    }

    fun getMostSold(): LiveData<Resource<EcovveGetMostSold>> {
        return  authAPI.getMostSoldItems().map {
            if (it.isSuccess) {

            }
            return@map it
        }
    }

    fun showItem(id: String): LiveData<Resource<EcovveShowItem>> {
        return  authAPI.showItem(id).map {
            if (it.isSuccess) {

            }
            return@map it
        }
    }

    fun showExtra(id: String): LiveData<Resource<EcovveShowExtra>> {
        return  authAPI.showExtra(id).map {
            if (it.isSuccess) {

            }
            return@map it
        }
    }

    fun onGoingOrders(id: String): LiveData<Resource<EcovveUserOrders>> {
        return  authAPI.ongoingOrders(id).map {
            if (it.isSuccess) {
                // ecovveUser = it.resource!!.userData
//                saveUserData(it.resource!!,rememberPassword)
            }
            return@map it
        }
    }

    fun cafeMenu(id: String): LiveData<Resource<EcovveCafeInfoBrand>> {
        return  authAPI.cafeMenu(id).map {
          it
        }
    }
// fun cafeMenu(id: String): LiveData<Resource<EcovveMenuResponse>> {
//        return  authAPI.cafeMenu(id).map {
//          it
//        }
//    }

    fun allarea(): LiveData<Resource<EcovveAllAreaOutlet>> {
        return  authAPI.allArea().map {
            it
        }
    }

    fun contactUs(name:String,phone:String,address:String): LiveData<Resource<EcovveContactUs>> {
        return  authAPI.contactUs(name,phone,address).map {
            it
        }
    }

    fun allAreaWithCities(): LiveData<Resource<EcovveCitiesWithAreas>> {
        return  authAPI.allAreaWithCities().map {
            it
        }
    }
    fun offerInfo(id: String): LiveData<Resource<EcovveOfferInfo>> {
        return  authAPI.offerInfo(id).map {
            it
        }
    }
    fun showMenu(id: String,page: Int): LiveData<Resource<MenuResponse>> {
        return  authAPI.showMenu(id,page).map {
           it
        }
    }

    fun getAllBanners(): LiveData<Resource<EcovveAllBanners>> {
        return  authAPI.getAllBanners().map {
            it
        }
    }
    fun searchBrand(search: String): LiveData<Resource<EcovveBrandSearch>> {
        return  authAPI.searchBrand(search).map {
           it
        }
    }

    fun cityBrand(search: String): LiveData<Resource<EcovveCityBrand>> {
        return  authAPI.cityBrand(search).map {
           it
        }
    }

    fun signup(name: String,
                  email: String,
                  password: String,
                  confirmpass:String , 
                  phone: String): LiveData<Resource<EcovveSignup>> {
        return  authAPI.Signup(name,email,password,confirmpass,phone).map {
           it
        }
    }

    fun giftCardCheckout(name: String,
                         description: String,
                         amount: String,
                         expire_date:String ,
                         status:String ,
                         giver_id:String ,
                         taker_id:String ,
                         notification_type:String): LiveData<Resource<EcovveGiftCardCheckout>> {
        return  authAPI.giftCardCheckout(
                name = name,
                description = description
                ,amount = amount
                ,expire_date = expire_date
                ,status = status,
                giver_id = giver_id,
                taker_id = taker_id,
                notification_type = notification_type
            ).map {
            it
        }
    }

    fun giftCardCheckoutByEmail(name: String,
                         description: String,
                         amount: String,
                         expire_date:String ,
                         status:String ,
                         notification_type:String
    ,taker_email : String): LiveData<Resource<EcovveGiftCardCheckout>> {
        return  authAPI.giftCardCheckoutByEmail(
                name = name,
                description = description
                ,amount = amount
                ,expire_date = expire_date
                ,status = status,
                notification_type = notification_type
        ,taker_email = taker_email
        ).map {
            it
        }
    }
    fun showMenuitems(id: String): LiveData<Resource<EcovveMenuItems>> {
        return  authAPI.menuitems(id).map {
           it
        }
    }
    fun showOfferitems(id: String,page: Int): LiveData<Resource<EcovveOfferItems>> {
        return  authAPI.offerItems(id,page).map {
           it
        }
    }

    fun allStatus(): LiveData<Resource<EcovveAllStatus>> {
        return  authAPI.allStatus().map {
            it
        }
    }
    fun showStatus(id:String): LiveData<Resource<EcovveOrderStatus>> {
        return  authAPI.showStatus(id).map {
            it
        }
    }
    fun allNotifications(): LiveData<Resource<EcovveNotification>> {
        return  authAPI.allnotifications().map {
            it
        }
    }

    fun allFaqs(page: Int): LiveData<Resource<EcovveFaqAll>> {
        return  authAPI.allFaqs(page).map {
            it
        }
    }


    fun getAllRewards(): LiveData<Resource<EcovveAllRewards>> {
        return  authAPI.getAllRewards().map {
            it
        }
    }

    fun exchangeReward(userId : String , rewardId:String): LiveData<Resource<EcovveUser>> {
        return  authAPI.exchangeReward(userId,rewardId).map {
            it
        }
    }

    fun changeImage(user:String, photo: MultipartBody.Part): LiveData<Resource<EcovveUserPhoto>> {
        return  authAPI.changeProfileImage(user,photo).map {
            if (it.isSuccess){

                Log.e("kmkahha", it.resource!!.data!![0]!!.message!!)
            }
            else {
                Log.e("powokwmke",it.errorResponse().message)
            }
            it
        }
    }


    @NonNull
    private fun createPartFromString(descriptionString: String): RequestBody {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString)
    }


    fun seenNotification(seen: String , notificationSeen: String): LiveData<Resource<EcovveNotificationSeen>> {
        return  authAPI.seenNotification(seen,notificationSeen).map {
            it
        }
    }
}