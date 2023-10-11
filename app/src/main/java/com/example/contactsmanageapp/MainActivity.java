package com.example.contactsmanageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.contactsmanageapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Data Source
    private ContactDataBase contactDataBase;
    private ArrayList<Contacts> contacts = new ArrayList<>();

    //Adapter
    private MyAdapter adapter;

    //Binding

    private ActivityMainBinding mainBinding;
    private MainActivityClickHandlers handlers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link entre main activity click avec main activity binding
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        handlers = new MainActivityClickHandlers(this);
        mainBinding.setClickHandler(handlers);

        //using recycle view
        RecyclerView recyclerView = mainBinding.recycleview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //adapter
        adapter = new MyAdapter(contacts);

        //Databse
        contactDataBase = ContactDataBase.getInstance(this);

        //ViewModel
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //Insering a new contact
        Contacts contacts1 = new Contacts("Yahya","yahya@gmail.com");
        myViewModel.addNewContact(contacts1);

        myViewModel.getAllContacts().observe(this,
                new Observer<List<Contacts>>() {
                    @Override
                    public void onChanged(List<Contacts> contacts) {
                        for (Contacts c: contacts){
                            Log.v("TAGY",c.getName());
                        }
                    }
                });
        recyclerView.setAdapter(adapter);
    }
}