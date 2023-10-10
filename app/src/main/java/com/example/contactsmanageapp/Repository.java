package com.example.contactsmanageapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private final ContactDAO contactDAO;
    ExecutorService executor;

    Handler handler;
    public Repository(Application application) {
        ContactDataBase contactDataBase = ContactDataBase.getInstance(application);
        this.contactDAO = contactDataBase.getContactDAO();
         executor = Executors.newSingleThreadExecutor();
         handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contacts) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contacts);
            }
        });
    }

    public void deleteContact(Contacts contacts) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contacts);
            }
        });

    }

    LiveData<List<Contacts>> getAllContacts() {
        return contactDAO.getAllContacts();
    }
}
