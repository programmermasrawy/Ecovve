package com.q8intouch.ecovve.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.Navigation

import com.q8intouch.ecovve.R
import kotlinx.android.synthetic.main.fragment_profile.*
import android.view.Gravity
import com.fxn.pix.Pix
import com.fxn.utility.ImageQuality
import com.fxn.pix.Options
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.fxn.utility.Constants
import com.fxn.utility.PermUtil
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentProfileBinding
import com.q8intouch.ecovve.network.model.EcovveUser
import com.q8intouch.ecovve.util.Shared
import com.q8intouch.ecovve.util.URLs
import com.q8intouch.ecovve.util.extension.errorResponse
import kotlinx.android.synthetic.main.app_bar_home.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class ProfileFragment : BaseFragment<ProfileViewModel,FragmentProfileBinding>(),PopupMenu.OnMenuItemClickListener {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val sharedPreference: Shared = Shared(context!!)
        Glide.with(context!!).load(URLs.IMAGES_URL+ sharedPreference.getValueString("photo")).into(activity!!.imgToolbar)
        binding.txtUserFullName.text = sharedPreference.getValueString("name")
        binding.txtAddress.text = sharedPreference.getValueString("user_address")
        binding.txtFriendsCount.text = ""
        val image = arguments!!.getString("photo","")
        if (image.isNotEmpty()) {
            val file = File(image);
            val id = ""+com.q8intouch.ecovve.util.Constants.USER_ID
            val reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            val mam = MultipartBody.Part.createFormData("avatar", file.getName(), reqFile);
            viewModel.changeImage(id, mam).observe(this, Observer {
                if (it.isSuccess) {
                    Glide.with(context!!).load(URLs.IMAGES_URL+ it.resource!!.data!![0]!!.user!!.avatar).into(activity!!.imgToolbar)
                } else {
                    Log.e("powokwmke", it.errorResponse().message)
                }
            })
        }
        var reviewsData = ArrayList<EcovveUser.Data.Reviews.ReviewITem>()
        val serializedObject = sharedPreference.getValueString("reviews")
        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<EcovveUser.Data.Reviews.ReviewITem>>() {
            }.type
            reviewsData = gson.fromJson(serializedObject, type)
        }
        if (reviewsData!=null) {
            val adapter = ReviewsAdapter(reviewsData, context!!, object :
                    ReviewsAdapter.ControlsListeners {
                override fun click(position: Int) {
                    val bundle = Bundle()
                    bundle.putParcelable("data",reviewsData[position])
                findNavController().navigate(R.id.action_profileFragment_to_feedbackDetailFragment,bundle)
                }
            })
            val layoutmanager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
            rvProfileReviews.layoutManager = layoutmanager
            rvProfileReviews.adapter = adapter
        }
//        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        frmChat.setOnClickListener {
            findNavController().navigate(R.id.action_global_chatFragment)
        }
        btnChat.setOnClickListener {
            findNavController().navigate(R.id.action_global_chatFragment)
        }
        frameAddFriend.setOnClickListener {
//            findNavController().navigate(R.id.findFriendsFragment)
        }
        fabAddFriend.setOnClickListener {
            findNavController().navigate(R.id.addfriendsFragment)
        }

        rvProfileReviews.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_profileFragment_to_feedbackDetailFragment))
        btnOptionsMenu.setOnClickListener {
            val popupMenu = PopupMenu(activity!!,it,(Gravity.START))
            popupMenu.inflate(R.menu.profile_secondry)
            popupMenu.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater!!.inflate(R.menu.profile,menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId){
            R.id.menuEditProfile -> {
                val popupMenu = PopupMenu(activity!!,btnOptionsMenu,(Gravity.END or Gravity.TOP))
                popupMenu.inflate(R.menu.profile_main)
                popupMenu.show()
                popupMenu.setOnMenuItemClickListener(this);

            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item!!.itemId){
            com.q8intouch.ecovve.R.id.menuUploadPhoto ->{

//                Pix.start(activity!!, Options.init().setRequestCode(100));

                val options = Options.init()
                        .setRequestCode(100)                                                 //Request code for activity results
                        .setCount(1)                                                         //Number of images to restict selection count //Front Facing camera on start
                        .setImageQuality(ImageQuality.HIGH)                                  //Image Quality
                        .setImageResolution(1024, 800)                                       //Custom Resolution
                        .setScreenOrientation(Options.SCREEN_ORIENTATION_REVERSE_PORTRAIT) //Orientaion
//                        .setPath("/pix/images")                                             //Custom Path For Image Storage

                Pix.start(activity!!,100)

//                Pix.start(activity!!, options)
            }
        }
        return true
    }


   override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
           when (requestCode) {
                PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS -> {
                   // If request is cancelled, the result arrays are empty.
                   if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                       Pix.start(activity!!,100)

                       val options = Options.init()
                               .setRequestCode(100)                                                 //Request code for activity results
                               .setCount(1)                                                         //Number of images to restict selection count //Front Facing camera on start
                               .setImageQuality(ImageQuality.HIGH)                                  //Image Quality
                               .setImageResolution(1024, 800)                                       //Custom Resolution
                               .setScreenOrientation(Options.SCREEN_ORIENTATION_REVERSE_PORTRAIT) //Orientaion
//                        .setPath("/pix/images")                                             //Custom Path For Image Storage

//                       Pix.start(activity!!, options)
                   } else {
                       Toast.makeText(context!!, "Approve permissions to open Pix ImagePicker", Toast.LENGTH_LONG).show();
                   }
                   return;
               }
           }
       }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("jajkhjodhidjms nabanab","..................................................")

        if (requestCode == 100) {
            val returnValue = data!!.getStringArrayListExtra(Pix.IMAGE_RESULTS)
            Snackbar.make(view!!,returnValue[0],Snackbar.LENGTH_LONG).show()

            var file =  File(returnValue[0]);
            Log.e("ullurl",returnValue[0].toString())
            var id = activity!!.intent.extras.getString("amount","")
            var reqFile = RequestBody.create(MediaType.parse("image/*"), file);
           var image = MultipartBody.Part.createFormData("avatar", file.getName(), reqFile);
            viewModel.changeImage(id,image).observe(this, Observer {
                if (it.isSuccess){
                    Snackbar.make(view!!,"sucess",Snackbar.LENGTH_LONG).show()

                    Log.e("kmkahha", it.resource!!.data!![0]!!.message!!)
                }
                else {
                    Log.e("powokwmke",it.errorResponse().message)
                }
            })
            Glide.with(context!!).load(returnValue[0])
                    .into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            if (resource != null) {
//                          var surveyImagesParts = new MultipartBody.Part[categories.size()];
                            }
                        }
                          })
        }
        else {
            Snackbar.make(view!!,"mdjdldk",Snackbar.LENGTH_LONG).show()

        }
    }
}
