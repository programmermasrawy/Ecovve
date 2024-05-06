package com.q8intouch.ecovve.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import br.com.ilhasoft.support.validation.Validator
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.twitter.ParseTwitterUtils;
import com.parse.ParseUser;
import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentLoginBinding
import com.q8intouch.ecovve.ui.HomeActivity
import com.q8intouch.ecovve.util.LoadingDialog
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.extension.errorResponse
import com.squareup.okhttp.FormEncodingBuilder
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.onClick
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(){
    override fun getLayoutRes(): Int = R.layout.fragment_login
    val callbackManager = CallbackManager.Factory.create()
    private lateinit var googleSignInClient:GoogleSignInClient
    private val RC_SIGN_IN = 9001
    private lateinit var validator: Validator

     fun isInternetAvailable(context : Context):Boolean {
        var connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                .getActiveNetworkInfo().isConnected();
    }

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.spanCreateAccount
                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment))
        // twitter()
        binding.btnLogin.onClick {
            if (binding.etUsername.text.isEmpty()) {
                binding.etUsername.error = resources.getString(R.string.error_message_empty_validation)
            } else if (binding.etPassword.text.isEmpty()) {
                binding.etPassword.error = resources.getString(R.string.error_message_empty_validation)
            } else {
                if(isInternetAvailable(context!!)){
                    val android_id = Settings.Secure.getString(activity!!.contentResolver,
                            Settings.Secure.ANDROID_ID)
                val dialog = LoadingDialog.showDialog(view.context)
                viewModel.login(chkRememberMe.isChecked, etUsername.text.toString()
                        , etPassword.text.toString(),android_id
                ).observe(this, Observer {
                    if (it.isSuccess) {
                        dialog.dismiss()
                        if (it.resource!!.data.phone != null) {
                            if (binding.chkRememberMe.isChecked) {
                                val sharedPreference: Shared = Shared(context!!)
                                sharedPreference.save("id", it.resource!!.data.id!!)
                                sharedPreference.save("token", "" + it.resource!!.accessToken)
                                val intent = Intent(activity, HomeActivity::class.java)
                                intent.putExtra("amount", "" + it.resource!!.data.id!!)
                                activity!!.finish()
                                startActivity(intent)
//                                var bundle = bundleOf("amount" to "" + it.resource!!.data.id!!)
//                                findNavController().navigate(R.id.action_loginFragment_to_homeActivity, bundle)
                            } else {
//                                var bundle = bundleOf("amount" to "" + it.resource!!.data.id!!)
//                                findNavController().navigate(R.id.action_loginFragment_to_homeActivity, bundle)
                                val intent = Intent(activity, HomeActivity::class.java)
                                intent.putExtra("amount", "" + it.resource!!.data.id!!)
                                activity!!.finish()
                                startActivity(intent)
                            }
                        }
                    } else {
                        Snackbar.make(view, it.errorResponse().message + "", Snackbar.LENGTH_LONG).show()
                        dialog.dismiss()
                    }
                })
                }
                else {
                    val factory = LayoutInflater.from(activity!!)
                    val addToCartDialogView = factory.inflate(R.layout.no_internet, null)
                    val deleteDialog = android.app.AlertDialog.Builder(activity!!).create()
                    deleteDialog.setView(addToCartDialogView)
                    deleteDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    deleteDialog.show()

                    deleteDialog.findViewById<AppCompatButton>(R.id.cancel).setOnClickListener {
                        //delete
                        activity!!.recreate()
                        deleteDialog.dismiss()
                    }
                }
                }
        }
        binding.spanForgotPassword
                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_restPasswordFragment))



        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Scope(Scopes.DRIVE_FULL))
                .requestServerAuthCode(resources.getString(R.string.server_client_id))
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(activity!!, gso)
        binding.btnGoogleLogin.setOnClickListener {
                signOut()
                signInGoogle()
        }
        binding.TwitterLogin.setOnClickListener {
            getUserEmail()
//            loginButtonTwitter?.performClick()
        }

