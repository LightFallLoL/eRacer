package org.milaifontanals.projecte.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.text.SimpleDateFormat;
import java.util.List;
import org.milaifontanals.projecte.Model.Cursa;
import org.milaifontanals.projecte.R;

public class CursaAdapter extends RecyclerView.Adapter<CursaAdapter.CursaViewHolder> {

    private Fragment context;
    private List<Cursa> cursaList;
    private SimpleDateFormat dateFormat;
    private int index = -1;

    public List<Cursa> getCursaList() {
        return cursaList;
    }

    public void setCursaList(List<Cursa> cursaList) {

        if (cursaList != null) {
            this.cursaList = cursaList;
            notifyDataSetChanged();
        } else {
            Log.e("CursaAdapter", "Cursa list is null");
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    ImageLoader imageLoader;

    Gson gson = new Gson();
    public CursaAdapter(Fragment context, List<Cursa> cursaList) {
        this.context = context;
        this.cursaList = cursaList;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Ajusta el formato según sea necesario
        this.imageLoader = ImageLoader.getInstance();
    }

    @NonNull
    @Override
    public CursaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cursa_item, parent, false);
        return new CursaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursaViewHolder holder, int position) {
        Cursa cursaActual = cursaList.get(position);
        holder.title.setText(cursaActual.getNom());
        holder.date.setText(dateFormat.format(cursaActual.getDataInici())); // Formatear la fecha
        holder.location.setText(cursaActual.getLloc());

        /// Aquí puedes agregar lógica para cargar imágenes si no son bitmaps
        if (cursaActual.getBitmap() != null) {
            holder.imageView.setImageBitmap(cursaActual.getBitmap());
        } else {
            holder.imageView.setImageResource(R.drawable.ic_launcher_background);
            imageLoader.loadImage(cursaActual.getUrlFoto(), new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    cursaActual.setBitmap(loadedImage);
                    holder.imageView.setImageBitmap(loadedImage);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
        }
        holder.clyItem.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(context);
            Bundle bundle = new Bundle();
            bundle.putSerializable("cursa", cursaActual);
            navController.navigate(R.id.action_cursesFragment_to_cursaDetailFragment, bundle);
        });

    }

    @Override
    public int getItemCount() {
        return cursaList.size();
    }

    public static class CursaViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, location;
        ImageView imageView;
        ConstraintLayout clyItem;

        public CursaViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txvTitle);
            date = itemView.findViewById(R.id.txvDate);
            location = itemView.findViewById(R.id.txvLocation);
            imageView = itemView.findViewById(R.id.imvCursa);
            clyItem = itemView.findViewById(R.id.clyItem);
        }
    }
}
