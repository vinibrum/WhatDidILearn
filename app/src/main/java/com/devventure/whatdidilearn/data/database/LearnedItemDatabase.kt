package com.devventure.whatdidilearn.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.devventure.whatdidilearn.entities.LearnedItem
import com.devventure.whatdidilearn.entities.UnderstandingLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [LearnedItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LearnedItemDatabase: RoomDatabase() {
    abstract fun learnedItemDao(): LearnedItemDao

    companion object {
        var INSTANCE: LearnedItemDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LearnedItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val database = Room
                                .databaseBuilder(
                                    context.applicationContext,
                                    LearnedItemDatabase::class.java,
                                    "learned_item_database"
                                )
                                .addCallback(LearnedItemDatabaseCallBack(scope))
                                .build()
                                INSTANCE = database
                                database
            }
        }

        fun getAll(): List<LearnedItem> {
            return listOf(
                LearnedItem(name = "Kotlin", description = "Linguagem kotlin para Android", understandingLevel = UnderstandingLevel.HIGH),
                LearnedItem(name = "RecyclerView", description = "Listas em Android", understandingLevel = UnderstandingLevel.MEDIUM),
                LearnedItem(name = "DataClass", description = "Kotlin data Class", understandingLevel = UnderstandingLevel.LOW),
                LearnedItem(name = "Life Cycle", description = "Ciclo de vida de uma aplicação Android", understandingLevel = UnderstandingLevel.HIGH),
                LearnedItem(name = "Intent", description = "Como usar intents", understandingLevel = UnderstandingLevel.MEDIUM),
                LearnedItem(name = "Services", description = "Service em  Android", understandingLevel = UnderstandingLevel.MEDIUM),
                LearnedItem(name = "Content Provider", description = "Dados com Contenct Provider", understandingLevel = UnderstandingLevel.LOW),
            )
        }
    }

    private class LearnedItemDatabaseCallBack(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    populateDatabase(it.learnedItemDao())
                }
            }
        }

        private fun populateDatabase(learnedItemDao: LearnedItemDao) {
            val items = getAll()
            learnedItemDao.insert(items)
        }
    }
}