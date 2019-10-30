package ies.tsds.myhabits.vista;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import ies.tsds.myhabits.R;

public class VistaToDo extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

    public TextView texto_tarea, texto_dias;

    public VistaToDo(@NonNull View itemView) {
        super(itemView);

        texto_tarea = itemView.findViewById(R.id.texto_tarea);
        texto_dias = itemView.findViewById(R.id.texto_dias);
        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Seleccionar Opcion");
        menu.add(0,0, getAdapterPosition(), "Actualizar");
        menu.add(0,1, getAdapterPosition(), "Eliminar");
    }
}
