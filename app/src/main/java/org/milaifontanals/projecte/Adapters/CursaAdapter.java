package org.milaifontanals.projecte.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import org.milaifontanals.projecte.Model.Cursa;
import org.milaifontanals.projecte.R;

public class CursaAdapter extends RecyclerView.Adapter<CursaAdapter.CursaViewHolder> {

    private Context context;
    private List<Cursa> cursaList;
    private int index = -2;

    public CursaAdapter(Context context, List<Cursa> cursaList) {
        this.context = context;
        this.cursaList = cursaList;
    }

    @NonNull
    @Override
    public CursaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cursa_item, parent, false);
        return new CursaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursaViewHolder holder, int position) {
        Cursa cursa = cursaList.get(position);
        holder.title.setText(cursa.getNom());
        holder.date.setText(cursa.getDataInici().toString()); // Format the date as needed
        holder.location.setText(cursa.getLloc());
        holder.imageView.setImageBitmap(cursa.getFoto()); // Ensure that the image is set correctlya
    }

    @Override
    public int getItemCount() {
        return cursaList.size();
    }

    public static class CursaViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, location;
        ImageView imageView;

        public CursaViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            location = itemView.findViewById(R.id.location);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
