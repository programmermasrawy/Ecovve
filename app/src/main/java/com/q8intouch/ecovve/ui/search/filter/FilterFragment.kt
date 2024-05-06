package com.q8intouch.ecovve.ui.search.filter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.navigation.fragment.findNavController

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BaseFragment
import com.q8intouch.ecovve.databinding.FragmentFilterBinding
import com.q8intouch.ecovve.util.Shared
import kotlinx.android.synthetic.main.fragment_filter.*
import org.jetbrains.anko.onClick

public class FilterFragment : BaseFragment<FilterViewModel,FragmentFilterBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_filter
    }

    companion object {
        fun newInstance() = FilterFragment()
    }
    var newcafes = -1
    var mastercase = -1
    var knetcase = -1
    var opencase = -1
    var freedeliverycase = -1
    var offerscase = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
//        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    lateinit var longtideAndLat: List<String>
    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        longtideAndLat = arguments!!.getStringArrayList("longtideAndLat")
        val sharedPreference: Shared = Shared(context!!)
        sharedPreference.setList("longtideAndLat",longtideAndLat)
        sharedPreference.save("searchInfoFragment","searchInfoFragment")
        var newcafes =    sharedPreference.getValueString("newcafes")
        var mastercase =    sharedPreference.getValueString("mastercase")
        var knetcase =   sharedPreference.getValueString("knetcase")
        var opencase =   sharedPreference.getValueString("opencase")
        var freedeliverycase =    sharedPreference.getValueString("freedeliverycase")
        var offerscase =    sharedPreference.getValueString("offerscase")
        var sorting =    sharedPreference.getValueString("sort")

        val minimum_charge__asc = "minimum_charge__asc"
        val id__desc = "id__desc"
        val avg_delivery_time__asc = "avg_delivery_time__asc"
        val reviews_rating__desc = "reviews_rating__desc"

        if (!sorting.isNullOrEmpty()){
            when (sorting) {
                minimum_charge__asc -> {
                   minChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
                    minChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)
                }
                id__desc -> {
                    recommendChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
                    recommendChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)
                }
                avg_delivery_time__asc -> {
                    delChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
                    delChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)

                }
                reviews_rating__desc -> {
                    RatingChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
                    RatingChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)
                }
            }

        }


        MinGroups.onClick {
            val sorting =    sharedPreference.getValueString("sort")
            if (sorting.equals(minimum_charge__asc)){
                sharedPreference.removeValue("sort")
                minChip.chipBackgroundColor = getColorStateList(context!!,android.R.color.transparent)
                minChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            }
            else {
                minChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
                minChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)
                sharedPreference.save("sort","minimum_charge__asc")
            }
            recommendChip.setChipBackgroundColorResource(android.R.color.transparent)
            RatingChip.setChipBackgroundColorResource(android.R.color.transparent)
            FastChop.setChipBackgroundColorResource(android.R.color.transparent)
            delChip.setChipBackgroundColorResource(android.R.color.transparent)
            recommendChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            RatingChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            FastChop.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            delChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
        }
        recommendedGroups.onClick {
            var sorting =    sharedPreference.getValueString("sort")
            if (sorting.equals(id__desc)){
                sharedPreference.removeValue("sort")
                recommendChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
                recommendChip.chipBackgroundColor = getColorStateList(context!!,android.R.color.transparent)
            }
            else {
                sharedPreference.save("sort",id__desc)
                recommendChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
                recommendChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)
            }
            minChip.setChipBackgroundColorResource(android.R.color.transparent)
            RatingChip.setChipBackgroundColorResource(android.R.color.transparent)
            FastChop.setChipBackgroundColorResource(android.R.color.transparent)
            delChip.setChipBackgroundColorResource(android.R.color.transparent)
            minChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            RatingChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            FastChop.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            delChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
        }
        FastGroups.onClick {
            var sorting =    sharedPreference.getValueString("sort")

            if (sorting.equals(avg_delivery_time__asc)){
                sharedPreference.removeValue("sort")
                FastChop.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
                FastChop.chipBackgroundColor = getColorStateList(context!!,android.R.color.transparent)
            }
            else {
                sharedPreference.save("sort",avg_delivery_time__asc)
                FastChop.chipIconTint = getColorStateList(context!!,R.color.textWhite)
                FastChop.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
            }
//            sharedPreference.save("sort","avg_delivery_time__asc")
//            FastChop.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
            recommendChip.setChipBackgroundColorResource(android.R.color.transparent)
            RatingChip.setChipBackgroundColorResource(android.R.color.transparent)
            minChip.setChipBackgroundColorResource(android.R.color.transparent)
            delChip.setChipBackgroundColorResource(android.R.color.transparent)
            recommendChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            RatingChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            minChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            delChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
        }
        RatringGroups.onClick {
            var sorting =    sharedPreference.getValueString("sort")

            if (sorting.equals(reviews_rating__desc)){
                sharedPreference.removeValue("sort")
                RatingChip.chipBackgroundColor = getColorStateList(context!!,android.R.color.transparent)
                RatingChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)

            }
            else {
                sharedPreference.save("sort",reviews_rating__desc)
                RatingChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)
                RatingChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
            }
            recommendChip.setChipBackgroundColorResource(android.R.color.transparent)
            minChip.setChipBackgroundColorResource(android.R.color.transparent)
            FastChop.setChipBackgroundColorResource(android.R.color.transparent)
            delChip.setChipBackgroundColorResource(android.R.color.transparent)
                       recommendChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            minChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            FastChop.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            delChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)

        }
        DelveryGroups.onClick {
            var sorting =    sharedPreference.getValueString("sort")

            if (sorting.equals(avg_delivery_time__asc)){
                sharedPreference.removeValue("sort")
                delChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
                delChip.chipBackgroundColor = getColorStateList(context!!,android.R.color.transparent)
            }
            else {
                sharedPreference.save("sort",avg_delivery_time__asc)
                delChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)
                delChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
            }
