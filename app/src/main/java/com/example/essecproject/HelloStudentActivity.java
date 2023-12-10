package com.example.essecproject;
// HelloTeacherActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import com.google.firebase.messaging.FirebaseMessaging;
public class HelloStudentActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    private ListView listViewTeacherData;
    private List<TeacherData> teacherDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_teacher);
        FirebaseMessaging.getInstance().subscribeToTopic("teachers");
        listViewTeacherData = findViewById(R.id.listViewTeacherData);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        setTitle("Hello Student");

        Button buttonLogoutTeacher = findViewById(R.id.buttonLogoutTeacher);
        buttonLogoutTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        listViewTeacherData = findViewById(R.id.listViewTeacherData);
        teacherDataList = new ArrayList<>();

        // Add a listener for editing data
        TeacherData.OnEditClickListener onEditClickListener = new TeacherData.OnEditClickListener() {
            @Override
            public void onEditClick(TeacherData teacherData) {
                // Implement the edit functionality here
                Toast.makeText(HelloStudentActivity.this, "Edit Clicked for " + teacherData.getNom(), Toast.LENGTH_SHORT).show();
            }
        };

        retrieveTeacherData(onEditClickListener);
    }

    private void logout() {
        auth.signOut();
        startActivity(new Intent(HelloStudentActivity.this, MainActivity.class));
        finish();
    }

    private void retrieveTeacherData(final TeacherData.OnEditClickListener onEditClickListener) {
        String userId = "1tMheOVHxygHWKK2wyznpj09XxXYS1piUv11GYQlMp6U";

        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot subjectSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot teacherSnapshot : subjectSnapshot.getChildren()) {
                        TeacherData teacherData = teacherSnapshot.getValue(TeacherData.class);
                        teacherDataList.add(teacherData);
                    }
                }

                displayTeacherData(onEditClickListener);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HelloStudentActivity.this, "Failed to retrieve data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayTeacherData(TeacherData.OnEditClickListener onEditClickListener) {
        TeacherDataAdapter teacherDataAdapter = new TeacherDataAdapter(this, teacherDataList, onEditClickListener);
        listViewTeacherData.setAdapter(teacherDataAdapter);
    }
}
