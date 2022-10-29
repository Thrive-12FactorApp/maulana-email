package com.thrive.model;


public class EmailRequest {

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public EmailRequest(String email, String nama) {
        this.email = email;
        this.nama = nama;
    }

    String email;
    String nama;

    String subjek;

    String body;

    public String getSubjek() {
        return subjek;
    }

    public String getBody() {
        return body;
    }

    public void setSubjek(String subjek) {
        this.subjek = subjek;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
