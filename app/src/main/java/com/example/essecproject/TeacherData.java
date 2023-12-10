package com.example.essecproject;

public class TeacherData {
    private String Date;
    private String Matière;
    private String Nom;
    private String Prénom;
    private String Statut;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMatière() {
        return Matière;
    }

    public void setMatière(String matière) {
        Matière = matière;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrénom() {
        return Prénom;
    }

    public void setPrénom(String prénom) {
        Prénom = prénom;
    }

    public String getStatut() {
        return Statut;
    }

    public void setStatut(String statut) {
        Statut = statut;
    }

    public TeacherData() {
        // Default constructor required for calls to DataSnapshot.getValue(TeacherData.class)
    }

    public TeacherData(String date, String matière, String nom, String prénom, String statut) {
        Date = date;
        Matière = matière;
        Nom = nom;
        Prénom = prénom;
        Statut = statut;
    }
    public interface OnEditClickListener {
        void onEditClick(TeacherData teacherData);
    }
}
