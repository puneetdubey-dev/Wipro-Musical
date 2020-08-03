package com.puneet.musicalwipro.albumsearch.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.puneet.musicalwipro.albumsearch.R
import com.puneet.musicalwipro.albumsearch.adapters.ListClickListener
import com.puneet.musicalwipro.albumsearch.adapters.ListItem
import com.puneet.musicalwipro.albumsearch.adapters.ListItemResult
import com.puneet.musicalwipro.albumsearch.adapters.SearchResultAdapter
import com.puneet.musicalwipro.albumsearch.extensions.SearchResultType
import com.puneet.musicalwipro.albumsearch.extensions.toListItem
import com.puneet.musicalwipro.albumsearch.model.CommonSearchResult
import com.puneet.musicalwipro.albumsearch.viewmodels.SearchViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.include_toolbar_search.*
import javax.inject.Inject

class SearchFragment : Fragment(), ListClickListener<CommonSearchResult> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            searchView.setOnQueryTextListener(searchQueryListener)
            observeSearchResults()
       }

    override fun onResume() {
        super.onResume()

        if (viewModel.searchResultsCombined.isNotEmpty()) {
            displayResults(viewModel.searchResultsCombined)
        }
    }

    private fun observeSearchResults() {

        viewModel.searchResultsLive.observe(viewLifecycleOwner,
            Observer {

                hideViews()
                if (it.isEmpty()) {
                    no_results_found.visibility = View.VISIBLE
                } else {
                    recycler_view.visibility = View.VISIBLE
                    displayResults(it)
                }
            })
    }

    private fun hideViews() {
        progress_bar.visibility = View.GONE
        recycler_view.visibility = View.GONE
        no_results_found.visibility = View.GONE
        hideKeyboard()
    }

    private fun displayResults(it: List<CommonSearchResult>) {

        val listItems = buildListItems(it)

        recycler_view.adapter = SearchResultAdapter(listItems, this@SearchFragment)

        recycler_view.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = linearLayoutManager
    }

    private fun buildListItems(it: List<CommonSearchResult>): MutableList<ListItem> {
        val listItems = mutableListOf<ListItem>()

        listItems.addAll(it.filter { resultType -> resultType.resultType == SearchResultType.Album }.toListItem())
        return listItems
    }

    private val searchQueryListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                hideViews()
                progress_bar.visibility = View.VISIBLE
                viewModel.search(it)
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onRowTap(data: CommonSearchResult) {
        if (data is CommonSearchResult) {
            val action =
                SearchFragmentDirections.actionSearchFragmentToDetailsFragment(data)
            findNavController().navigate(action)
        }
    }

    private fun hideKeyboard() {
        context?.let { context ->
            view?.let { view ->
                val inputManager =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(
                    view.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }
}
