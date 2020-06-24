package com.example.contacts_laba4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface API {

    @Insert
    void insert(Contact contact);

    @Query("SELECT * FROM Contacts")
    List<Contact> readAll();

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM Contacts")
    void clear();


    @Query("SELECT COUNT(id) FROM Contacts")
    int count();

    @Query("SELECT * FROM Contacts WHERE id = :id")
    Contact readContact(int id);


}
