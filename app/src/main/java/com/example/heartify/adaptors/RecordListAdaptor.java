package com.example.heartify.adaptors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heartify.R;
import com.example.heartify.activities.COERecordActivity;
import com.example.heartify.activities.RecordDetailsActivity;
import com.example.heartify.interfaces.RecordListListeners;
import com.example.heartify.models.Record;
import com.example.heartify.utils.Helpers;

import java.util.ArrayList;
import java.util.List;

public class RecordListAdaptor extends RecyclerView.Adapter<RecordListAdaptor.RecordListViewHolder> {

    List<Record> records = new ArrayList<>();
    Context context;
    private RecordListListeners recordListListeners;

    public RecordListAdaptor(Context context, List<Record> records, RecordListListeners recordListListeners) {
        this.records = records;
        this.context = context;
        this.recordListListeners = recordListListeners;
    }

    @NonNull
    @Override
    public RecordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.record_recyler_item_view,parent,false);
        return new RecordListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecordListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.dateMeasured.setText(records.get(position).getDateMeasured());
        holder.sysPressure.setText(records.get(position).getSystolicPressure()+" mmHg");
        holder.diaPressure.setText(records.get(position).getDiastolicPressure()+" mmHg");
        holder.heartRate.setText(records.get(position).getHeartRate()+" bits/min");
        holder.status.setText(Helpers.getStatus(
                records.get(position).getSystolicPressure(),
                records.get(position).getDiastolicPressure()));
        holder.statusBackground.setBackgroundColor(Color.parseColor(Helpers.getStatusColor(
                records.get(position).getSystolicPressure(),
                records.get(position).getDiastolicPressure())));

        //on details record click
        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecordDetailsActivity.class);
                intent.putExtra("index",position);

                context.startActivity(intent);
            }
        });

        //on edit record click
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(context, "okay" + String.valueOf(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, COERecordActivity.class);

                intent.putExtra("index",position);
                context.startActivity(intent);
            }
        });

        //on delete record click
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordListListeners.onRecordDeleteListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class RecordListViewHolder extends RecyclerView.ViewHolder{
        TextView sysPressure,diaPressure,heartRate,dateMeasured,status;
        Button editButton,deleteButton,viewButton;
        ConstraintLayout statusBackground;
        public RecordListViewHolder(@NonNull View itemView) {
            super(itemView);

            sysPressure = itemView.findViewById(R.id.r_r_v_i_systolic_data);
            diaPressure = itemView.findViewById(R.id.r_r_v_i_diastolic_data);
            heartRate = itemView.findViewById(R.id.r_r_v_i_heart_rate_data);
            dateMeasured= itemView.findViewById(R.id.r_r_v_i_dateMeasured);
            statusBackground = itemView.findViewById(R.id.r_r_v_i_status_background);
            status = itemView.findViewById(R.id.r_r_v_i_status_data);
            viewButton = itemView.findViewById(R.id.r_r_v_i_view_button);
            editButton = itemView.findViewById(R.id.r_r_v_i_edit_button);
            deleteButton = itemView.findViewById(R.id.r_r_v_i_delete_button);

        }
    }

}
