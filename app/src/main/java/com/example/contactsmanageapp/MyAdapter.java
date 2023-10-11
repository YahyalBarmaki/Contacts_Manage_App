package com.example.contactsmanageapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanageapp.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Contacts> contactsList;



    public MyAdapter(ArrayList<Contacts> contactsList) {
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ContactListItemBinding contactListItemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.contact_list_item,
                        parent,
                        false
                );
        return new MyViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contacts currentContacts = contactsList.get(position);
        holder.contactListItemBinding.setContact(currentContacts);
    }

    @Override
    public int getItemCount() {
        if (contactsList != null){
            return contactsList.size();
        }else {
            return 0;
        }
    }
    public void setContactsList(ArrayList<Contacts> contactsList) {
        this.contactsList = contactsList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ContactListItemBinding contactListItemBinding;

        public MyViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
