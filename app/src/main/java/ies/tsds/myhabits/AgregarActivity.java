package ies.tsds.myhabits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AgregarActivity extends AppCompatActivity {

    private EditText editarTarea, editarDias;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        editarTarea = findViewById(R.id.editar_tarea);
        editarDias = findViewById(R.id.cantidad_dias);

        btn_add = findViewById(R.id.btAgregar);

        
    }
}
