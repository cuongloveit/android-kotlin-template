package com.template.kotlintemplate.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.givestech.data.Repository
import io.reactivex.Flowable

@Dao
interface RepositoryDAO {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertRepository(vararg repository: Repository)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertRepositories(repositories: List<Repository>)

  @Query("SELECT * FROM Repository")
  fun getAllRepository(): Flowable<List<Repository>>

  @Query("DELETE  FROM Repository")
  fun deleteAllRepositories()

}