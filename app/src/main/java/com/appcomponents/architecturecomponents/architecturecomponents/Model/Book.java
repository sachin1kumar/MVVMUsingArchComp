package com.appcomponents.architecturecomponents.architecturecomponents.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by sachin on 19/11/17.
 */

@Entity
public class Book {
    @PrimaryKey
    @NonNull
    public String id;

    public String title;

    public String user;

    public String year;
}
