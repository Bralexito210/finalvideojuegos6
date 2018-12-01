package com.example.asus.finalvideojuegos.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.finalvideojuegos.MenuPrincipalActivity;
import com.example.asus.finalvideojuegos.R;
import com.example.asus.finalvideojuegos.adapter.CarritoDeComprasAdapter;
import com.example.asus.finalvideojuegos.entidades.CarritoDeCompras;
import com.example.asus.finalvideojuegos.entidades.Producto;
import com.example.asus.finalvideojuegos.entidades.VolleySingleton;
import com.example.asus.finalvideojuegos.interfaces.IComunicaFragment;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DetalleProductoFragment extends Fragment implements Serializable {

    Button btn_carrito;
    TextView descripcion,marca,precio,stock,peso,fecvenc,cantidad;
    Integer cod_prod;
    ImageView imagen;
    File fileImagen;
    Bitmap bitmap;
    String marcaprueba,marcaprueba2;

    //ESTO ES PARA PASAR LOS DATOS AL CARRITO
    Activity activity;
    IComunicaFragment interfaceComunicaFragments;
    ProgressDialog pDialog;
    StringRequest stringRequest;

    //para pasar al carrito dw compras
    ArrayList<CarritoDeCompras> listacarritodecompras;
    RecyclerView recyclerCarritoCompras;
    Double dpeso,dprecio;
    Integer icantidad;
    Bitmap bimagen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final String ip=getString(R.string.ip);
        View vista= inflater.inflate(R.layout.fragment_detalle_producto, container, false);
        descripcion=(TextView) vista.findViewById(R.id.idDescripcion);
        fecvenc=(TextView) vista.findViewById(R.id.idFecVenc);
        marca=(TextView) vista.findViewById(R.id.idMarca);
        precio=(TextView) vista.findViewById(R.id.idPrecio);
        stock=(TextView) vista.findViewById(R.id.idStock);
        peso=(TextView) vista.findViewById(R.id.idPeso);
        btn_carrito=(Button) vista.findViewById(R.id.btnCarrito);
        cantidad=(TextView) vista.findViewById(R.id.txt_cantidad);




        btn_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegistrarCarritoCompra();
               Toast.makeText(getContext(), cod_prod.toString(), Toast.LENGTH_LONG).show();


            }
        });
        imagen = (ImageView) vista.findViewById(R.id.idImagen);

        Bundle objetoProducto=getArguments();
        Producto producto=null;
        //este es el bundle q lelvara los detalles

        if(objetoProducto !=null ){

            producto= (Producto) objetoProducto.getSerializable("objeto");


            fecvenc.setText(producto.getFec_venc().toString());
            //imagen.setImageResource(producto.getRutaImagen());
            descripcion.setText(producto.getDescripcion().toString());
            marca.setText(producto.getMarca().toString());
            precio.setText(producto.getPrecio().toString());
            stock.setText(producto.getStock().toString());
            peso.setText(producto.getPeso().toString());
            cod_prod=producto.getCod_prod();



            String urlImagen=ip+"/BD_APP/"+producto.getRutaImagen();
            urlImagen=urlImagen.replace(" ","%20");
           // Toast.makeText(getContext(), "url "+urlImagen, Toast.LENGTH_LONG).show();
            cargarWebServiceImagen(urlImagen);

        }
        return vista;
    }

    private void RegistrarCarritoCompra() {

        pDialog=new ProgressDialog(getContext());
        pDialog.setMessage("Cargando...");
        pDialog.show();

        // String ip=getString(R.string.ip);
        String ip=getString(R.string.ip);

        String url=ip+"/BD_APP/wsJSONRegistrarCarritoCompras.php";

        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.hide();

                if (response.trim().equalsIgnoreCase("actualiza")){
                    // etiNombre.setText("");
                    //  txtDocumento.setText("");
                    //   etiProfesion.setText("");
                    Toast.makeText(getContext(),"Se ha añadido con exito",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"No se ha añadido ",Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ",""+response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String ccod_prod=cod_prod.toString();
                String ccantidad=cantidad.getText().toString();
                //String precio=txt_precic.getText().toString();




                Map<String,String> parametros=new HashMap<>();
                parametros.put("cod_prod",ccod_prod);
                parametros.put("cantidad",ccantidad);



                return parametros;
            }
        };
        //request.add(stringRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(stringRequest);

    }


    private void LlenarListaCarritoCompras() {

       /* Double dpeso,dprecio;
        Integer icantidad;*/
       dpeso =Double.parseDouble(String.valueOf(peso));
       dprecio =Double.parseDouble(String.valueOf(precio));
       icantidad =Integer.parseInt(String.valueOf(peso));
        bimagen = ((BitmapDrawable)imagen.getDrawable()).getBitmap();
        listacarritodecompras.add(new CarritoDeCompras(marca.getText().toString(),dpeso,dprecio,icantidad,"dato",bimagen,"asd"));

    }

    private void cargarWebServiceImagen(String urlImagen) {
        urlImagen=urlImagen.replace(" ","%20");

        ImageRequest imageRequest=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                bitmap=response;//SE MODIFICA
                imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error al cargar la imagen",Toast.LENGTH_SHORT).show();
                Log.i("ERROR IMAGEN","Response -> "+error);
                imagen.setImageResource(R.drawable.sinimagen);
            }
        });
        //request.add(imageRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(imageRequest);
    }



}
