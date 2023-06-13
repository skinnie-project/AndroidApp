package com.company.skinnie.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlistProduct")
data class WishlistProduct (
    @field:ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @field:ColumnInfo(name = "product_name")
    val productName: String = "",

    ): java.io.Serializable