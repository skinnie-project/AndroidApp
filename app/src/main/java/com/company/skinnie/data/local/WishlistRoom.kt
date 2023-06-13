package com.company.skinnie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.company.skinnie.data.response.ResponsePopularItem

@Database(entities = [ResponsePopularItem::class], version = 1)
abstract class WishlistRoom : RoomDatabase(){

    companion object {
        @Volatile
        private var INSTANCE: WishlistRoom? = null

        @JvmStatic
        fun getDatabase(context: Context): WishlistRoom {
            if (INSTANCE == null) {
                synchronized(WishlistRoom::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        WishlistRoom::class.java, "Wishlist_database")
                        .build()
                }
            }
            return INSTANCE as WishlistRoom
        }
    }

    abstract fun wishlistDao() : WishlistDao
}