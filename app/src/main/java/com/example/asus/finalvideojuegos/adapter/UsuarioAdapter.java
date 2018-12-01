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
import com.example.asus.finalvideojuegos.entidades.Producto;
import com.example.asus.finalvideojuegos.entidades.Usuario;
import com.example.asus.finalvideojuegos.entidades.VolleySingleton;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuariosHolder> {
    List<Usuario> listUsuario;
    // RequestQueue request;
    Context context;



    public UsuarioAdapter(List<Usuario> listUsuario, Context context) {
        this.listUsuario = listUsuario;
        this.context=context;
        //request=Volley.newRequestQueue(context);
    }

    @Override
    public UsuariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.listausuarios,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(UsuariosHolder holder, int position) {


        holder.txtNick.setText(listUsuario.get(position).getNick().toString());
        holder.txtNombre.setText(listUsuario.get(position).getNombre().toString());
        holder.txtCelular.setText(listUsuario.get(position).getCelular().toString());
        holder.txtCargo.setText(listUsuario.get(position).getCargo().toString());
        holder.txtApellido.setText(listUsuario.get(position).getApellidos().toString());



    }


    @Override
    public int getItemCount() {
        return listUsuario.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder{

        TextView txtNick,txtNombre,txtCelular,txtCargo,txtApellido;

        public UsuariosHolder(View itemView) {
            super(itemView);
            txtNick = (TextView) itemView.findViewById(R.id.idNick);
            txtNombre = (TextView) itemView.findViewById(R.id.idNombre);
            txtCelular = (TextView) itemView.findViewById(R.id.idCelular);
            txtCargo = (TextView) itemView.findViewById(R.id.idCargo);
            txtApellido = (TextView) itemView.findViewById(R.id.idApellido);

        }
    }

}
