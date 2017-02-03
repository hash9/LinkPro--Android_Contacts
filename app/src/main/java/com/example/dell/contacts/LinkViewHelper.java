package com.example.dell.contacts;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RatingBar;

public class LinkViewHelper {

    private final Activity activity;

    public LinkViewHelper(Activity activity){
        this.activity = activity;
    }

    private String getName() {
        int field = R.id.student_form_name;
        return getTestFieldValue(field);
    }

    private String getTestFieldValue(int fieldId) {
        EditText field = (EditText) activity.findViewById(fieldId);
        String value = field.getText().toString();
        return value;
    }

    private String getAddress() {
        return getTestFieldValue(R.id.student_form_address);
    }

    private String getPhoneNumber() {
        return getTestFieldValue(R.id.student_form_phonenumber);
    }

    private String getWebsite() {
        return getTestFieldValue(R.id.student_form_website);
    }

    private String getEmail() {
        return getTestFieldValue(R.id.student_form_email);
    }

    public Link createALink() {
        return new Link(getName(), getAddress(), getPhoneNumber(), getWebsite(), getEmail(), getGrading());
    }

    private double getGrading() {
        RatingBar rating = (RatingBar) activity.findViewById(R.id.student_form_grading);
        return rating.getRating();
    }

    public void fillInTheForm(Link link) {
        fill(R.id.student_form_name, link.getName());
        fill(R.id.student_form_address, link.getAddress());
        fill(R.id.student_form_phonenumber, link.getPhoneNumber());
        fill(R.id.student_form_website, link.getWebsite());
        fill(R.id.student_form_email, link.getEmail());
        RatingBar rating = (RatingBar) activity.findViewById(R.id.student_form_grading);
        rating.setRating((float) link.getGrading());
    }

    private void fill(int id, String value) {
        EditText field = (EditText) activity.findViewById(id);
        field.setText(value);
    }
}
