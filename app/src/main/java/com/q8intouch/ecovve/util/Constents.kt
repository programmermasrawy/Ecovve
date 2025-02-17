package com.q8intouch.ecovve.util

object URLs {
    var lang = "en"
    const val BASE_URL = "https://ecovve.com/"
    const val IMAGES_URL = "https://www.ecovve.com/public/"
    const val AUTH_LOGIN = "api/auth/login"
    const val LOGIN_SOCIAL = "api/auth/moible_login"
    const val SEARCH_LATLAON = "api/public/outlet/search/category/latlng"
    const val CHANGE_PASS = "api/auth/change-password"
    const val ALL_CATEGORY = "api/public/category/all"
    const val RANDOMAD = "api/public/ad/random-ad"
    const val VERIFY_MOBILE = "api/auth/signup/mobile_activate/{id}"
    const val CANCEL_ORDER = "api/public/order/cancel_order/{id}"
    const val RESEND_MOBILE_VERVICATION = "api/auth/resend-phone-verification"
    const val DELETE_ADDRESS = "api/public/address/{id}"
    const val SIGN_UP = "api/auth/signup"
    const val GIFT_CHECKOUT = "api/public/gift-card"
    const val RESEND_PASS_CODE = "api/auth/password/phone"
    const val RESEND_PASS_CODE_EMAIL = "api/auth/resend-email-verification"
    const val FORGET_PASS_CODE_EMAIL = "api/auth/password/email"
    const val RESET_PASS_BY_EMAIL = "api/auth/password/reset"
    const val RESET_PASS_BY_PHONE = "api/auth/password/reset/by_phone"
    const val AREA = "api/public/outlet-area/all"
    const val AREA_WITH_CITIES = "api/public/city/cities_with_areas?result_count=100"
    const val USER_DATA = "api/public/user/{userId}"
    const val EXCHANGE = "api/public/rewards/exchange"
    const val SHOW_REVIEW = "api/public/review/{id}"
    const val ORDER_REVIEW = "api/public/order_review/{id}"
    const val OUTLET_AREA = "api/public/outlet/{id}"
    const val ALL_OUTLET = "api/public/outlet/all"
    const val SHOW_FIEND = "api/public/friendship/{id}"
    const val SHOW_ROOM = "api/public/chat-room/{id}"
    const val USER_FRIENDS = "api/public/user/show_user_friends/{id}"
    const val BLOCK_FRIEND = "api/public/user/block/{id}"
    const val UNBLOCK_FRIEND = "api/public/user/unblock/{id}"
    const val ACCEPT_FRIEND = "api/public/user/accept_friendship/{id}"
    const val DECLINE_FRIEND = "api/public/user/decline_friendship/{id}"
    const val SENT_FRIEND_REQUEST = "api/public/friendship/show_sent_relationship/{id}"
    const val RECEIVED_FRIEND_REQUESTS = "api/public/friendship/show_received_relationship/{id}"
    const val ALL_REVIEW = "api/public/review/all"
    const val ALL_CHATROOMS = "api/public/chat-room/all"
    const val USER_CHATROOMS = "api/public/user/group-chatrooms"
    const val USER_PRIVATE_MESSAGES = "api/public/user/private-chatrooms"
    const val CHATROOM_MESSAGES = "api/public/chat-message/{id}"
    const val USER_POINTS = "api/public/user/user_points"
    const val USER_CREDITES = "api/public/user/user_free_credit"
    const val CONVERTE_POINTS_VALUE = "api/public/user/convert_points_value/{id}"
    const val CONVERTE_POINTS_CREDITE = "api/public/user/convert_points_to_free_credit/{id}"
    const val DELETE_REVIEW = "api/public/review/{id}"
    const val DELETE_FRIEND = "api/public/friendship/unfriend/{id}"
    const val ADD_FRIEND = "api/public/friendship"
    const val ECOVVE_SEARCH_FRIEND = "api/public/search/user"
    const val ADD_CHATROOM = "api/public/chat-room"
    const val SEND_MESSAGE = "api/public/chat-message"
    const val LEAVE_ROOM = "api/public/chat-room/leave_chat"
    const val ADD_CHATROOMUsers = "api/public/chat-room/add_users"
    const val DELETE_FRIENDSHIP = "api/public/friendship/{id}"
    const val CART_STORE = "api/public/cart"
    const val UPDATE_CART_STORE = "api/public/cart/{id}"
    const val ShowAllMenu = "api/public/menu/all"
    const val ShowAllBanners = "api/public/banners/all"
    const val ShowMenu = "api/public/menu/{id}"
    const val MENU_ITEMS = "api/public/item/{id}"
    const val Notes = "Notes"
    const val AREA_ID = "area"
    const val PREV_ORDERS = "api/public/user/user_previous_orders/{id}"
    const val USER_ORDERS = "api/public/user/user_orders/{id}"
    const val USER_HABITS = "api/public/user/habits/{id}"
    const val SHOW_ITEM = "api/public/item/{id}"
    const val SHOW_Extra = "api/public/extra/{id}"
    const val ONGING_ORDERS = "api/public/user/user_ongoing_orders/{id}"
    const val ALL_PROMOTION = "api/public/promotion/all"
    const val MOST_SOLD = "api/public/most_sold_items/latest"
    const val PROMOTION = "api/public/promotion/{promotionId}"
    const val LATEST_CAFES = "api/public/brand/latest"
    const val CAFE_INFO = "api/public/outlet/{id}"
    const val USER_FAVORITES = "api/public/user/favorite"
    const val SHOW_ORDER_REVIEW = "api/public/order_review/{id}"
    const val SEND_ORDER_REVIEW = "api/public/order_review"
    const val LOGOUT = "api/auth/logout"
    const val CAFE_MENU = "api/public/menu/{id}"
    const val CAFE_Brand = "api/public/brand/{id}"
    const val OFFER_INFO = "api/public/promotion/{id}"
    const val ACCOUNT_INFO = "api/public/user/{userId}"
    const val SEARCH_BRAND = "api/public/search/brand"
    const val CITY_BRAND = "api/public/search/city_brand"
    const val OFFER_ITEMS = "api/public/promotion/{id}"
    const val GIFT_CARDS = "api/public/gift-card/all"
    const val GIFT_CARD = "api/public/gift-card/{giftId}"
    const val ORDERS = "api/public/user/user_orders/{userId}"
    const val ORDER = "api/public/order/{orderId}"
    //    const val ORDER_REVIEW = "api/public/order_review/{orderId}"
    const val ADD_ADDRESS = "api/public/address"
    const val CHECKOUT = "api/public/order"
    const val CHECK_PRICE = "api/public/order/check-price"
    const val NEW_CAFES = "api/public/outlet/latest"
    const val TOP_CAFES = "api/public/brand/top"
    const val CONTACT = "api/public/contact_us/"
    const val FAQ_LIST = "api/public/faq/all"
    const val FAQ = "api/public/faq/{faqId}"
    const val ALL_NOTIFICATIONS = "api/public/notification/user_notifications"
    const val FaqAll = "api/public/faq/all"
    const val REWARDS_ALL = "api/public/rewards/all"
    const val SEEN_NOTIFICATION = "api/public/notification/seen_action"
    const val ALL_STATUS = "api/public/orderstatus/all"
    const val SHOW_STATUS = "api/public/orderstatus/{id}"
    var URLL = "$BASE_URL/$lang/"
}


