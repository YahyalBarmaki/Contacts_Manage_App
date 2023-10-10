package com.example.contactsmanageapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class},version = 1)
public abstract class ContactDataBase extends RoomDatabase {

    public abstract ContactDAO getContactDAO();

    private static ContactDataBase dbInstance;

    public static synchronized ContactDataBase getInstance(Context context){
        if(dbInstance == null){

            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDataBase.class,
                    "contacts_table"
            ).fallbackToDestructiveMigration().build();
        }
        return dbInstance;
    }
}
