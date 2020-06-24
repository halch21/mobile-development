package com.example.contacts_laba4;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class DB extends RoomDatabase {
    public abstract API api();
}