const val tempAuthToken =
        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjdjOWI3Mzc0ZWYwODdiYTYwMjA4ZjI0MjM2ZDIxZWU3M2E1MjliNWM1NzA1ZTZkZTFkZDc1ZTc5YzBhMTFmM2YxMzRiYWI2MzcxMTVjMTBiIn0.eyJhdWQiOiIxIiwianRpIjoiN2M5YjczNzRlZjA4N2JhNjAyMDhmMjQyMzZkMjFlZTczYTUyOWI1YzU3MDVlNmRlMWRkNzVlNzljMGExMWYzZjEzNGJhYjYzNzExNWMxMGIiLCJpYXQiOjE1NDU1ODExMDgsIm5iZiI6MTU0NTU4MTEwOCwiZXhwIjoxNTc3MTE3MTA4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.NTroux1mhOl77MBQVqrGGjE_Pyyc64AL9zdUzh6YdWiDPhpxXqI0KqKGVHG9CCiu27Y25FkHteo6UwXTMXhTZdx8qGxRm8P_6U647WDj3NxzZR9X6v3H44gZqjohKMVVvkyYlJAjtwJ8gSRxRI3jUMlItvwVZwZGb_I5M-vcnOLuUUMmOu8cJFYYpX0sfNE4D9Yq9EHXxpO1zYtSZ8Ncv6ndTvjXJGQuNOdZ8sEYD_EB-j6BRMAKpVZdFFUgBOrxjLzGauR9B8ymsK_7gt1Ils1Zg5si52e0cTTVbnyfjCpXK-6wmwcjyFK7TlumYmPzl3kh7r9W44rRlzvvvgHdCmqm5AASdTa5yJQY7tCQX9gHJNLos29LhSQA7CRbZpA9ZBC_1XSN9_p1AExcJYZXtRP61qZxKD1WCrfm8lRDptkedmb3yKB4IsgpRfXVLMhXKlb-wuMYQx9tTq9pSNuwSu3jEj6EPaABNdAunZrsPLS_CSTDb5Ui5jJ57g9ukjUUePVGu2CyRvSs_3mDJIto3nyUMREn60Z_opDWopsxl2i7WPYTsYnuxsE1L17TuMe375re5vN80Nzjiq1NoYwAS7pzLOP43tYvfR5wEctedSZbhssisVbGe1QJOYBoCJHeVcnGw3x7s3pgpVjodxRikfFdgdHlct8ulQaGLmpdp8U"