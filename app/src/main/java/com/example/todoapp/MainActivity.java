package com.example.todoapp;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editUsername, editPassword;
    Button buttonLogin;

    // Hardcoded login credentials
    String correctUsername = "admin";
    String correctPassword = "1234";
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please fill all fields!", Toast.LENGTH_SHORT).show();
                } else if (username.equals(correctUsername)
                        && password.equals(correctPassword)) {
                    // Go to To-Do screen
                    Intent intent = new Intent(MainActivity.this,
                            ToDoActivity.class);
                    startActivity(intent);
                    finish(); // close login screen
                } else {
                    Toast.makeText(MainActivity.this,
                            "Wrong username or password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}