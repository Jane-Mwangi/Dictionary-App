package design.fiti.dictionaryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import design.fiti.dictionaryapp.data.local.entity.WordInfoEntity


@Database(
    entities = [WordInfoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {

    abstract val dao:WordInfoDao
}