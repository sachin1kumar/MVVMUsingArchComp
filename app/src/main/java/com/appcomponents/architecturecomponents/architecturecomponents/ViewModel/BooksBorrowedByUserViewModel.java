package com.appcomponents.architecturecomponents.architecturecomponents.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.appcomponents.architecturecomponents.architecturecomponents.Model.AppDatabase;
import com.appcomponents.architecturecomponents.architecturecomponents.Model.Book;
import com.appcomponents.architecturecomponents.architecturecomponents.Model.utils.DatabaseInitializer;

import java.util.List;

/**
 * Created by sachin on 19/11/17.
 */

public class BooksBorrowedByUserViewModel extends AndroidViewModel {

    public final LiveData<List<Book>> books;

    private AppDatabase mDb;

    public BooksBorrowedByUserViewModel(Application application) {
        super(application);
        createDb();

        books = mDb.bookModel().findBooksBorrowedByLiveDataName("Sachin");
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication());

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);
    }
}
