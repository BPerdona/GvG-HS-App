package br.com.gvg_hs_app.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDao {

    @Query("SELECT * FROM localcard")
    fun getAllCards(): LiveData<List<LocalCard>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllHeroes(heroes: List<LocalCard>)
}