package org.milaifontanals.projecte.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.milaifontanals.projecte.Model.Circuit;
import org.milaifontanals.projecte.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CircuitAdapter extends RecyclerView.Adapter<CircuitAdapter.CircuitViewHolder> {
    private List<Circuit> circuitList;


    private int selectedPosition = -1;
    public CircuitAdapter(List<Circuit> circuitList) {
        this.circuitList = circuitList;
    }

    public List<Circuit> getCircuitList() {
        return circuitList;
    }

    public void setCircuitList(List<Circuit> circuitList) {
        this.circuitList = circuitList;
    }

    public Circuit getSelectedCircuit() {
        if (selectedPosition != -1) {
            return circuitList.get(selectedPosition);
        }
        return null;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @NonNull
    @Override
    public CircuitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.circuit_item, parent, false);
        return new CircuitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CircuitViewHolder holder, int position) {
        Circuit circuit = circuitList.get(position);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedDate = dateFormat.format(circuit.getTempsEstimat());

        holder.tvCircuitTitle.setText(circuit.getNom());
        holder.tvEstimatedTime.setText("ETA : "+circuit.getFormattedTempsEstimat() + "h");
        holder.tvDistance.setText(String.format(Locale.getDefault(), "%.2f Km", circuit.getDistancia()));
        holder.tvCircuitPrice.setText(String.format(Locale.getDefault(), "%.2f€", circuit.getPreu()));


        holder.itemView.setSelected(selectedPosition == position);
        holder.itemView.setOnClickListener(v -> {
            notifyItemChanged(selectedPosition); // Deseleccionar el anterior
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(selectedPosition); // Seleccionar el nuevo
        });
    }

    @Override
    public int getItemCount() {
        return circuitList.size();
    }

    public static class CircuitViewHolder extends RecyclerView.ViewHolder {
        TextView tvCircuitTitle, tvEstimatedTime, tvDistance, tvCircuitPrice;

        public CircuitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCircuitTitle = itemView.findViewById(R.id.tvCircuitTitle);
            tvEstimatedTime = itemView.findViewById(R.id.tvEstimatedTime);
            tvDistance = itemView.findViewById(R.id.tvDistance);
            tvCircuitPrice = itemView.findViewById(R.id.tvCircuitPrice);
        }
    }
}
