package com.appcomponents.architecturecomponents.architecturecomponents.Model.utils;

import android.os.AsyncTask;

import com.appcomponents.architecturecomponents.architecturecomponents.Model.AppDatabase;
import com.appcomponents.architecturecomponents.architecturecomponents.Model.Book;

/**
 * Created by sachin on 19/11/17.
 */

public class DatabaseInitializer {

    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }
    }

    private static void populateWithTestData(AppDatabase db) {

        db.bookModel().deleteAll();

        try {
            addBook(db, "1", "Dune","Sachin","1999");
            Thread.sleep(500);
            addBook(db, "2", "Monk","Sachin","2000");
            Thread.sleep(500);
            addBook(db, "3", "The War of the Worlds","Sachin","2001");
            Thread.sleep(500);
            addBook(db, "4", "Brave New World","Sachin","2000");
            Thread.sleep(500);
            addBook(db, "5", "Stay Hungary","Sachin","2010");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void addBook(final AppDatabase db, final String id, final String title,
                                final String user,final String year) {
        Book book = new Book();
        book.id = id;
        book.title = title;
        book.user = user;
        book.year = year;
        db.bookModel().insertBook(book);
    }
}
