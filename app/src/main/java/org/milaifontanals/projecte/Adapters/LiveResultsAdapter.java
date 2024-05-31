package org.milaifontanals.projecte.Adapters;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.milaifontanals.projecte.Model.Circuit;
import org.milaifontanals.projecte.Model.Inscripcio;
import org.milaifontanals.projecte.Model.Participant;
import org.milaifontanals.projecte.Model.Registre;
import org.milaifontanals.projecte.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LiveResultsAdapter extends RecyclerView.Adapter<LiveResultsAdapter.LiveResultsViewHolder> {

    private List<Registre> registreList;
    private Circuit selectedCircuit;
    private Date cursaDataInici;
    private List<Inscripcio> inscripcions;
    private List<Participant> participants;

    public LiveResultsAdapter(List<Registre> registres, Circuit selectedCircuit, Date cursaDataInici, List<Inscripcio> inscripcions, List<Participant> participants) {
        this.registreList = registres;
        this.selectedCircuit = selectedCircuit;
        this.cursaDataInici = cursaDataInici;
        this.inscripcions = inscripcions;
        this.participants = participants;
    }

    @NonNull
    @Override
    public LiveResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_result_item, parent, false);
        return new LiveResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LiveResultsViewHolder holder, int position) {
        Registre registre = registreList.get(position);
        holder.bind(registre);
    }

    @Override
    public int getItemCount() {
        return registreList.size();
    }

    public void updateRegistres(List<Registre> newRegistres) {
        this.registreList = newRegistres;
        notifyDataSetChanged();
    }

    class LiveResultsViewHolder extends RecyclerView.ViewHolder {

        TextView txvCheckpoints, txvTime, txvDorsal, txvName;

        LiveResultsViewHolder(@NonNull View itemView) {
            super(itemView);
            txvCheckpoints = itemView.findViewById(R.id.txvCheckpoints);
            txvTime = itemView.findViewById(R.id.txvTime);
            txvDorsal = itemView.findViewById(R.id.txvDorsal);
            txvName = itemView.findViewById(R.id.txvName);
        }

        void bind(Registre registre) {
            txvCheckpoints.setText(String.format("%d/%d", registre.getCheckpoint().getChkId(), selectedCircuit.getCheckpoints()));
            String formattedTime = calculateTimeDifference(cursaDataInici, registre.getRegTemps());
            txvTime.setText(formattedTime);
            txvDorsal.setText(String.valueOf(registre.getInscripcio().getDorsal()));

            Participant participant = getParticipantFromRegistre(registre);
            if (participant != null) {
                txvName.setText(String.format("%s %s", participant.getNom(), participant.getCognoms()));
            } else {
                txvName.setText("Unknown Participant");
            }
        }

        private Participant getParticipantFromRegistre(Registre registre) {
            for (Inscripcio inscripcio : inscripcions) {
                if (inscripcio.getInsId() == registre.getRegInsId()) {
                    for (Participant participant : participants) {
                        if (participant.getId() == inscripcio.getParId()) {
                            return participant;
                        }
                    }
                }
            }
            return null;
        }
    }

    private String calculateTimeDifference(Date startDate, Date regTempsDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        long differenceInMillis = regTempsDate.getTime() - startDate.getTime();
        long hours = differenceInMillis / (1000 * 60 * 60);
        long minutes = (differenceInMillis / (1000 * 60)) % 60;
        long seconds = (differenceInMillis / 1000) % 60;
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
    }
    }



