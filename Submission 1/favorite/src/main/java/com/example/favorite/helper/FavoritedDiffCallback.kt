package com.example.favorite.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.favorite.model.data.FavoritedStory

class FavoritedDiffCallback(
    private val mOldFavoritedList: List<FavoritedStory>,
    private val mNewFavoritedList: List<FavoritedStory>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavoritedList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavoritedList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavoritedList[oldItemPosition].id == mNewFavoritedList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldStory = mOldFavoritedList[oldItemPosition]
        val newStory = mNewFavoritedList[newItemPosition]
        return oldStory.name == newStory.name && oldStory.description == oldStory.description
    }
}