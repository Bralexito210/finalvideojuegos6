package com.example.asus.finalvideojuegos.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.asus.finalvideojuegos.R;
import com.example.asus.finalvideojuegos.entidades.CarritoDeCompras;
import com.example.asus.finalvideojuegos.entidades.VolleySingleton;

import java.util.ArrayList;

public class CarritoDeComprasAdapter extends RecyclerView.Adapter<CarritoDeComprasAdapter.ViewHolderDatos> {

    ArrayList<CarritoDeCompras> listaCarritoCompras;
    Context context;

    public CarritoDeComprasAdapter(ArrayList<CarritoDeCompras> listaCarritoCompras, Context context) {
        this.listaCarritoCompras = listaCarritoCompras;
        this.context=context;
    }

    @Override
    public CarritoDeComprasAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //aca le vinculamso el layout lista
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listacarritodecompras,null,false);


        return new CarritoDeComprasAdapter.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoDeComprasAdapter.ViewHolderDatos viewHolderDatos, int i) {
        ImageView imagen;
        viewHolderDatos.marca.setText(listaCarritoCompras.get(i).getMarca().toString());
        viewHolderDatos.precio.setText(listaCarritoCompras.get(i).getPrecio().toString());
        viewHolderDatos.peso.setText(listaCarritoCompras.get(i).getPeso().toString());
        viewHolderDatos.cantidad.setText(listaCarritoCompras.get(i).getCantidad().toString());

        if (listaCarritoCompras.get(i).getRutaImagen()!=null){
            // holder.imagen.setImageBitmap(listaUsuarios.get(position).getImagen());
            cargarImagenWebService(listaCarritoCompras.get(i).getRutaImagen(),viewHolderDatos);
        }else {
            viewHolderDatos.imagen.setImageResource(R.drawable.sinimagen);


        }


    }

    private void cargarImagenWebService(String rutaImagen, final CarritoDeComprasAdapter.ViewHolderDatos holder) {
        String ip =context.getString(R.string.ip);
        String urlImagen=ip+"/BD_APP/"+rutaImagen;
        urlImagen=urlImagen.replace(" ","%20");

        ImageRequest imageRequest=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                holder.imagen.setImageBitmap(response);

            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No se ha podido establecer conexión con el servidor" , Toast.LENGTH_LONG).show();

            }
        });
        //request.add(imageRequest);
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(imageRequest);
    }

    @Override
    public int getItemCount() {
        return listaCarritoCompras.size();
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView marca,precio,peso,cantidad;
        ImageView imagen;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            marca = (TextView) itemView.findViewById(R.id.idMarca);
            precio = (TextView) itemView.findViewById(R.id.idPrecio);
            peso = (TextView) itemView.findViewById(R.id.idPeso);
            cantidad = (TextView) itemView.findViewById(R.id.idCantidad);

            imagen=(ImageView) itemView.findViewById(R.id.idImagen);

        }
    }
}
