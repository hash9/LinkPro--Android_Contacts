package com.example.dell.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        getStudentsList().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> studentsList, View item, int position, long id) {
                Student student = (Student) studentsList.getItemAtPosition(position);
                Intent intention = new Intent(StudentListActivity.this, StudentFormActivity.class);
                intention.putExtra("student", student);

                startActivity(intention);
            }
        });


        registerForContextMenu(getStudentsList());


        Button newStudent = (Button) findViewById(R.id.student_list_new_student);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intention = new Intent(StudentListActivity.this, StudentFormActivity.class);
                startActivity(intention);


            }
        });
    }

    private ListView getStudentsList() {
        return (ListView) findViewById(R.id.student_list_list);

    }


    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }

    private void loadStudents() {
        //Load the students from the database
        StudentDAO dao = new StudentDAO(this);
        List<Student> students = dao.listALL();
        dao.close();


        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
        getStudentsList().setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.equals(getStudentsList())) {

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Student student = (Student) getStudentsList().getItemAtPosition(info.position);

            showContextMenuForStudent(menu, student);
        }

    }

    private void showContextMenuForStudent(ContextMenu menu, final Student student) {
        MenuItem remove = menu.add("Remove");
        remove.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                StudentDAO dao = new StudentDAO(StudentListActivity.this);
                dao.remove(student);
                dao.close();
                Toast.makeText(StudentListActivity.this, "Removing " + student.getName(), Toast.LENGTH_SHORT).show();
                loadStudents();
                return true;
            }
        });
    }

}