//        loginButtonTwitter?.callback = object : Callback<TwitterSession>() {
//            override fun success(result: Result<TwitterSession>?) {
//                var session = TwitterCore.getInstance()
//                        .sessionManager.activeSession;
//                var token = session.authToken.toString()
//                Snackbar.make(view!!, token.toString(), Snackbar.LENGTH_LONG).show()
//                socialLogin("twitter", session!!.id.toString(), session!!.id.toString())
//
//            }
//
//            override fun failure(exception: TwitterException?) {
//                Snackbar.make(view!!, exception!!.localizedMessage.toString(), Snackbar.LENGTH_LONG).show()
//            }
//        };

//            binding.TwitterLogin.setOnClickListener {
//                //check if user is already authenticated or not
////            if (getTwitterSession() == null) {
//
//                //if user is not authenticated start authenticating
//                client!!.authorize(activity, object : Callback<TwitterSession>() {
//                    override fun success(result: com.twitter.sdk.android.core.Result<TwitterSession>?) {
//                        Log.d("FBLOGIN", result!!.data.authToken.token.toString()+"" + "")
//
//                        viewModel.login("https://ecovve.com/public/auth/twitter/" +result!!.data.authToken.token.toString()+"")
//                            .observe(this@LoginFragment, Observer {  if (it.isSuccess) {
//                            Snackbar.make(view!!,it.resource.toString(),Snackbar.LENGTH_LONG).show()
//                            if (it.resource!!.data.phone != null) {
//                                if (binding.chkRememberMe.isChecked) {
//                                    val sharedPreference: Shared = Shared(context!!)
//                                    sharedPreference.save("id",it.resource!!.data.id!!)
//                                    sharedPreference.save("token",""+it.resource!!.accessToken)
//                                    var bundle = bundleOf("amount" to "" + it.resource!!.data.id!!)
//                                    findNavController().navigate(R.id.action_loginFragment_to_homeActivity, bundle)
//                                } else {
//                                    var bundle = bundleOf("amount" to "" + it.resource!!.data.id!!)
//                                    findNavController().navigate(R.id.action_loginFragment_to_homeActivity, bundle)
//                                }
//                            }
//                        }
//                        else
//                            Snackbar.make(view!!,it.error.toString()+"",Snackbar.LENGTH_LONG).show()
//                        })
//
//                    }
//
//                    override fun failure(exception: TwitterException) {
//                        Snackbar.make(view!!,exception.localizedMessage.toString()+"",Snackbar.LENGTH_LONG).show()
//                    }
//                })
////            }
////                else {
////                Snackbar.make(view!!,"here",Snackbar.LENGTH_LONG).show()
////            }
//
//        }
        facebookLogin()
    }

    fun getUserEmail() {
        ParseUser.logOut();
        val dialog = LoadingDialog.showDialog(view!!.context)
    ParseTwitterUtils.logIn(context, object : LogInCallback {
        override fun done(user: ParseUser?, err: ParseException?) {
            dialog.dismiss()
            if (err != null) {
//                ParseUser.logOut();
                Log.e("err", "err", err);
            }
            if (user == null) {
//                ParseUser.logOut();
//                Toast.makeText(context!!, "The user cancelled the Twitter login.", Toast.LENGTH_LONG).show();
                Log.d("MyApp", "Uh oh. The user cancelled the Twitter login.");
            } else if (user.isNew) {
//                dlg.dismiss();
//                user.sessionToken
//                Toast.makeText(context!!, "User signed up and logged in through Twitter."+ user.sessionToken, Toast.LENGTH_LONG).show();
                Log.d("MyApp", "User signed up and logged in through Twitter!");
                user.username = ParseTwitterUtils.getTwitter().getScreenName();
                socialLogin(ParseTwitterUtils.getTwitter().authTokenSecret,ParseTwitterUtils.getTwitter().userId,ParseTwitterUtils.getTwitter().authToken)
            } else {
                socialLogin(ParseTwitterUtils.getTwitter().authTokenSecret,ParseTwitterUtils.getTwitter().userId,ParseTwitterUtils.getTwitter().authToken)
                Log.e("MyApp", ParseUser.getCurrentSessionToken());
                Log.e("MyApp", user.sessionToken);
                Log.e("MyApp", ParseTwitterUtils.getTwitter().authToken);
                Log.e("MyApp", ParseTwitterUtils.getTwitter().authTokenSecret);
                Log.e("MyApp",user.objectId);
                Log.e("MyApp",ParseTwitterUtils.getTwitter().userId);
            }
        }
     });
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
           RC_SIGN_IN -> {
//                if (resultCode == Activity.RESULT_OK) {
                    Log.e("jam", "لما بتكون ريليز بتوصل هنا برده")
                            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                                handleSignInResultGoogleREsult(task)
           }
            else -> callbackManager.onActivityResult(requestCode, resultCode, data);
        };
    }



    private fun handleSignInResultGoogleREsult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val dialog = LoadingDialog.showDialog(view!!.context)
            var account = completedTask.getResult(ApiException::class.java)


            if (account != null) {
                var client = OkHttpClient();

                var requestBody = FormEncodingBuilder()
                        .add("grant_type", "authorization_code")
                        .add("client_id", getString(R.string.server_client_id))
                        .add("client_secret", "8_MZrUHWbep45MDKeIpeKnZm")
                        .add("redirect_uri", "")
                        .add("code", account.serverAuthCode)
                        .build()
                var request = Request.Builder()
                        .url("https://www.googleapis.com/oauth2/v4/token")
                        .post(requestBody!!)
                        .build();

                client.newCall(request).enqueue(object : com.squareup.okhttp.Callback {
                    override fun onResponse(response: com.squareup.okhttp.Response?) {
                        try {
                            dialog.dismiss()
                            var jsonObject = JSONObject(response!!.body()!!.string());
                            var accessToken = jsonObject.getString("access_token");
//            response.body().string()
                            Log.e("jam", accessToken)
                            Log.e("jam", account.id.toString())
                            val bundle = Bundle()
                            bundle.putString("id", account.id.toString())
                            bundle.putString("token", accessToken)
                            findNavController().navigate(R.id.action_loginFragment_to_loginFragment, bundle)
//                        getlogingoogle(completedTask.result!!.id.toString(), accessToken)
                        } catch (e: JSONException) {
                            Log.e("loggg", e.localizedMessage)
                        }
                    }

                    override fun onFailure(request: Request?, e: IOException?) {
                        Toast.makeText(context!!, e!!.message, Toast.LENGTH_LONG).show()
                        dialog.dismiss()
                    }

                });
            }
        }
        catch (e:ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("marcelo", "signInResult:failed code=" + e.getStatusCode());

        }

    }

    fun signOut() {
        googleSignInClient!!.signOut().addOnCompleteListener {
            Log.e("logout","googe")
        }
        googleSignInClient!!.revokeAccess().addOnCompleteListener {
            Log.e("logout","googe")
        }
    }
    private fun facebookLogin() {
//        ParseUser.logOut();
        val loginButton = view!!.findViewById<LoginButton>(R.id.btnFacebookLogin);
        binding.btnFacebook.setOnClickListener {
            loginButton.performClick()
//            val permissions = Arrays.asList("public_profile", "email");
//    ParseFacebookUtils.logInWithReadPermissionsInBackground(activity, permissions, object : LogInCallback {
//        override fun done(user: ParseUser?, err: ParseException?) {
//            if (err != null) {
//                ParseUser.logOut();
//                Log.e("err", "err", err);
//            }
//            if (user == null) {
//
////                ParseUser.logOut();
////                Toast.makeText(activity!!, "The user cancelled the Facebook login.", Toast.LENGTH_LONG).show();
//                Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
//            } else if (user.isNew) {
//                ParseUser.getCurrentUser().sessionToken
//                Toast.makeText(activity!!, "User signed up and logged in through Facebook.", Toast.LENGTH_LONG).show()
//                Log.d("MyApp", "User signed up and logged in through Facebook!");
////                getUserDetailFromFB();
//            } else {
//                Toast.makeText(activity!!, "User logged in through Facebook.", Toast.LENGTH_LONG).show();
//
//                Log.d("MyApp", "User logged in through Facebook!");
//                getUserDetailFromFB();
//            }
//        }
// });
        }
        val permissions = Arrays.asList("public_profile", "email");
        loginButton.setReadPermissions(permissions);
        // If you are using in a fragment, call loginButton.setFragment(this);
        loginButton.fragment = this;
        // Callback registration

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                val request = GraphRequest.newMeRequest(result!!.accessToken) { `object`, response ->
                    try {
//
//                        AccessToken.createFromNativeLinkingIntent(activity!!.intent,context!!.applicationContext.applicationInfo.uid.toString()
//                        , object : AccessToken.AccessTokenCreationCallback{
//                            override fun onSuccess(token: AccessToken?) {
//                                Log.e("FBLOGIN_JSON_RES", token!!.token)
//                            }
//                            override fun onError(error: FacebookException?) {
//                                Log.e("FBLOGIN_JSON_RES", "error ")
//                            }
//                        })
                       var accessTokenTracker = object : AccessTokenTracker() {
                           override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
                               var accessToken = AccessToken.getCurrentAccessToken()
                               Log.e("FBLOGIN_JSON_S", accessToken!!.token.toString())
                               Log.e("FBLOGIN_JSON_S", accessToken!!.userId.toString())
                           }
                    }

                        Log.e("FBLOGIN_JSON_RES", `object`.toString())
                        Log.e("FBLOGIN_JSON_RES",  LoginManager.getInstance().authType.toString())
                        Log.e("FBLOGIN_JSON_RES", AccessToken.getCurrentAccessToken().token)
                        Log.e("FBLOGIN_JSON_RES", result!!.accessToken.applicationId)
                        Log.e("FBLOGIN_JSON_RES", result!!.accessToken.userId)
                        Log.e("FBLOGIN_JSON_RES", AccessToken.getCurrentAccessToken().userId)
                        Log.e("FBLOGIN_JSON_RES", result!!.accessToken.expires.time.toString())
                        if (`object`.has("id")) {
                            socialLogin("facebook", AccessToken.getCurrentAccessToken().userId, AccessToken.getCurrentAccessToken().token)
                        } else {
                            Log.e("FBLOGIN_FAILD", `object`.toString())
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context!!, e.printStackTrace().toString(), Toast.LENGTH_LONG).show()
                    }
                }

                var parameters =  Bundle();
                parameters.putString("fields","name,email");
                request.setParameters(parameters);
                request.executeAsync();
                Log.e("FBLOGIN", result!!.accessToken.token.toString())
            }

            override fun onCancel() {
                Log.e("FBLOGIN_FAILD", "Cancel")
            }

            override fun onError(error: FacebookException?) {
                Log.e("FBLOGIN_FAILD", "ERROR", error)
            }
        });
    }
