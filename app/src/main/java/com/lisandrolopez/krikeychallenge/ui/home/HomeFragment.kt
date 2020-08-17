package com.lisandrolopez.krikeychallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lisandrolopez.krikeychallenge.R
import com.lisandrolopez.krikeychallenge.repository.CatRepository
import com.lisandrolopez.krikeychallenge.repository.model.Cat
import com.lisandrolopez.krikeychallenge.repository.network.Network
import com.lisandrolopez.krikeychallenge.repository.network.util.Status
import com.lisandrolopez.krikeychallenge.util.GridMarginDecorator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var homeViewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            val catRepo = CatRepository(Network.getInstance())
            val vmFactory = HomeViewModel.Factory(catRepo)
            homeViewModel = ViewModelProvider(it, vmFactory).get(HomeViewModel::class.java)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareAdapter()

        homeViewModel?.kittiesEvent?.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    setCats(result.data)
                }
                Status.ERROR -> {

                }
            }
        })
    }

    private fun prepareAdapter() {
        rv_cats.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        rv_cats.layoutManager = layoutManager
        rv_cats.addItemDecoration(GridMarginDecorator(
            resources.getDimensionPixelSize(R.dimen.grid_margin)
        ))
    }

    private fun setCats(catList: List<Cat>?) {
        catList?.let {
            val adapter = CatListAdapter(it)
            rv_cats.adapter = adapter
        }
    }
}