package com.q8intouch.ecovve.network.apis

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import com.q8intouch.ecovve.network.EcovveAllStatus
import com.q8intouch.ecovve.network.EcovveGiftCardCheckout
import com.q8intouch.ecovve.network.EcovveSendOrderReview
import com.q8intouch.ecovve.network.model.*
import com.q8intouch.ecovve.network.model.cart.CartRow
import com.q8intouch.ecovve.util.URLs
import okhttp3.MultipartBody
import retrofit2.http.*


interface AuthAPI {
    @FormUrlEncoded
    @POST(URLs.AUTH_LOGIN)
    fun login(@Field("email") email: String?, @Field("phone") phone: String?
              , @Field("password") password: String, @Field("mobile_id") mobile_id: String): LiveData<Resource<LoginResponse>>


    @FormUrlEncoded
    @POST(URLs.LOGIN_SOCIAL)
    fun loginSocial(@Field("provider_name") email: String?
                    , @Field("provider_id") phone: String?
                    , @Field("token") password: String
                    , @Field("secret") secret: String?
                    , @Field("mobile_id") mobile_id: String?): LiveData<Resource<LoginResponse>>

    @FormUrlEncoded
    @POST(URLs.SEARCH_LATLAON)
    fun SearchLatLon(@Query("page") page: Int,
                     @Field("lat") email: String?
                     , @Field("lng") phone: String?
                     , @Field("area_boundaries") password: String
                     , @QueryMap options: HashMap<String, String>): LiveData<Resource<EcovveLatLonSearch>>


    @FormUrlEncoded
    @POST(URLs.CHANGE_PASS)
    fun changePassword(
            @Field("old_password") email: String?
            , @Field("password") phone: String?
            , @Field("password_confirmation") password: String
    ): LiveData<Resource<EcovveDelete>>

    @FormUrlEncoded
    @POST(URLs.SEARCH_LATLAON)
    fun SearchLatLon(@Query("page") page: Int,
                     @Field("category_id") category_id: String?,
                     @Field("lat") email: String?
                     , @Field("lng") phone: String?
                     , @Field("area_boundaries") password: String
                     , @QueryMap options: HashMap<String, String>): LiveData<Resource<EcovveLatLonSearch>>


    @POST(URLs.SEARCH_LATLAON)
    fun SearchLatLonCategory(@Query("page") page: Int,/* @Field("category_id") category_id: String?,*/ @QueryMap options: HashMap<String, String>): LiveData<Resource<EcovveLatLonSearch>>

    @FormUrlEncoded
    @POST(URLs.SEARCH_LATLAON)
    fun SearchLatLonCity(@Query("page") page: Int, @Field("area_id__") category_id: String?, @QueryMap options: HashMap<String, String>): LiveData<Resource<EcovveLatLonSearch>>

    @FormUrlEncoded
    @POST(URLs.SEARCH_LATLAON)
    fun SearchLatLonAll(@Query("page") page: Int,/* @Field("category_id") category_id: String?,*/ @Field("area_id__") city: String?, @QueryMap options: HashMap<String, String>): LiveData<Resource<EcovveLatLonSearch>>

    @POST(URLs.SEARCH_LATLAON)//
    fun SearchLatLon(@Query("page") page: Int, @QueryMap options: HashMap<String, String>): LiveData<Resource<EcovveLatLonSearch>>

    @GET(URLs.ALL_CATEGORY)
    fun allCategories(): LiveData<Resource<EcovveAllCategoryResponse>>


    @GET(URLs.RANDOMAD)
    fun randomAD(): LiveData<Resource<EcovveRandomAD>>

    @GET(URLs.VERIFY_MOBILE)
    fun activeMobile(@Path("id") id: String): LiveData<Resource<EcovveActiveCode>>


    @GET(URLs.CANCEL_ORDER)
    fun cancelOrder(@Path("id") id: String): LiveData<Resource<EcovveCancelOrder>>

    @POST(URLs.RESEND_MOBILE_VERVICATION)
    @FormUrlEncoded
    fun resendCode(@Field("phone") phone: String): LiveData<Resource<EcovveDelete>>

    @POST(URLs.RESEND_PASS_CODE)
    @FormUrlEncoded
    fun resendPassCode(@Field("phone") phone: String): LiveData<Resource<EcovveForgetPassPhone>>


