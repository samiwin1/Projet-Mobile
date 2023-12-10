package com.example.essecproject;

// TeacherDataAdapter.java

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TeacherDataAdapter extends ArrayAdapter<TeacherData> {

    private Context context;
    private List<TeacherData> teacherDataList;
    private TeacherData.OnEditClickListener onEditClickListener;

    public TeacherDataAdapter(Context context, List<TeacherData> teacherDataList, TeacherData.OnEditClickListener onEditClickListener) {
        super(context, 0, teacherDataList);
        this.context = context;
        this.teacherDataList = teacherDataList;
        this.onEditClickListener = onEditClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.teacher_data_list_item, parent, false);
        }

        final TeacherData currentTeacherData = teacherDataList.get(position);

        TextView textViewNomPrenom = listItem.findViewById(R.id.textViewNomPrenom);
        TextView textViewMatiere = listItem.findViewById(R.id.textViewMatiere);
        TextView textViewDate = listItem.findViewById(R.id.textViewDate);
        TextView textViewStatut = listItem.findViewById(R.id.textViewStatut);

        textViewNomPrenom.setText(currentTeacherData.getNom() + " " + currentTeacherData.getPrénom());
        textViewMatiere.setText(currentTeacherData.getMatière());
        textViewDate.setText(currentTeacherData.getDate());
        textViewStatut.setText(currentTeacherData.getStatut());

        // Add an edit button click listener
        Button buttonEdit = listItem.findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEditClickListener != null) {
                    onEditClickListener.onEditClick(currentTeacherData);
                }
            }
        });

        return listItem;
    }
}
