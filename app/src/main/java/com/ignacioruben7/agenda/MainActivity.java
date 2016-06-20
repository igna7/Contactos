package com.ignacioruben7.agenda;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button siguiente;
    EditText textName;
    EditText textPhone;
    EditText textEmail;
    EditText textDesc;
    DatePicker datePicker;
    private Calendar cal;
    private int año;
    private int mes;
    private int dia;
    private EditText textFecha;
    private static final int TIPO_DIALOGO = 0;
    private DatePickerDialog.OnDateSetListener oyenteSelectorFecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textFecha = (EditText) findViewById(R.id.textFecha);
        loadSavedPreferences4();

        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);


        oyenteSelectorFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                año = i;
                mes = i1;
                dia = i2;
                mostrarFecha();
            }
        };



        textName = (EditText) findViewById(R.id.textName);
        loadSavedPreferences();
        textPhone = (EditText) findViewById(R.id.textPhone);
        loadSavedPreferences1();
        textEmail = (EditText) findViewById(R.id.textEmail);
        loadSavedPreferences2();
        textDesc = (EditText) findViewById(R.id.textDesc);
        loadSavedPreferences3();

        siguiente = (Button) findViewById(R.id.btnNext);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();

                textName = (EditText) findViewById(R.id.textName);
                textPhone = (EditText) findViewById(R.id.textPhone);
                textEmail = (EditText) findViewById(R.id.textEmail);
                textDesc = (EditText) findViewById(R.id.textDesc);

                Intent intent = new Intent(MainActivity.this,DetalleContacto.class);
                intent.putExtra("Nombre", textName.getText().toString());
                intent.putExtra("Telefono", textPhone.getText().toString());
                intent.putExtra("Email", textEmail.getText().toString());
                intent.putExtra("Descripcion", textDesc.getText().toString());
                intent.putExtra("Fecha",textFecha.getText().toString());
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:
                return new DatePickerDialog(this,oyenteSelectorFecha,dia,mes,año );

        }
        return null;
    }

    public void mostrarCalendario(View control){
        showDialog(TIPO_DIALOGO);
    }

    public void mostrarFecha(){
        textFecha.setText(dia+"/"+(mes+1)+"/"+año);

    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        textName.setText(sharedPreferences.getString("string_et1",""));

    }
    private void loadSavedPreferences1() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        textPhone.setText(sharedPreferences.getString("string_et2",""));

    }
    private void loadSavedPreferences2() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        textEmail.setText(sharedPreferences.getString("string_et3",""));

    }
    private void loadSavedPreferences3() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        textDesc.setText(sharedPreferences.getString("string_et4",""));

    }
    private void loadSavedPreferences4() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        textFecha.setText(sharedPreferences.getString("string_et5",""));

    }
    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public void saveData(){
        savePreferences("string_et1", textName.getText().toString());
        savePreferences("string_et2", textPhone.getText().toString());
        savePreferences("string_et3", textEmail.getText().toString());
        savePreferences("string_et4", textDesc.getText().toString());
        savePreferences("string_et5", textFecha.getText().toString());
    }

}
