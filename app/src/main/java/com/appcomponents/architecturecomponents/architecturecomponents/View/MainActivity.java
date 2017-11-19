package com.appcomponents.architecturecomponents.architecturecomponents.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.appcomponents.architecturecomponents.R;
import com.appcomponents.architecturecomponents.architecturecomponents.Model.AppDatabase;
import com.appcomponents.architecturecomponents.architecturecomponents.Model.Book;
import com.appcomponents.architecturecomponents.architecturecomponents.Model.utils.DatabaseInitializer;
import com.appcomponents.architecturecomponents.architecturecomponents.ViewModel.BooksBorrowedByUserViewModel;

import java.util.List;

/**
 * Created by sachin on 19/11/17.
 */

public class MainActivity extends AppCompatActivity {

    private TextView mBooksTextView;
    private BooksBorrowedByUserViewModel mViewModel;

    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mBooksTextView = findViewById(R.id.books_tv);

        // Get a reference to the ViewModel for this screen.
        mViewModel = ViewModelProviders.of(this).get(BooksBorrowedByUserViewModel.class);

        //With ViewModel and LiveData
        //Update the UI whenever there's a change in the ViewModel's data.
        subscribeUiBooks();

        // Without ViewModel and LiveData
        // Note: Db references should not be in an activity.
       /* mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        populateDb();
        showBooksInUi(mDb.bookModel().findBooksBorrowedByName("Sachin"));*/
    }

    public void onRefreshBtClicked(View view) {
        if (mViewModel!=null)
          mViewModel.createDb();
        else{
            //Without ViewModel and LiveData
            showBooksInUi(mDb.bookModel().findBooksBorrowedByName("Sachin"));
        }
    }

  // for ViewModel Call Back
   private void subscribeUiBooks() {
        //refresh the list of books when there's new data
        mViewModel.books.observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@NonNull final List<Book> books) {
                showBooksInUi(books);
            }
        });
   }

    @SuppressWarnings("unused")
    private void showBooksInUi(final @NonNull List<Book> books) {
        StringBuilder sb = new StringBuilder();

        for (Book book : books) {
            sb.append(book.title);
            sb.append("\n");

        }
        mBooksTextView.setText(sb.toString());
    }

    //For without ViewModel
    private void populateDb() {
        DatabaseInitializer.populateAsync(mDb);
    }
}
