package com.xpomanager.adapters;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.xpomanager.R;
import com.xpomanager.controllers.ControladorPrincipal;
import com.xpomanager.models.Idioma;
import com.xpomanager.models.Nivel;
import com.xpomanager.models.Personaje;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptadorObjects extends RecyclerView.Adapter<AdaptadorObjects.ObjectsViewHolder> {

    /*************
     * ATRIBUTOS *
     *************/
    private List<?> datos;
    private Map<String, ?> extraData;
    private ControladorPrincipal controladorPrincipal;
    private View linkedView;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public AdaptadorObjects(List<?> datos, Map<String, ?> extraData, ControladorPrincipal controladorPrincipal, View linkedView) {
        this(datos, controladorPrincipal, linkedView);
        this.extraData = extraData;
    }

    public AdaptadorObjects(List<?> datos, ControladorPrincipal controladorPrincipal, View linkedView) {
        this.datos = datos;
        this.controladorPrincipal = controladorPrincipal;
        this.linkedView = linkedView;
        this.extraData = new HashMap<>();
    }


    @Override
    public ObjectsViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item, viewGroup, false);

        return new ObjectsViewHolder(itemView, extraData, controladorPrincipal, linkedView);
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
        private TextView textViewRecyclerView;
        private View linkedView;
        private Map<String, ?> extraData;

        private ObjectsViewHolder(View itemView, Map<String, ?> extraData, ControladorPrincipal controladorPrincipal, View linkedView) {
            super(itemView);

            this.controladorPrincipal = controladorPrincipal;
            this.extraData = extraData;
            this.linkedView = linkedView;

            declareElements();
        }

        private void declareElements() {
            imageViewRecyclerView = itemView.findViewById(R.id.ImageViewRecyclerView);
            textViewRecyclerView = itemView.findViewById(R.id.TextViewRecyclerView);
        }

        private void bindObject(final Object object) {
            Bitmap bitmap = null;
            String string = null;
            Boolean selected = false;

            if (object instanceof Personaje) {
                bitmap = controladorPrincipal.getPersonajeImageBitmap((Personaje) object);
            } else if (object instanceof Idioma) {
                bitmap = controladorPrincipal.getIdiomaImageBitmap((Idioma) object);
            } else if (object instanceof Nivel) {
                Nivel nivel = (Nivel) object;
                Idioma idioma = (Idioma) extraData.get("Idioma");

                string = nivel.getTraducciones().get(idioma);
            }

            final Object linkedObject = linkedView.getTag();

            if (linkedObject.equals(object)) {
                imageViewRecyclerView.setPadding(12, 12, 12, 12);
                imageViewRecyclerView.setBackgroundColor(Color.parseColor("#80ff0000"));
            }

            if (bitmap != null) {
                imageViewRecyclerView.setImageBitmap(bitmap);
                imageViewRecyclerView.setVisibility(View.VISIBLE);
                imageViewRecyclerView.setTag(object);
                setViewListeners(imageViewRecyclerView, object);
            } else if (string != null){
                textViewRecyclerView.setText(string);
                textViewRecyclerView.setVisibility(View.VISIBLE);
                textViewRecyclerView.setTag(object);
                setViewListeners(textViewRecyclerView, object);
            }

        }

        private void setViewListeners(final View view, final Object object) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bitmap = null;
                    String string = null;
                    ConstraintLayout constraintLayout = (ConstraintLayout) view
                            .getParent()
                            .getParent()
                            .getParent();

                    constraintLayout.setVisibility(View.INVISIBLE);

                    RecyclerView recyclerView = (RecyclerView) view
                            .getParent()
                            .getParent();

                    for (int i = 0; i < recyclerView.getChildCount(); i++) {
                        View childView = recyclerView.getChildAt(i).findViewById(R.id.ImageViewRecyclerView);
                        childView.setPadding(0, 0, 0, 0);
                        childView.setBackgroundColor(Color.TRANSPARENT);

                        /*
                        if (view instanceof ImageView) {
                            ImageView imageView = (ImageView) viewAux;
                            imageView.setPadding(0, 0, 0, 0);
                            imageView.setBackgroundColor(Color.TRANSPARENT);
                        } else if (view instanceof Text) {
                            TextView textView = (TextView) viewAux;
                            textView.setPadding(0, 0 ,0, 0);
                            textView.setBackgroundColor();
                        }*/
                    }

                    imageViewRecyclerView.setPadding(12, 12, 12, 12);
                    imageViewRecyclerView.setBackgroundColor(Color.parseColor("#80ff0000"));

                    if (object instanceof Personaje) {
                        bitmap = controladorPrincipal.getPersonajeImageBitmap((Personaje) object);
                    } else if (object instanceof Idioma) {
                        bitmap = controladorPrincipal.getIdiomaImageBitmap((Idioma) object);
                    } else if (object instanceof Nivel) {
                        String keyIdioma = "Idioma";
                        if (extraData.containsKey(keyIdioma)) {
                            Idioma idioma = (Idioma) extraData.get(keyIdioma);
                            string = ((Nivel) object).getTraducciones().get(idioma);
                        }
                    }

                    if (linkedView instanceof ImageView) {
                        if (bitmap != null)
                            ((ImageView) linkedView).setImageBitmap(bitmap);
                    } else if (linkedView instanceof TextView) {
                        if (string != null)
                            ((TextView) linkedView).setText(string);
                    }
                }
            });
        }

    }



}
