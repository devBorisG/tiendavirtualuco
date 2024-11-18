package com.example.tiendavirtualuco.persistence.dao

import androidx.room.*
import com.example.tiendavirtualuco.persistence.entity.TrackingEntity

@Dao
interface TrackingDao {

    @Insert
    suspend fun insertTrack(track: List<TrackingEntity>)

    @Query("SELECT * FROM tracking")
    suspend fun getAllTracks(): List<TrackingEntity>

}