var m = true
    private fun socialLogin(authProvider: String, Id: String, TOKEN: String) {
        if (m) {
            val dialog = LoadingDialog.showDialog(view!!.context)
            val android_id = Settings.Secure.getString(activity!!.contentResolver,
                    Settings.Secure.ANDROID_ID)
            viewModel.login(authProvider, Id, TOKEN, context!!,android_id, findNavController())
                    .observe(this, Observer {
                        if (it.isSuccess) {
                            dialog.dismiss()
                            Snackbar.make(view!!, it.resource.toString(), Snackbar.LENGTH_LONG)
                            if (it.resource!!.data.phone != null) {
                                val sharedPreference: Shared = Shared(context!!)
                                sharedPreference.save("id", it.resource!!.data.id!!)
                                sharedPreference.save("token", "" + it.resource!!.accessToken)
//                                var bundle = bundleOf("amount" to "" + it.resource!!.data.id!!)
//                                findNavController().navigate(R.id.action_loginFragment_to_homeActivity, bundle)
                                val intent = Intent(activity, HomeActivity::class.java)
                                intent.putExtra("amount", "" + it.resource!!.data.id!!)
                                activity!!.finish()
                                startActivity(intent)
                            }
                        } else {
                            Snackbar.make(view!!, it.error.toString() + "", Snackbar.LENGTH_LONG).show()
                            dialog.dismiss()
                        }
                    })
            m = false
        }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private  fun getUserDetailFromFB(){
         var request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), object : GraphRequest.GraphJSONObjectCallback{
             override fun onCompleted(obj: JSONObject?, response: GraphResponse?) {
                 Log.e("onk",obj.toString())


                var user = ParseUser.getCurrentUser();
                 Log.e("onk", user.sessionToken)
                 Log.e("onk", user.sessionToken)
                try{
                    user.setUsername(obj!!.getString("name"));
                }catch( e: JSONException){
                    e.printStackTrace();
                }
                try{
                    user.setEmail(obj!!.getString("email"));
                }catch( e:JSONException){
                    e.printStackTrace();
                }
                user.saveInBackground {

                };
             }
         });
        var parameters =  Bundle();
        parameters.putString("fields","name,email");
        request.setParameters(parameters);
        request.executeAsync();
     }
}