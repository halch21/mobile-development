package com.example.contacts_laba4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    EditText editPhone;

    RecyclerView view;
    ContactAdapter adapter;
    List<Contact> contacts;

    static Handler handler;
    int cursor;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        contacts = new ArrayList<>();
        adapter = new ContactAdapter(this, contacts);
        view = findViewById(R.id.list);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        handler = new Handler(){
            public void handleMessage(@NonNull Message message){
                adapter.list = contacts;
                editName.setText("");
                editPhone.setText("");
                adapter.notifyDataSetChanged();
            }
        };

        Thread dbThread = new Thread(new Runnable() {
            @Override
            public void run() {
                contacts = App.db.api().readAll();
                handler.sendEmptyMessage(0);
            }
        });
        dbThread.start();
    }

    public void onAddClick(View view){
        Thread dbThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Contact contact = new Contact();
                contact.id = App.db.api().count();
                contact.name = editName.getText().toString();
                contact.phone = editPhone.getText().toString();
                App.db.api().insert(contact);
                contacts.add(contact);
                handler.sendEmptyMessage(0);
            }
        });
        dbThread.start();
    }

    public void onReadClick(View view){
        Thread dbThread = new Thread(new Runnable() {
            @Override
            public void run() {
                contacts.clear();
                contacts = App.db.api().readAll();
                handler.sendEmptyMessage(0);
            }
        });
        dbThread.start();
    }

    public void onUpdateClick(View view){
        Thread dbThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Contact contact = App.db.api().readContact(cursor);
                if (contact != null){
                    contact.name = editName.getText().toString();
                    contact.phone = editPhone.getText().toString();
                    App.db.api().update(contact);
                    contacts.set(contact.id, contact);
                } else {
                    contact = new Contact();
                    contact.id = App.db.api().count();
                    contact.name = editName.getText().toString();
                    contact.phone = editPhone.getText().toString();
                    App.db.api().insert(contact);
                    contacts.add(contact);
                }
                handler.sendEmptyMessage(0);
            }
        });
        dbThread.start();
    }


    public void onDeleteClick(View view){
        Thread dbThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Contact contact = App.db.api().readContact(cursor);
                    contact.name = editName.getText().toString();
                    contact.phone = editPhone.getText().toString();
                    App.db.api().delete(contact);
                    contacts.set(contact.id, contact);
                    onReadClick(null);
                handler.sendEmptyMessage(0);
            }
        });
        dbThread.start();
    }


    public void onClearClick(View view){
        Thread dbThread = new Thread(new Runnable() {
            @Override
            public void run() {
                App.db.api().clear();
                contacts.clear();
                handler.sendEmptyMessage(0);
            }
        });
        dbThread.start();
    }
}