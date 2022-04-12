package com.team3.comp_4200_final_project.db

import android.content.Context
import androidx.room.*
import java.io.Serializable

// Table for Room DB to hold course info
@Entity(tableName="courses")
data class Course(
    @PrimaryKey(autoGenerate = true)    val id: Int = 0,
    @ColumnInfo(name="courseCode")      var courseCode: String = "",
    @ColumnInfo(name="courseName")      var courseName: String = "",
    @ColumnInfo(name="courseTimeRange") var courseTimeRange: String = "",
    @ColumnInfo(name="courseDays")      var courseDays: String = "",
    @ColumnInfo(name="courseLocation")  var courseLocation: String = "",
    @ColumnInfo(name="courseProfessor") var courseProfessor: String = ""
) :Serializable

// Dao for basic insert, retrieve, delete
@Dao
interface CourseDao {
    @Insert
    fun insert(course: Course)

    @Query("SELECT * FROM courses")
    fun getAll(): List<Course>

    @Query("DELETE FROM courses WHERE id=:id")
    fun delete(id: Int)

    @Query("DELETE FROM courses")
    fun deleteAll()
}

// Room DB with ability to query from main thread
@Database(entities = [Course::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "course_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
