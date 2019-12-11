package com.xpomanager.adapters;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
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
    private ImageView linkedImageView;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public AdaptadorObjects(List<?> datos, ControladorPrincipal controladorPrincipal, ImageView linkedImageView) {
        this.datos = datos;
        this.controladorPrincipal = controladorPrincipal;
        this.linkedImageView = linkedImageView;
    }

    @Override
    public ObjectsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item, viewGroup, false);

        return new ObjectsViewHolder(itemView, controladorPrincipal, linkedImageView);

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

        private ControladorPrincipal controladorPrincipal;
        private ImageView imageViewRecyclerView;
        private ImageView linkedImageView;

        private ObjectsViewHolder(View itemView, ControladorPrincipal controladorPrincipal, ImageView linkedImageView) {
            super(itemView);

            this.controladorPrincipal = controladorPrincipal;
            this.imageViewRecyclerView = itemView.findViewById(R.id.ImageViewRecyclerView);
            this.linkedImageView = linkedImageView;

        }

        private void bindObject(Object object) {
            Bitmap bitmap = null;
            Boolean selected = false;

            if (object instanceof Personaje) {
                bitmap = controladorPrincipal.getPersonajeImageBitmap((Personaje) object);
            } else if (object instanceof Idioma) {
                bitmap = controladorPrincipal.getIdiomaImageBitmap((Idioma) object);
            }

            Object linkedObject = linkedImageView.getTag();

            if (linkedObject.equals(object)) {
                imageViewRecyclerView.setPadding(12,12,12,12);
                imageViewRecyclerView.setBackgroundColor(Color.parseColor("#80ff0000"));
            }

            if (bitmap != null) {
                imageViewRecyclerView.setImageBitmap(bitmap);
            }

            imageViewRecyclerView.setTag(object);

        }

    }

}