    @POST(URLs.FORGET_PASS_CODE_EMAIL)
    @FormUrlEncoded
    fun resendPassCodeEmail(@Field("email") phone: String): LiveData<Resource<EcovveDelete>>

    @POST(URLs.RESET_PASS_BY_PHONE)
    @FormUrlEncoded
    fun resetPassByPhone(@Field("token") token: String
                         , @Field("phone") phone: String
                         , @Field("password") password: String
                         , @Field("password_confirmation") password_confirmation: String
    ): LiveData<Resource<EcovveDelete>>

    @POST(URLs.RESET_PASS_BY_EMAIL)
    @FormUrlEncoded
    fun resetPassByEmail(@Field("token") token: String
                         , @Field("email") phone: String
                         , @Field("password") password: String
                         , @Field("password_confirmation") password_confirmation: String
    ): LiveData<Resource<EcovveDelete>>


    @DELETE(URLs.DELETE_ADDRESS)
    fun deleteAddress(@Path("id") id: String): LiveData<Resource<EcovveDelete>>

    @GET(URLs.USER_DATA)
    fun getUserData(@Path("userId") userId: String): LiveData<Resource<EcovveUser>>

    @GET(URLs.PREV_ORDERS)
    fun prevOrders(@Path("id") id: String): LiveData<Resource<EcovveUserPrevOrders>>

    @GET(URLs.USER_ORDERS)
    fun userOrders(@Path("id") id: String, @Query("delivery__") delivery__: String): LiveData<Resource<EcovveUserOrders>>

    @GET(URLs.USER_HABITS)
    fun userHabits(@Path("id") id: String): LiveData<Resource<EcovveAllHabits>>

    @GET(URLs.SHOW_ITEM)
    fun showItem(@Path("id") id: String): LiveData<Resource<EcovveShowItem>>

    @GET(URLs.SHOW_Extra)
    fun showExtra(@Path("id") id: String): LiveData<Resource<EcovveShowExtra>>

    @GET(URLs.ONGING_ORDERS)
    fun ongoingOrders(@Path("id") id: String): LiveData<Resource<EcovveUserOrders>>

    @GET(URLs.ALL_PROMOTION)
    fun allPromotions(@Query("page") page: Int): LiveData<Resource<EcovveAllPromotion>>

    @GET(URLs.MOST_SOLD)
    fun getMostSoldItems(): LiveData<Resource<EcovveGetMostSold>>

    @GET(URLs.PROMOTION)
    fun getPromotionById(@Path("promotionId") promotionId: String): LiveData<Resource<PromotionResponse>>

    @GET(URLs.LATEST_CAFES)
    fun latestCafes(@Query("page") page: Int): LiveData<Resource<EcovveNewBrand>>

    @GET(URLs.TOP_CAFES)
    fun topCafes(@Query("page") page: Int): LiveData<Resource<EcovveTopCafes>>

    @GET(URLs.ALL_OUTLET)
    fun allOutlet(@Query("page") page: Int): LiveData<Resource<EcovveALLOUTLETS>>

    @GET(URLs.CAFE_INFO)
    fun cafeInfo(@Path("id") id: String): LiveData<Resource<EcovveCafeInfo>>


    @GET(URLs.USER_FAVORITES)
    fun userFavorites(): LiveData<Resource<EcovveUserFavorites>>

    @GET(URLs.SHOW_ORDER_REVIEW)
    fun showOrderReview(@Path("id") id: String): LiveData<Resource<EcovveOrderReviews>>


    @POST(URLs.SEND_ORDER_REVIEW)
    fun sendOrderReview(): LiveData<Resource<EcovveSendOrderReview>>


    @GET(URLs.LOGOUT)
    fun logout(): LiveData<Resource<LogoutResponse>>

    @GET(URLs.CAFE_Brand)
    fun cafeMenu(@Path("id") id: String): LiveData<Resource<EcovveCafeInfoBrand>>

    @GET(URLs.AREA)
    fun allArea(): LiveData<Resource<EcovveAllAreaOutlet>>

    @GET(URLs.AREA_WITH_CITIES)
    fun allAreaWithCities(): LiveData<Resource<EcovveCitiesWithAreas>>

    @GET(URLs.OFFER_INFO)
    fun offerInfo(@Path("id") id: String): LiveData<Resource<EcovveOfferInfo>>

    @POST(URLs.CART_STORE)
    fun SendCart(@Body cartRow: CartRow): LiveData<Resource<CartRow>>

