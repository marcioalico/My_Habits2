package ies.tsds.myhabits;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ies.tsds.myhabits.modelo.ToDo;

public class AgregarActivity extends AppCompatActivity {

    private EditText editarTarea, editarDias;
    private Button btn_add;

    FirebaseDatabase database;
    DatabaseReference todoDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // se agrega boton para volver con ayuda del manifest
        editarTarea = findViewById(R.id.editar_tarea);
        editarDias = findViewById(R.id.cantidad_dias);

        btn_add = findViewById(R.id.btAgregar);

        database = FirebaseDatabase.getInstance();
        todoDb = database.getReference("ToDo");

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                guardarEnFirebase();
                cambiarVista();


            }
        });
        
    }

    private void cambiarVista() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void guardarEnFirebase() {

        String tarea = editarTarea.getText().toString();
        String dias = editarDias.getText().toString();

        if(!TextUtils.isEmpty(tarea) && !TextUtils.isEmpty(dias)){

            ToDo toDo = new ToDo(tarea, dias);

            todoDb.push().setValue(toDo).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    Toast.makeText(AgregarActivity.this, "Tarea Agregada", Toast.LENGTH_SHORT).show();

                    editarTarea.setText("");
                    editarDias.setText("");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(AgregarActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });



        }else{

            Toast.makeText(this, "Todos los campos tienen que ser completados", Toast.LENGTH_SHORT).show();
        }
    }
}
