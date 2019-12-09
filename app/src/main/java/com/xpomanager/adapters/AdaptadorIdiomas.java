package com.xpomanager.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xpomanager.R;
import com.xpomanager.models.Idioma;

import java.util.List;

public class AdaptadorIdiomas extends RecyclerView.Adapter<AdaptadorIdiomas.IdiomasViewHolder> {

    /*************
     * ATRIBUTOS *
     *************/
    private List<Idioma> datos;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public AdaptadorIdiomas(List<Idioma> datos) {
        this.datos = datos;
    }

    @Override
    public IdiomasViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item, viewGroup, false);

        return new IdiomasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IdiomasViewHolder viewHolder, int pos) {
        Idioma item = datos.get(pos);

        viewHolder.bindIdioma(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class IdiomasViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitulo;
        private TextView textSubtitulo;

        public IdiomasViewHolder(View itemView) {
            super(itemView);

            this.textTitulo = itemView.findViewById(R.id.LblTitulo);
            this.textSubtitulo = itemView.findViewById(R.id.LblSubtitulo);

        }

        public void bindIdioma(Idioma idioma) {
            textTitulo.setText(idioma.getNombre());
            textSubtitulo.setText(idioma.getNombre());
        }

    }

}
