package com.example.vedroids2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;


public class main extends Activity {
    SharedPreferences sharedPref;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloact);

        EditText loginField = findViewById(R.id.editTextName);
        EditText passwordField = findViewById(R.id.editTextTextPassword);

        TextView attentionText = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> userList = db.getAllUsers();
                User currentUser;
                boolean access = false;
                for(int i = 0; i < userList.size(); i++) {
                    currentUser = userList.get(i);
                    if (loginField.getText().toString().equals(currentUser.getLogin()) &&
                            passwordField.getText().toString().equals(currentUser.getPass())) {
                        Intent intent = new Intent(main.this, MyList.class);
                        intent.putExtra("account", currentUser.getID());
                        finish();
                        main.this.startActivity(intent);
                        access = true;
                    }
                }
                if(!access)
                    attentionText.setText("Неверный логин или пароль");
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("AppLogger", "Сработал старт");
        Toast mytoast = new Toast(this);
        mytoast.makeText(main.this, "Сработал старт", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("AppLogger", "Сработало возобновление");
        Toast mytoast = new Toast(this);
        mytoast.makeText(main.this, "Сработало возобновление", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("AppLogger", "Сработала пауза");
        Toast mytoast = new Toast(this);
        mytoast.makeText(main.this, "Сработала пауза", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("AppLogger", "Работа прекращена");
        Toast mytoast = new Toast(this);
        mytoast.makeText(main.this, "Работа прекращена", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("AppLogger", "Приложение закрыто");
        Toast mytoast = new Toast(this);
        mytoast.makeText(main.this, "Приложение закрыто", Toast.LENGTH_SHORT).show();
    }

}
