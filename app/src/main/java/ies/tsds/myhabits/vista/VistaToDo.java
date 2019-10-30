package ies.tsds.myhabits.vista;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ies.tsds.myhabits.R;

public class VistaToDo extends RecyclerView.ViewHolder {

    public TextView texto_tarea, texto_dias;

    public VistaToDo(@NonNull View itemView) {
        super(itemView);

        texto_tarea = itemView.findViewById(R.id.texto_tarea);
        texto_dias = itemView.findViewById(R.id.texto_dias);
    }
}
