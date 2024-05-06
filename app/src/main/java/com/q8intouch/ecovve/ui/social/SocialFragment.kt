package com.q8intouch.ecovve.ui.social

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.ui.social.friends.FriendsFragment
import com.q8intouch.ecovve.ui.social.groups.GroupsFragment
import com.q8intouch.ecovve.ui.social.messages.MessagesFragment
import kotlinx.android.synthetic.main.fragment_social.*
import com.q8intouch.ecovve.R.id.imageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.q8intouch.ecovve.EcovveApplication
import io.reactivex.Emitter
import io.reactivex.annotations.SchedulerSupport.IO
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.net.URISyntaxException


class SocialFragment : Fragment() {

    companion object {
        fun newInstance() = SocialFragment()
    }
    private class EchoWebSocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response?) {
            webSocket.send("Hello, it's SSaurel !")
            webSocket.send("What's up ?")
            webSocket.send(ByteString.decodeHex("deadbeef"))
            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
        }

        @SuppressLint("TimberArgCount")
        override fun onMessage(webSocket: WebSocket?, text: String?) {
//            output("Receiving : $text");
            Timber.d("Receiving",text)
        }



        @SuppressLint("TimberArgCount")
        override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
//            output("Receiving bytes : " + bytes!!.hex());
            Timber.d("Receiving",bytes!!.hex().toString())
        }

        @SuppressLint("TimberArgCount")
        override fun onClosing(webSocket: WebSocket, code: Int, reason: String?) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
//            output("Closing : $code / $reason");
            Timber.d("Receiving",""+code)
        }

        @SuppressLint("TimberArgCount")
        override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
//            output("Error : " + t!!.localizedMessage);
            Timber.d("Receiving",t!!.localizedMessage.toString())
        }


        companion object {
            private const val NORMAL_CLOSURE_STATUS = 1000
        }
    }

    private lateinit var viewModel: SocialViewModel
    private var  URL = "https://ecovve.com:6001/"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_social, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabAddFriend.setOnClickListener {
            findNavController().navigate(R.id.action_socialFragment_to_addfriendsFragment)
        }


        viewPager.adapter = viewpagerApater(context!!,childFragmentManager)
//        viewPager.offscreenPageLimit = 3
        tabLayout.setupWithViewPager(viewPager)
        viewModel = ViewModelProviders.of(this).get(SocialViewModel::class.java)
       // fabAddFriend.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_socialFragment_to_addfriendsFragment))


    }

    class viewpagerApater(val con:Context,val fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    MessagesFragment.newInstance()
                }
                1 -> {
                    FriendsFragment.newInstance()
                }
                else -> {
                    GroupsFragment.newInstance()
                }
            }
        }

        override fun getCount(): Int {
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> {
                    con.getString(R.string.messages)
                }
                1 -> {
                    con.getString(R.string.friends)
                }
                else -> {
                    con.getString(R.string.groups)
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.social,menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.menuViewProfile -> {
                findNavController().navigate(R.id.profileFragment)
            }
            R.id.menuProfileSettings -> {
                findNavController().navigate(R.id.settingsFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