//            sharedPreference.save("sort","avg_delivery_time__asc")
//            delChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
            recommendChip.setChipBackgroundColorResource(android.R.color.transparent)
            RatingChip.setChipBackgroundColorResource(android.R.color.transparent)
            FastChop.setChipBackgroundColorResource(android.R.color.transparent)
            minChip.setChipBackgroundColorResource(android.R.color.transparent)
            recommendChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            RatingChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            FastChop.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            minChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
        }

        minChip.onClick {
            var sorting =    sharedPreference.getValueString("sort")

            if (sorting.equals(minimum_charge__asc)){
                sharedPreference.removeValue("sort")
                minChip.chipBackgroundColor = getColorStateList(context!!,android.R.color.transparent)
                minChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            }
            else {
                minChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
                minChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)
                sharedPreference.save("sort","minimum_charge__asc")
            }
            recommendChip.setChipBackgroundColorResource(android.R.color.transparent)
            RatingChip.setChipBackgroundColorResource(android.R.color.transparent)
            FastChop.setChipBackgroundColorResource(android.R.color.transparent)
            delChip.setChipBackgroundColorResource(android.R.color.transparent)
            recommendChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            RatingChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            FastChop.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            delChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)

        }

        recommendChip.onClick {
            var sorting =    sharedPreference.getValueString("sort")

            if (sorting.equals(id__desc)){
                sharedPreference.removeValue("sort")
                recommendChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
                recommendChip.chipBackgroundColor = getColorStateList(context!!,android.R.color.transparent)
            }
            else {
                sharedPreference.save("sort",id__desc)
                recommendChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
                recommendChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)
            }
            minChip.setChipBackgroundColorResource(android.R.color.transparent)
            RatingChip.setChipBackgroundColorResource(android.R.color.transparent)
            FastChop.setChipBackgroundColorResource(android.R.color.transparent)
            delChip.setChipBackgroundColorResource(android.R.color.transparent)
            minChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            RatingChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            FastChop.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            delChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
        }

        FastChop.onClick {
            var sorting =    sharedPreference.getValueString("sort")

            if (sorting.equals(avg_delivery_time__asc)){
                sharedPreference.removeValue("sort")
                FastChop.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
                FastChop.chipBackgroundColor = getColorStateList(context!!,android.R.color.transparent)
            }
            else {
                sharedPreference.save("sort",avg_delivery_time__asc)
                FastChop.chipIconTint = getColorStateList(context!!,R.color.textWhite)
                FastChop.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
            }
//            sharedPreference.save("sort","avg_delivery_time__asc")
//            FastChop.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
            recommendChip.setChipBackgroundColorResource(android.R.color.transparent)
            RatingChip.setChipBackgroundColorResource(android.R.color.transparent)
            minChip.setChipBackgroundColorResource(android.R.color.transparent)
            delChip.setChipBackgroundColorResource(android.R.color.transparent)
            minChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            RatingChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            recommendChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            delChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
        }


        RatingChip.onClick {
            var sorting =    sharedPreference.getValueString("sort")

            if (sorting.equals(reviews_rating__desc)){
                sharedPreference.removeValue("sort")
                RatingChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
                RatingChip.chipBackgroundColor = getColorStateList(context!!,android.R.color.transparent)
            }
            else {
                sharedPreference.save("sort",reviews_rating__desc)
                RatingChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)
                RatingChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
            }
