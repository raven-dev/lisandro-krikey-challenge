package com.lisandrolopez.krikeychallenge.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.lisandrolopez.krikeychallenge.R
import com.lisandrolopez.krikeychallenge.repository.CatRepository
import com.lisandrolopez.krikeychallenge.repository.network.Network
import com.lisandrolopez.krikeychallenge.ui.MainActivity
import com.lisandrolopez.krikeychallenge.ui.catlist.CatListViewModel
import com.lisandrolopez.krikeychallenge.util.GlideApp
import kotlinx.android.synthetic.main.fragment_cat_detail.*

class CatDetailFragment : Fragment() {

    private var homeViewModel: CatListViewModel? = null
    private var detailViewModel: CatDetailViewModel? = null
    private val args: CatDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        activity?.let {
            val catRepo = CatRepository(Network.getInstance())
            val vmFactory = CatListViewModel.Factory(catRepo)
            homeViewModel = ViewModelProvider(it, vmFactory).get(CatListViewModel::class.java)

            detailViewModel = ViewModelProvider(this).get(CatDetailViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_cat_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catImg = args.catimg
        iv_cat_detail.apply {
            transitionName = catImg
            GlideApp.with(this)
                .load(catImg)
                .centerCrop()
                .into(iv_cat_detail)
        }

        homeViewModel?.catSelected?.observe(viewLifecycleOwner, Observer {
            detailViewModel?.setDetailCat(it)
            setCatInfo()
        })

        detailViewModel?.titleEvent?.observe(viewLifecycleOwner, Observer {
            (activity as? MainActivity)?.supportActionBar?.title = it
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_menu_share -> {
                val shareIntent = detailViewModel?.getShareIntent()
                val chooser = Intent.createChooser(shareIntent, null)
                startActivity(chooser)
            }
        }

        return true
    }

    private fun setCatInfo() {
        tv_description.text = detailViewModel?.getDescription()
    }
}
