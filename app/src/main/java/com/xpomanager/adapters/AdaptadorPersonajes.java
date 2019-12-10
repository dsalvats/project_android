package com.xpomanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorPrincipal;
import com.xpomanager.models.Personaje;

import java.util.List;

public class AdaptadorPersonajes extends RecyclerView.Adapter<AdaptadorPersonajes.PersonajesViewHolder> {

    /*************
     * ATRIBUTOS *
     *************/
    private List<Personaje> datos;
    private ControladorPrincipal controladorPrincipal;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public AdaptadorPersonajes(List<Personaje> datos, ControladorPrincipal controladorPrincipal) {
        this.datos = datos;
        this.controladorPrincipal = controladorPrincipal;
    }

    @Override
    public PersonajesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item, viewGroup, false);

        return new PersonajesViewHolder(itemView, controladorPrincipal);

    }

    @Override
    public void onBindViewHolder(PersonajesViewHolder viewHolder, int pos) {
        Personaje item = datos.get(pos);

        viewHolder.bindPersonaje(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class PersonajesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewPersonajeRecyclerView;
        private ControladorPrincipal controladorPrincipal;

        public PersonajesViewHolder(View itemView, ControladorPrincipal controladorPrincipal) {
            super(itemView);

            this.controladorPrincipal = controladorPrincipal;
            this.imageViewPersonajeRecyclerView = itemView.findViewById(R.id.ImageViewPersonaRecyclerView);

        }

        public void bindPersonaje(Personaje personaje) {
            imageViewPersonajeRecyclerView.setImageBitmap(controladorPrincipal.getPersonajeImageBitmap(personaje));
        }

    }

}
