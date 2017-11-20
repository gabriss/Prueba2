package com.example.gabri.prueba2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;


public class Adaptador extends ArrayAdapter<Bares> {

    public Adaptador(Context context, List<Bares> datos) {
        super(context, R.layout.celda, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Bares bar = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.celda, null);
        }
        TextView lblTitulo = (TextView) convertView.findViewById(R.id.enunciado);
        lblTitulo.setText(bar.getNombre());
        TextView lblSubtitulo = (TextView) convertView.findViewById(R.id.texto);
        lblSubtitulo.setText(bar.getDireccion());
        ImageView fotico = (ImageView) convertView.findViewById(R.id.foto);
        new DownloadImageTask(fotico)
                .execute(bar.getImagen());

        return (convertView);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
