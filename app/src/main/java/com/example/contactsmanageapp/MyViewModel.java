package com.example.contactsmanageapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private Repository repository;


    private LiveData<List<Contacts>> allContacts;


    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = repository.getAllContacts();
        return allContacts;
    }

    public void addNewContact(Contacts contacts){
        repository.addContact(contacts);
    }
    public void deleteContact(Contacts contacts){
        repository.deleteContact(contacts);
    }
}
