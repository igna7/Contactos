package com.ignacioruben7.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {
    Button back;
    TextView tvNombre;
    TextView tvTelefono;
    TextView tvEmail;
    TextView tvDescripcion;
    TextView tvFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        tvNombre = (TextView) findViewById(R.id.tvNombre);

        Intent nombre = getIntent();
        String name = nombre.getStringExtra("Nombre");
        tvNombre.setText(name);

        tvTelefono = (TextView) findViewById(R.id.tvTelefono);

        Intent telefono = getIntent();
        String phone = telefono.getStringExtra("Telefono");
        tvTelefono.setText(phone);

        tvEmail = (TextView) findViewById(R.id.tvEmail);

        Intent correo = getIntent();
        String email = correo.getStringExtra("Email");
        tvEmail.setText(email);

        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);

        Intent descripcion = getIntent();
        String description = descripcion.getStringExtra("Descripcion");
        tvDescripcion.setText(description);


        tvFecha = (TextView) findViewById(R.id.tvFecha);
        Intent fecha = getIntent();
        String date = fecha.getStringExtra("Fecha");
        tvFecha.setText(date);


        back = (Button) findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleContacto.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(DetalleContacto.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
