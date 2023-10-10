package com.example.vedroids2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyList extends Activity {

    ArrayList<String> myStringArray;
    DatabaseHandler db = new DatabaseHandler(this);
    User currentUser = new User();
    int index = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        //Создание списка
        myStringArray = new ArrayList<String>();
        List<User> userList  = db.getAllUsers();
        for(int i = 0; i < userList.size(); i++)
        {
            myStringArray.add(userList.get(i)._login + "\t" + userList.get(i)._pass);
        }

        //Вывод имени
        Bundle arguments = getIntent().getExtras();

        //Определяем текущего пользователя
        int userId = Integer.parseInt(arguments.get("account").toString());
        for(int i = 0; i < userList.size(); i++)
        {
            if (userList.get(i).getID() == userId)
            {
                currentUser = userList.get(i);
                index = i;
            }
        }

        //Создание динамического списка
        ArrayAdapter<String> TextAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myStringArray);
        ListView textList = findViewById(R.id.textList);
        textList.setAdapter(TextAdapter);

        //Обновление списка
        TextAdapter.notifyDataSetChanged();

        //Диалоговое окно
        AlertDialog.Builder builder = new AlertDialog.Builder(MyList.this);
        final EditText et = new EditText(MyList.this);
        et.setHint("Пароль");
        builder.setView(et).setTitle("Укажите новый пароль пользователя").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                db.updateUser(currentUser, et.getText().toString());
                currentUser.setPass(et.getText().toString());
                myStringArray.remove(index);
                myStringArray.add(index, currentUser.getLogin() + "\t" + currentUser.getPass());
                TextAdapter.notifyDataSetChanged();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();

        //Функционал кнопка "добавить"
        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText dataLog = findViewById(R.id.editLogin);
                EditText dataPass = findViewById(R.id.editPassword);
                userList.add(new User(dataLog.getText().toString(), dataPass.getText().toString()));
                db.addUser(new User(dataLog.getText().toString(), dataPass.getText().toString()));
                myStringArray.add(dataLog.getText().toString() + "\t" + dataPass.getText().toString());
                TextAdapter.notifyDataSetChanged();
            }
        });

        //Функционал кнопки "удалить"
        Button buttonDel = findViewById(R.id.buttonDel);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText dataLog = findViewById(R.id.editLogin);
                for(int i = 0; i < userList.size(); i++)
                {
                    if(dataLog.getText().toString().equals(userList.get(i)._login)){
                        db.deleteUser(userList.get(i));
                        myStringArray.remove(i);
                        userList.remove(i);
                        i--;
                        TextAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        Button buttonEdit = findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show it
                alertDialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(MyList.this, main.class);

        finish();
        MyList.this.startActivity(intent);
    }
}
