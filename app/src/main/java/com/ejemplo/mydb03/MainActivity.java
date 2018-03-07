package com.ejemplo.mydb03;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ejemplo.mydb03.utilidades.Utilidades;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnnew;
    private ImageButton btndel;
    private ImageButton btnupdate;
    private ImageButton btnlist;

    private EditText clave;
    private EditText nombre;
    private EditText sueldo;
    ConexionSQLiteHelper con;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con=new ConexionSQLiteHelper(MainActivity.this);
        init();
        recyclerView=findViewById(R.id.recycler);
        String [][] a = con.consultar("Select * from usuario");
        adapter = new DBAdapter(a);
        layoutManager = new LinearLayoutManager(this);


//        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setAdapter(adapter);
        //recyclerView.getAdapter().notifyDataSetChanged();


        //listardatos();


    }

    private void init() {
        btnnew = findViewById(R.id.imageButton);
        btndel = findViewById(R.id.imageButton2);
        btnupdate = findViewById(R.id.imageButton3);
        btnlist = findViewById(R.id.imageButton4);

        clave=findViewById(R.id.edt1);
        nombre=findViewById(R.id.edt2);
        sueldo=findViewById(R.id.edt3);
        btnnew.setOnClickListener(btnListener);
        btndel.setOnClickListener(btnListener);
        btnupdate.setOnClickListener(btnListener);
        btnlist.setOnClickListener(btnListener);
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.imageButton:
                    Long registros;
                    registros = registrarUsuario();
                    if (registros==-1){
                        Toast.makeText(MainActivity.this,"NOT ADD ROW ",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(MainActivity.this,"ADD ROW "+ registros,Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.imageButton2:
                    Toast.makeText(MainActivity.this,"DEL ROW",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imageButton3:
                    Toast.makeText(MainActivity.this,"UPDATE ROW",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.imageButton4:
                    Toast.makeText(MainActivity.this,"LIST ALL",Toast.LENGTH_SHORT).show();
                    //listardatos();
                    recyclerView.setLayoutManager(layoutManager);

                    //recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);
                    recyclerView.getAdapter().notifyDataSetChanged();
                    break;

            }

        }
    };

  /*  private void listardatos() {

        StringBuffer datos = new StringBuffer();
        Cursor cursor = con.getAll();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            datos.append(cursor.getInt(cursor.getColumnIndex(Utilidades.CLAVE)));
            datos.append(" - ");
            datos.append(cursor.getString(cursor.getColumnIndex(Utilidades.NOMBRE)));
            datos.append(" - ");
            datos.append(cursor.getDouble(cursor.getColumnIndex(Utilidades.SUELDO)));
            datos.append("\n");
        }
        resultado.setText(datos);
    }

    public Long registrarUsuario() {

        return con.insert(clave.getText().toString(), nombre.getText().toString(), sueldo.getText().toString());



    }*/
  private Long registrarUsuario() {
      ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this);
      SQLiteDatabase db=conn.getWritableDatabase();
      ContentValues values=new ContentValues();
      values.put(Utilidades.CLAVE,clave.getText().toString());
      values.put(Utilidades.NOMBRE,nombre.getText().toString());
      values.put(Utilidades.SUELDO,sueldo.getText().toString());

      Long idRsultante=db.insert(Utilidades.TABLA, Utilidades.ID,values);
      Toast.makeText(getApplicationContext(),"Id Registro: "+idRsultante,Toast.LENGTH_SHORT).show();
      return idRsultante;
  }




}
