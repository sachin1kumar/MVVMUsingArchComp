package com.appcomponents.architecturecomponents.architecturecomponents.Model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;
import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by sachin on 19/11/17.
 */

@Dao
public interface BookDao {

    @Query("SELECT * FROM Book WHERE Book.user LIKE :userName")
    LiveData<List<Book>> findBooksBorrowedByLiveDataName(String userName);

    @Query("SELECT * FROM Book WHERE Book.user LIKE :userName")
    List<Book> findBooksBorrowedByName(String userName);

    @Insert(onConflict = IGNORE)
    void insertBook(Book book);

    @Query("DELETE FROM Book")
    void deleteAll();

}
