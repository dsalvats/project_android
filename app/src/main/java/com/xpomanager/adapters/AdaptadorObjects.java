package com.xpomanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorPrincipal;
import com.xpomanager.models.Idioma;
import com.xpomanager.models.Personaje;

import java.util.List;

public class AdaptadorObjects extends RecyclerView.Adapter<AdaptadorObjects.ObjectsViewHolder> {

    /*************
     * ATRIBUTOS *
     *************/
    private List<?> datos;
    private ControladorPrincipal controladorPrincipal;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public AdaptadorObjects(List<?> datos, ControladorPrincipal controladorPrincipal) {
        this.datos = datos;
        this.controladorPrincipal = controladorPrincipal;
    }

    @Override
    public ObjectsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item, viewGroup, false);

        return new ObjectsViewHolder(itemView, controladorPrincipal);

    }

    @Override
    public void onBindViewHolder(ObjectsViewHolder viewHolder, int pos) {
        Object item = datos.get(pos);

        viewHolder.bindObject(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class ObjectsViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewRecyclerView;
        private ControladorPrincipal controladorPrincipal;

        private ObjectsViewHolder(View itemView, ControladorPrincipal controladorPrincipal) {
            super(itemView);

            this.controladorPrincipal = controladorPrincipal;
            this.imageViewRecyclerView = itemView.findViewById(R.id.ImageViewRecyclerView);

        }

        private void bindObject(Object object) {

            if (object instanceof Personaje) {
                imageViewRecyclerView.setImageBitmap(controladorPrincipal.getPersonajeImageBitmap((Personaje) object));
            } else if (object instanceof Idioma) {
                imageViewRecyclerView.setImageBitmap(controladorPrincipal.getIdiomaImageBitmap((Idioma) object));
            }

        }

    }

}
