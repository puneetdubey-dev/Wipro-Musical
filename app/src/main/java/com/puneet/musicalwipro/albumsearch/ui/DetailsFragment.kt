package com.puneet.musicalwipro.albumsearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.puneet.musicalwipro.albumsearch.R
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.include_toolbar_details.*

class DetailsFragment : Fragment() {

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_title.text = args.SearchResult.title
        searchResultType.text = args.SearchResult.resultType.name
        searchResultTitle.text = args.SearchResult.title
        searchResultSubTitle.text = args.SearchResult.subTitle
        Glide.with(imageView)
            .load(args.SearchResult.imageUrlLarge)
            .placeholder(R.drawable.ic_music)
            .into(imageView)

        imageView
        button_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
