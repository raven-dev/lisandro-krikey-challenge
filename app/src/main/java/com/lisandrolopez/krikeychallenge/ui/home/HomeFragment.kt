package com.lisandrolopez.krikeychallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.lisandrolopez.krikeychallenge.R
import com.lisandrolopez.krikeychallenge.repository.CatRepository
import com.lisandrolopez.krikeychallenge.repository.model.Cat
import com.lisandrolopez.krikeychallenge.repository.network.Network
import com.lisandrolopez.krikeychallenge.repository.network.util.Status
import com.lisandrolopez.krikeychallenge.util.DebouncingQueryTextListener
import com.lisandrolopez.krikeychallenge.util.GridMarginDecorator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), CatListAdapter.OnCatSelectedListener {

    private var homeViewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            val catRepo = CatRepository(Network.getInstance())
            val vmFactory = HomeViewModel.Factory(catRepo)
            homeViewModel = ViewModelProvider(it, vmFactory).get(HomeViewModel::class.java)
            homeViewModel?.getCatList()
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
        str_main.isRefreshing = true
        str_main.setOnRefreshListener {
            homeViewModel?.getCatList()
        }

        prepareAdapter()
        retrieveCatList()

        et_search.addTextChangedListener(DebouncingQueryTextListener(lifecycle) {
            homeViewModel?.search(it)
        })

        homeViewModel?.isLoadingEvent?.observe(viewLifecycleOwner, Observer {
            str_main.isRefreshing = it
        })
    }

    private fun retrieveCatList() {
        homeViewModel?.kittiesEvent?.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    setCats(result.data)
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), result.message ?: "", Toast.LENGTH_LONG).show()
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
        tv_no_cats.visibility = if (catList.isNullOrEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }

        val adapter = CatListAdapter(catList ?: listOf(), this)
        rv_cats.adapter = adapter
    }

    override fun onCatSelected(imageView: ImageView, cat: Cat) {
        homeViewModel?.catToShow(cat)
        val extras = FragmentNavigatorExtras(imageView to cat.url!!)
        val action = HomeFragmentDirections.actionNavigationHomeToCatDetailFragment(cat.url ?: "")

        findNavController().navigate(action, extras)

    }
}
