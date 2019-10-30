package ies.tsds.myhabits;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ies.tsds.myhabits.modelo.ToDo;
import ies.tsds.myhabits.vista.VistaToDo;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    FirebaseDatabase database;
    DatabaseReference todoDb;

    FirebaseRecyclerOptions<ToDo> options;
    FirebaseRecyclerAdapter<ToDo, VistaToDo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton2);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
                startActivity(intent);
            }
        });

        database = FirebaseDatabase.getInstance();
        todoDb = database.getReference("ToDo");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mostrarTareas();
    }

    private void mostrarTareas() {

        options = new FirebaseRecyclerOptions.Builder<ToDo>().setQuery(todoDb, ToDo.class).build();

        adapter = new FirebaseRecyclerAdapter<ToDo, VistaToDo>(options) {
            @Override
            protected void onBindViewHolder(@NonNull VistaToDo holder, int position, @NonNull ToDo model) {

                holder.texto_tarea.setText(model.getTarea());
                holder.texto_dias.setText(model.getDias());

            }

            @NonNull
            @Override
            public VistaToDo onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_tarea, viewGroup, false);
                return new VistaToDo(itemView);
            }
        };

        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().equals("Actualizar")) {
            mostrarMensajeActz(adapter.getRef(item.getOrder()).getKey(), adapter.getItem(item.getOrder()));// Metodo para mostrar mensaje de actualizacion de tarea
        }else if (item.getTitle().equals("Eliminar")){
            eliminarTarea(adapter.getRef(item.getOrder()).getKey());

        }
        return super.onContextItemSelected(item);
    }

    private void eliminarTarea(String key) {


    }

    private void mostrarMensajeActz(final String key, ToDo item) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Actualizar");
        builder.setMessage("Actualiza los campos");

        View actualizar_layout =  LayoutInflater.from(this).inflate(R.layout.activity_custom, null);

        final EditText edt_actz_tarea = actualizar_layout.findViewById(R.id.editar_actz_tarea);
        final EditText edt_actz_dias = actualizar_layout.findViewById(R.id.editar_actz_dias);


        edt_actz_tarea.setText(item.getTarea());
        edt_actz_dias.setText(item.getDias());

        builder.setView(actualizar_layout);
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tarea = edt_actz_tarea.getText().toString();
                String dias = edt_actz_dias.getText().toString();

                ToDo toDo = new ToDo(tarea, dias);
                todoDb.child(key).setValue(toDo);

                Toast.makeText(MainActivity.this, "La Tarea  fue Actualizada", Toast.LENGTH_SHORT).show();
          }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
