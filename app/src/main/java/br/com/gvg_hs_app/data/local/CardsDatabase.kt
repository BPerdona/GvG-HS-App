package br.com.gvg_hs_app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalCard::class], version = 1)
abstract class CardsDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object{

        @Volatile
        private var INSTANCE: CardsDatabase? = null

        fun getInstance(context: Context): CardsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardsDatabase::class.java,
                    "gvg_cards_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}