    @POST(URLs.UPDATE_CART_STORE)
    fun UpdateCart(@Path("id") id: String, @Body cartRow: CartRow): LiveData<Resource<CartRow>>

    @POST(URLs.CONTACT)
    @FormUrlEncoded
    fun contactUs(@Field("site_name") name: String,
                  @Field("phone[1]") phone: String,
                  @Field("address[1]") address: String
    ): LiveData<Resource<EcovveContactUs>>

    @DELETE(URLs.UPDATE_CART_STORE)
    fun deleteCart(@Path("id") id: String, @Body cartRow: CartRow): LiveData<Resource<CartRow>>

    @GET(URLs.ShowAllMenu)
    fun showAllMenu(): LiveData<Resource<MenuResponse>>

    @GET(URLs.ShowAllBanners)
    fun getAllBanners(): LiveData<Resource<EcovveAllBanners>>

    @GET(URLs.ShowMenu)
    fun showMenu(@Path("id") id: String, @Query("items_page") page: Int): LiveData<Resource<MenuResponse>>

    @GET(URLs.MENU_ITEMS)
    fun menuitems(@Path("id") id: String): LiveData<Resource<EcovveMenuItems>>

    @GET(URLs.OFFER_ITEMS)
    fun offerItems(@Path("id") id: String, @Query("page") page: Int): LiveData<Resource<EcovveOfferItems>>

    @GET(URLs.SHOW_STATUS)
    fun showStatus(@Path("id") id: String): LiveData<Resource<EcovveOrderStatus>>

    @GET(URLs.ALL_STATUS)
    fun allStatus(): LiveData<Resource<EcovveAllStatus>>

    @GET(URLs.ALL_NOTIFICATIONS)
    fun allnotifications(): LiveData<Resource<EcovveNotification>>

    @GET(URLs.FaqAll)
    fun allFaqs(@Query("page") page: Int): LiveData<Resource<EcovveFaqAll>>

    @GET(URLs.REWARDS_ALL)
    fun getAllRewards(): LiveData<Resource<EcovveAllRewards>>

    @Multipart
    @POST(URLs.USER_DATA)
    fun changeProfileImage(
            @Path("userId") id: String,
            @Part avatar: MultipartBody.Part
    ): LiveData<Resource<EcovveUserPhoto>>

    @POST(URLs.EXCHANGE)
    @FormUrlEncoded
    fun exchangeReward(
            @Field("user_id") id: String,
            @Field("reward_id") reward_id: String
    ): LiveData<Resource<EcovveUser>>

    @POST(URLs.SEEN_NOTIFICATION)
    @FormUrlEncoded
    fun seenNotification(
            @Field("seen") name: String,
            @Field("notification_id") email: String
    ): LiveData<Resource<EcovveNotificationSeen>>

    @POST(URLs.SEARCH_BRAND)
    @FormUrlEncoded
    fun searchBrand(@Field("search") search: String): LiveData<Resource<EcovveBrandSearch>>

    @POST(URLs.CITY_BRAND)
    @FormUrlEncoded
    fun cityBrand(@Field("search") search: String): LiveData<Resource<EcovveCityBrand>>

    @POST(URLs.SIGN_UP)
    @FormUrlEncoded
    fun Signup(@Field("name") name: String,
               @Field("email") email: String,
               @Field("password") password: String,
               @Field("password_confirmation") password_confirmation: String,
               @Field("phone") phone: String
    ): LiveData<Resource<EcovveSignup>>

    @POST(URLs.GIFT_CHECKOUT)
    @FormUrlEncoded
    fun giftCardCheckout(@Field("name") name: String,
                         @Field("description") description: String,
                         @Field("amount") amount: String,
                         @Field("expire_date") expire_date: String,
                         @Field("status") status: String,
                         @Field("giver_id") giver_id: String,
                         @Field("taker_id") taker_id: String,
                         @Field("notification_type") notification_type: String
    ): LiveData<Resource<EcovveGiftCardCheckout>>

    @POST(URLs.GIFT_CHECKOUT)
    @FormUrlEncoded
    fun giftCardCheckoutByEmail(@Field("name") name: String,
                                @Field("description") description: String,
                                @Field("amount") amount: String,
                                @Field("expire_date") expire_date: String,
                                @Field("status") status: String,
                                @Field("notification_type") notification_type: String,
                                @Field("taker_email") taker_email: String
    ): LiveData<Resource<EcovveGiftCardCheckout>>
}