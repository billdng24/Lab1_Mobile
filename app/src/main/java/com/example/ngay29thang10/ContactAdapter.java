package com.example.ngay29thang10;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<Contact> contactList;
    private List<Contact> filteredList;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
        this.filteredList = new ArrayList<>(contactList);
    }

    public void filter(String keyword) {
        filteredList.clear();
        if (keyword.isEmpty()) {
            filteredList.addAll(contactList);
        } else {
            for (Contact c : contactList) {
                if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredList.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void updateData(List<Contact> newList) {
        this.contactList = newList;
        filter("");
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        Contact contact = filteredList.get(position);
        holder.txtName.setText(contact.getName());
        holder.txtEmail.setText(contact.getEmail().isEmpty() ? "No description" : contact.getEmail());
        holder.imgAvatar.setImageResource(R.drawable.ic_person);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtEmail;
        ImageView imgAvatar;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
        }
    }
}
