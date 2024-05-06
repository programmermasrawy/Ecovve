package com.q8intouch.ecovve.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.q8intouch.ecovve.base.BaseViewModelFactory
import com.q8intouch.ecovve.notification.NotificationViewModel
import com.q8intouch.ecovve.ui.cart.CartViewModel
import com.q8intouch.ecovve.ui.cart.GiftCardOrderViewModel
import com.q8intouch.ecovve.ui.cart.add_address.AddAddressViewModel
import com.q8intouch.ecovve.ui.cart.order_complete.OrderCompleteViewModel
import com.q8intouch.ecovve.ui.cart.order_type.OrderTypeViewModel
import com.q8intouch.ecovve.ui.cart.payment_method_select.PaymentMethodSelectViewModel
import com.q8intouch.ecovve.ui.cart.payment_page.PaymentViewModel
import com.q8intouch.ecovve.ui.contact.ContactUsViewModel
import com.q8intouch.ecovve.ui.gift.GiftCardViewModel
import com.q8intouch.ecovve.ui.gift.BuyGiftCardViewModel
import com.q8intouch.ecovve.ui.gift.GiftCardSecletMethodViewModel
import com.q8intouch.ecovve.ui.home.HomeViewModel
import com.q8intouch.ecovve.ui.login.LoginViewModel
import com.q8intouch.ecovve.ui.login.new_password.NewPasswordViewModel
import com.q8intouch.ecovve.ui.login.register.RegisterViewModel
import com.q8intouch.ecovve.ui.login.register.CodeVerficationViewModel
import com.q8intouch.ecovve.ui.login.reset_password.ResetPasswordViewModel
import com.q8intouch.ecovve.ui.maps.search.SearchMapViewModel
import com.q8intouch.ecovve.ui.offers.OfferItemsViewModel
import com.q8intouch.ecovve.ui.offers.OffersViewModel
import com.q8intouch.ecovve.ui.order.MyOrdersViewModel
import com.q8intouch.ecovve.ui.order.order_feedback.FeedbackViewModel
import com.q8intouch.ecovve.ui.profile.ProfileViewModel
import com.q8intouch.ecovve.ui.program4.Program4UViewModel
import com.q8intouch.ecovve.ui.search.ItemsViewModel
import com.q8intouch.ecovve.ui.search.SearchViewModel
import com.q8intouch.ecovve.ui.search.filter.FilterViewModel
import com.q8intouch.ecovve.ui.settings.SettingsViewModel
import com.q8intouch.ecovve.ui.settings.account_info.AccountInfoViewModel
import com.q8intouch.ecovve.ui.settings.change_password.ChangePasswordViewModel
import com.q8intouch.ecovve.ui.shop_info.MenuCategoryViewModel
import com.q8intouch.ecovve.ui.shop_info.ShopInfoViewModel
import com.q8intouch.ecovve.ui.social.ChatBotViewModel
import com.q8intouch.ecovve.ui.social.add_friends.FindFriendsViewModel
import com.q8intouch.ecovve.ui.social.chat.ChatViewModel
import com.q8intouch.ecovve.ui.social.friends.FriendsViewModel
import com.q8intouch.ecovve.ui.social.groups.GroupsViewModel
import com.q8intouch.ecovve.ui.social.messages.MessagesViewModel
import com.q8intouch.ecovve.ui.splash.SplashViewModel
import com.q8intouch.ecovve.ui.top_new_shops.NavNewCafesViewModel
import com.q8intouch.ecovve.ui.top_new_shops.TopNewShopsViewModel
import com.q8intouch.ecovve.ui.wishlist.WishListViewModel
import com.q8intouch.ecovve.util.extension.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("FunctionName")
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: BaseViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun SplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun LoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CodeVerficationViewModel::class)
    abstract fun CodeVerficationViewModel(viewModel: CodeVerficationViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun SearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemsViewModel::class)
    abstract fun ItemsViewModel(viewModel: ItemsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    abstract fun FilterViewModel(viewModel: FilterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun HomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatBotViewModel::class)
    abstract fun ChatBotViewModel(viewModel: ChatBotViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GiftCardOrderViewModel::class)
    abstract fun GiftCardOrderViewModel(viewModel: GiftCardOrderViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SearchMapViewModel::class)
    abstract fun SearchMapViewModel(viewModel: SearchMapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GroupsViewModel::class)
    abstract fun GroupsViewModel(viewModel: GroupsViewModel): ViewModel

  @Binds
    @IntoMap
    @ViewModelKey(MessagesViewModel::class)
    abstract fun MessagesViewModel(viewModel: MessagesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FriendsViewModel::class)
    abstract fun FriendsViewModel(viewModel: FriendsViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel::class)
    abstract fun NotificationViewModel(viewModel: NotificationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyOrdersViewModel::class)
    abstract fun MyOrdersViewModel(viewModel: MyOrdersViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(FeedbackViewModel::class)
    abstract fun FeedbackViewModel(viewModel: FeedbackViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun RegisterViewModel(viewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResetPasswordViewModel::class)
    abstract fun resetPasswordViewModel(viewModel: ResetPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewPasswordViewModel::class)
    abstract fun NewPasswordViewModel(viewModel: NewPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun CartViewModel(viewModel: CartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel::class)
    abstract fun ChangePasswordViewModel(ChangePasswordViewModel: CartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaymentViewModel::class)
    abstract fun PaymentViewModel(viewModel: PaymentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OffersViewModel::class)
    abstract fun OffersViewModel(viewModel: OffersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OfferItemsViewModel::class)
    abstract fun OfferItemsViewModel(viewModel: OfferItemsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopNewShopsViewModel::class)
    abstract fun TopNewShopsViewModel(viewModel: TopNewShopsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NavNewCafesViewModel::class)
    abstract fun NavNewCafesViewModel(viewModel: NavNewCafesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountInfoViewModel::class)
    abstract fun AccountInfoViewModel(viewModel: AccountInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GiftCardViewModel::class)
    abstract fun GiftCardViewModel(viewModel: GiftCardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactUsViewModel::class)
    abstract fun ContactUsViewModel(viewModel: ContactUsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun ChatViewModel(viewModel: ChatViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BuyGiftCardViewModel::class)
    abstract fun BuyCardViewModel(viewModel: BuyGiftCardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GiftCardSecletMethodViewModel::class)
    abstract fun GiftCardSecletMethodViewModel(viewModel: GiftCardSecletMethodViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FindFriendsViewModel::class)
    abstract fun FindFriendsViewModel(viewModel: FindFriendsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShopInfoViewModel::class)
    abstract fun ShopInfoViewModel(viewModel: ShopInfoViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(WishListViewModel::class)
    abstract fun WishListViewModel(viewModel: WishListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuCategoryViewModel::class)
    abstract fun MenuCategoryViewModel(viewModel: MenuCategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderCompleteViewModel::class)
    abstract fun OrderCompleteViewModel(viewModel: OrderCompleteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaymentMethodSelectViewModel::class)
    abstract fun PaymentMethodSelectViewModel(viewModel: PaymentMethodSelectViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun SettingsViewModel(viewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddAddressViewModel::class)
    abstract fun AddAddressViewModel(viewModel: AddAddressViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderTypeViewModel::class)
    abstract fun OrderTypeViewModel(viewModel: OrderTypeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Program4UViewModel::class)
    abstract fun Program4UViewModel(viewModel: Program4UViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun ProfileViewModel(viewModel: ProfileViewModel): ViewModel

}