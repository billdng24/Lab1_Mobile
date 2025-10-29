package com.example.ngay29thang10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddContactActivity extends AppCompatActivity {
    private EditText edtName, edtPhone, edtEmail;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên và số điện thoại", Toast.LENGTH_SHORT).show();
                return;
            }

            List<Contact> contacts = Utils.getContacts(this);
            contacts.add(new Contact(name, phone, email));
            Utils.saveContacts(this, contacts);

            Toast.makeText(this, "Đã lưu danh bạ!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
