package com.example.onpvd;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnExitTG, btnThemTG, btnSuaTG, btnXoaTG, btnXemTG;
    EditText etMaTG, etTenTG, etDiachi, etEmail;
    GridView gvTG;

    Button btnExitS, btnThemS, btnSuaS, btnXoaS, btnXemS;
    EditText etMaS, etTuaS, etMaTGS;
    GridView gvS;

    MyContentProvider myContentProvider = new MyContentProvider();

    public void anhXaS(Dialog dlS) {
        btnExitS = dlS.findViewById(R.id.btnExitS);
        btnThemS = dlS.findViewById(R.id.btnThemS);
        btnSuaS = dlS.findViewById(R.id.btnSuaS);
        btnXoaS = dlS.findViewById(R.id.btnXoaS);
        btnXemS = dlS.findViewById(R.id.btnXemS);

        etMaS = dlS.findViewById(R.id.etMaS);
        etTuaS = dlS.findViewById(R.id.etTuaS);
        etMaTGS = dlS.findViewById(R.id.etMaTGS);

        gvS = dlS.findViewById(R.id.gvS);
    }

    public void anhXaTG(Dialog dlTG) {
        btnExitTG = dlTG.findViewById(R.id.btnExitTG);
        btnThemTG = dlTG.findViewById(R.id.btnThemTG);
        btnSuaTG = dlTG.findViewById(R.id.btnSuaTG);
        btnXoaTG = dlTG.findViewById(R.id.btnXoaTG);
        btnXemTG = dlTG.findViewById(R.id.btnXemTG);

        etMaTG = dlTG.findViewById(R.id.etMaTG);
        etTenTG = dlTG.findViewById(R.id.etTenTG);
        etDiachi = dlTG.findViewById(R.id.etDiachi);
        etTenTG = dlTG.findViewById(R.id.etTenTG);
        etEmail = dlTG.findViewById(R.id.etEmail);

        gvTG = dlTG.findViewById(R.id.gvTG);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnQuanlysach:
                quanLySach();
                return true;
            case R.id.mnQuanlytacgia:
                quanLyTacGia();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void quanLySach() {
        final Dialog dlS = new Dialog(MainActivity.this);
        dlS.setContentView(R.layout.sach_layout);
        dlS.setTitle("Quan ly sach");

        anhXaS(dlS);
        btnExitS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlS.dismiss();
            }
        });
        btnXemS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list_string = new ArrayList<>();
                Cursor cursor = getContentResolver().query(myContentProvider.uri, null, null, null, "tuasach");
                if (cursor != null) {
                    cursor.moveToFirst();
                    do {
                        list_string.add(cursor.getInt(0) + "");
                        list_string.add(cursor.getString(1) + "");
                        list_string.add(cursor.getInt(2) + "");
                    } while (cursor.moveToNext());
                } else
                    Toast.makeText(getApplicationContext(), "Khong co", Toast.LENGTH_SHORT).show();

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list_string);
                gvS.setAdapter(adapter);
            }
        });
        btnThemS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("masach", Integer.parseInt(etMaS.getText().toString()));
                values.put("tuasach", etTuaS.getText().toString());
                values.put("matacgia", Integer.parseInt(etMaTGS.getText().toString()));
                Uri insert_uri = getContentResolver().insert(myContentProvider.uri, values);
                Toast.makeText(getApplicationContext(), "Luu thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        btnSuaS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("masach", Integer.parseInt(etMaS.getText().toString()));
                values.put("tuasach", etTuaS.getText().toString());
                values.put("matacgia", Integer.parseInt(etMaTGS.getText().toString()));
                if (getContentResolver().update(myContentProvider.uri, values,
                        "masach=?",
                        new String[]{etMaS.getText().toString()}) > 0) ;
                Toast.makeText(getBaseContext(), "Sua thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        btnXoaS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContentResolver().delete(myContentProvider.uri,
                        "masach=?",
                        new String[]{etMaS.getText().toString()}) > 0) ;
                Toast.makeText(getBaseContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

        dlS.show();
        Window window = dlS.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public void quanLyTacGia() {
        final Dialog dlTG = new Dialog(MainActivity.this);
        dlTG.setContentView(R.layout.tacgia_layout);
        dlTG.setTitle("Quan ly tac gia");

        anhXaTG(dlTG);
        btnExitTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlTG.dismiss();
            }
        });
        btnXemTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list_string = new ArrayList<>();
                Cursor cursor = getContentResolver().query(myContentProvider.uri, null, null, null, "tentacgia");
                if (cursor != null) {
                    cursor.moveToFirst();
                    do {
                        list_string.add(cursor.getInt(0) + "");
                        list_string.add(cursor.getString(1) + "");
                        list_string.add(cursor.getString(2) + "");
                        list_string.add(cursor.getString(3) + "");
                    } while (cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list_string);
                    gvTG.setAdapter(adapter);
                } else
                    Toast.makeText(getApplicationContext(), "Khong co", Toast.LENGTH_SHORT).show();
            }
        });
        btnThemTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("matacgia", Integer.parseInt(etMaTG.getText().toString()));
                values.put("tentacgia", etTenTG.getText().toString());
                values.put("diachi", etDiachi.getText().toString());
                values.put("email", etEmail.getText().toString());
                Uri insert_uri = getContentResolver().insert(myContentProvider.uri, values);
                Toast.makeText(getApplicationContext(), "Luu thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        btnSuaTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("matacgia", Integer.parseInt(etMaTG.getText().toString()));
                values.put("tentacgia", etTenTG.getText().toString());
                values.put("diachi", etDiachi.getText().toString());
                values.put("email", etEmail.getText().toString());
                if (getContentResolver().update(myContentProvider.uri, values,
                        "matacgia=?",
                        new String[]{etMaTG.getText().toString()}) > 0) ;
                Toast.makeText(getBaseContext(), "Sua thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        btnXoaTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContentResolver().delete(myContentProvider.uri,
                        "matacgia=?",
                        new String[]{etMaTG.getText().toString()}) > 0) ;
                Toast.makeText(getBaseContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

        dlTG.show();
        Window window = dlTG.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

}