//            sharedPreference.save("sort","reviews_rating__desc")
//            RatingChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
            recommendChip.setChipBackgroundColorResource(android.R.color.transparent)
            minChip.setChipBackgroundColorResource(android.R.color.transparent)
            FastChop.setChipBackgroundColorResource(android.R.color.transparent)
            delChip.setChipBackgroundColorResource(android.R.color.transparent)
            minChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            FastChop.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            recommendChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            delChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
        }


        delChip.onClick {
            var sorting =    sharedPreference.getValueString("sort")

            if (sorting.equals(avg_delivery_time__asc)){
                sharedPreference.removeValue("sort")
                delChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
                delChip.chipBackgroundColor = getColorStateList(context!!,android.R.color.transparent)
            }
            else {
                sharedPreference.save("sort",avg_delivery_time__asc)
                delChip.chipIconTint = getColorStateList(context!!,R.color.textWhite)
                delChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
            }
//            sharedPreference.save("sort","avg_delivery_time__asc")
//            delChip.chipBackgroundColor = getColorStateList(context!!,R.color.colorPrimary)
            recommendChip.setChipBackgroundColorResource(android.R.color.transparent)
            RatingChip.setChipBackgroundColorResource(android.R.color.transparent)
            FastChop.setChipBackgroundColorResource(android.R.color.transparent)
            minChip.setChipBackgroundColorResource(android.R.color.transparent)
            minChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            FastChop.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            recommendChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
            RatingChip.chipIconTint = getColorStateList(context!!,R.color.colorPrimary)
        }


        if (knetcase != null)
            knet.isChecked = true

        if (mastercase!=null)
           master.isChecked = true

        if (opencase!=null)
            open.isChecked = true

        if (offerscase!=null)
            offers.isChecked = true

        if (freedeliverycase!=null)
            freed.isChecked = true

       if (newcafes!=null)
           newCafes.isChecked = true


        freed.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, checked: Boolean) {
                if (checked)
                    sharedPreference.save("freedeliverycase","a")
                else
                    sharedPreference.removeValue("freedeliverycase")
            }

        })

        master.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, checked: Boolean) {
                if (checked)
                    sharedPreference.save("mastercase","a")
                else
                    sharedPreference.removeValue("mastercase")
            }

        })

        knet.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, checked: Boolean) {
                if (checked)
                    sharedPreference.save("knetcase","a")
                else
                    sharedPreference.removeValue("knetcase")
            }

        })


        offers.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, checked: Boolean) {
                if (checked)
                    sharedPreference.save("offerscase","a")
                else
                    sharedPreference.removeValue("offerscase")
            }

        })


        open.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, checked: Boolean) {
                if (checked)
                    sharedPreference.save("opencase","a")
                else
                    sharedPreference.removeValue("opencase")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.filter_action, menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.menuFilter -> {
                var bundle = org.jetbrains.anko.bundleOf("longtideAndLat" to longtideAndLat)
//                findNavController().navigate(R.id.searchFragment,bundle)
                findNavController().navigateUp()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
