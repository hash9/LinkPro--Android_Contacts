package com.example.dell.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);


        if(hasIntentionToUpdate()) {
            Student student = getOriginalStudentToUpdate();
            StudentFormViewHelper helper = new StudentFormViewHelper(this);
            helper.fillInTheForm(student);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.student_form_confirm){
            StudentFormViewHelper helper = new StudentFormViewHelper(this);
            Student student = helper.createAStudent();

            //save the student into the database
            StudentDAO dao = new StudentDAO(this);
            if(hasIntentionToUpdate()){
                dao.update(student, getOriginalStudentToUpdate().getId());
            }else {
                dao.insert(student);
            }

            String message = "'" + student.getName() + "' was saved with grading " + student.getGrading();
            Toast.makeText(StudentFormActivity.this, message, Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean hasIntentionToUpdate() {
        return getIntent().hasExtra("student");
    }

    private Student getOriginalStudentToUpdate() {
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        return student;
    }
}
