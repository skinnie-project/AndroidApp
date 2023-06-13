package com.company.skinnie.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.company.skinnie.data.response.ResponsePopularItem

@Dao
interface WishlistDao {
    @Insert
    suspend fun insert(wishlistProduct: ResponsePopularItem)

    @Query("SELECT * FROM wishlistProduct")
    fun getWishlist(): LiveData<List<ResponsePopularItem>>

    @Query("SELECT EXISTS(SELECT * FROM wishlistProduct WHERE wishlistProduct.id = :id)")
    suspend fun checkWishlist(id: Int): Int

    @Query("DELETE FROM wishlistProduct WHERE wishlistProduct.id = :id")
    suspend fun delete(id: Int): Int

}