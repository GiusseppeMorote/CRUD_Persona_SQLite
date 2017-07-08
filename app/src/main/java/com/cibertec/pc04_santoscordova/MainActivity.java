package com.cibertec.pc04_santoscordova;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lstPrincipal;
    private Button btnAgregar;
    private ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        CargarListadoPersona();
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstPrincipal = (ListView)findViewById(R.id.lvLista);
        btnAgregar = (Button)findViewById(R.id.btnAgregar);
        CargarListadoPersona();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        lstPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, listado.get(position), Toast.LENGTH_LONG).show();
                int clave = Integer.parseInt(listado.get(position).split(" ")[0]);
                String nombre = listado.get(position).split(" ")[1];
                String apellido = listado.get(position).split(" ")[2];
                String edad = listado.get(position).split(" ")[3];
                String telefono = listado.get(position).split(" ")[4];
                String documento = listado.get(position).split(" ")[5];
                String direccion = listado.get(position).split(" ")[6];

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("ID", clave);
                intent.putExtra("NOMBRE", nombre);
                intent.putExtra("APELLIDO", apellido);
                intent.putExtra("EDAD", edad);
                intent.putExtra("TELEFONO", telefono);
                intent.putExtra("DOCUMENTO", documento);
                intent.putExtra("DIRECCION", direccion);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CargarListadoPersona(){
       listado = listaPersona();
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,listado);
        lstPrincipal.setAdapter(adapter);
    }
    private ArrayList<String> listaPersona(){
        ArrayList<String> datos = new ArrayList<String>();
        DataBaseHelper helper = new DataBaseHelper(MainActivity.this,"db_examen",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String Sql = "SELECT * FROM PERSONA";
        Cursor c = db.rawQuery(Sql,null);
        if (c.moveToFirst()){
            do{
                String columna = c.getInt(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3) + " " + " " + c.getString(4) + " " + " " + c.getString(5) + " " + c.getString(6);
                datos.add(columna);
             }while (c.moveToNext());
        }
        db.close();
        return datos;
    }
}
