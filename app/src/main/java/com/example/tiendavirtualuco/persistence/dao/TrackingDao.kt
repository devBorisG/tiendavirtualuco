package com.example.tiendavirtualuco.persistence.dao

import androidx.room.*
import com.example.tiendavirtualuco.persistence.entity.TrackingEntity

@Dao
interface TrackingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: List<TrackingEntity>)

    @Query("SELECT * FROM tracking")
    suspend fun getAllTracks(): List<TrackingEntity>

    @Query("DELETE FROM tracking WHERE idTrack = :id")
    suspend fun deleteTrackById(id: Int)

    @Query("SELECT * FROM tracking WHERE idTrack = :idTrack")
    suspend fun getTracksByIdTrack(idTrack: Int): List<TrackingEntity>
}
