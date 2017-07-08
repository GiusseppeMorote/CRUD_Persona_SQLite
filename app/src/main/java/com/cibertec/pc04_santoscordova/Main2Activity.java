package com.cibertec.pc04_santoscordova;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    private EditText txtNombre, txtApellido, txtEdad, txtTelefono, txtDocumento,txtDireccion;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    int codigo;
    String nombre, apellido, edad, telefono, documento, direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtEdad = (EditText)findViewById(R.id.txtEdad);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);
        txtDocumento = (EditText)findViewById(R.id.txtDocumento);
        txtDireccion = (EditText)findViewById(R.id.txtDireccion);

        btnRegistrar = (Button)findViewById(R.id.btnGuardar);
        btnActualizar = (Button)findViewById(R.id.btnActualizar);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);

        Bundle b =getIntent().getExtras();
        if (b!=null){
            codigo = b.getInt("ID");
            nombre = b.getString("NOMBRE");
            apellido = b.getString("APELLIDO");
            edad = b.getString("EDAD");
            telefono = b.getString("TELEFONO");
            documento = b.getString("DOCUMENTO");
            direccion = b.getString("DIRECCION");

        }

        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        txtEdad.setText(edad);
        txtTelefono.setText(telefono);
        txtDocumento.setText(documento);
        txtDireccion.setText(direccion);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persona persona = new Persona();
                persona.setNombre(txtNombre.getText().toString().trim());
                persona.setApellido(txtApellido.getText().toString().trim());
                persona.setEdad(txtEdad.getText().toString().trim());
                persona.setTelefono(txtTelefono.getText().toString().trim());
                persona.setDocumento(txtDocumento.getText().toString().trim());
                persona.setDireccion(txtDireccion.getText().toString().trim());
                Registrar(persona);
                onBackPressed();

            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Persona persona = new Persona();
                Elimnar(persona);
                onBackPressed();
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persona persona = new Persona();
                persona.setNombre(txtNombre.getText().toString().trim());
                persona.setApellido(txtApellido.getText().toString().trim());
                persona.setEdad(txtEdad.getText().toString().trim());
                persona.setTelefono(txtTelefono.getText().toString().trim());
                persona.setDocumento(txtDocumento.getText().toString().trim());
                persona.setDireccion(txtDireccion.getText().toString().trim());
                Actualizar(persona);
                onBackPressed();
            }
        });
    }

    private void Registrar(Persona persona){
        DataBaseHelper helper = new DataBaseHelper(Main2Activity.this,"db_examen",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("NOMBRE", persona.getNombre());
            contentValues.put("APELLIDO", persona.getApellido());
            contentValues.put("EDAD", persona.getEdad());
            contentValues.put("TELEFONO", persona.getTelefono());
            contentValues.put("DOCUMENTO", persona.getDocumento());
            contentValues.put("DIRECCION", persona.getDireccion());
             db.insert("PERSONA", null, contentValues);
             db.close();
        }catch (Exception e){
           e.printStackTrace();
        }
    }
    private void Actualizar(Persona persona){
        DataBaseHelper helper = new DataBaseHelper(Main2Activity.this, "db_examnen", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("NOMBRE", persona.getNombre());
            contentValues.put("APELLIDO", persona.getApellido());
            contentValues.put("EDAD", persona.getEdad());
            contentValues.put("TELEFONO", persona.getTelefono());
            contentValues.put("DOCUMENTO", persona.getDocumento());
            contentValues.put("DIRECCION", persona.getDireccion());
            db.update("PERSONA",contentValues, "ID=" + persona.getId(), null);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void Elimnar(Persona persona){
        DataBaseHelper helper = new DataBaseHelper(Main2Activity.this, "db_examnen", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {


            db.delete("PERSONA","ID=" + persona.getId(), null);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
