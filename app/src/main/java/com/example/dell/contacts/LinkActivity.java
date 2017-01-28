package com.example.dell.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class LinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        if(hasIntentionToUpdate()) {
            Link link = getOriginalLinkToUpdate();
            LinkViewHelper helper = new LinkViewHelper(this);
            helper.fillInTheForm(link);
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
            LinkViewHelper helper = new LinkViewHelper(this);
            Link link = helper.createALink();

            //save the student into the database
            LinkDAO dao = new LinkDAO(this);
            if(hasIntentionToUpdate()){
                dao.update(link, getOriginalLinkToUpdate().getId());
            }else {
                dao.insert(link);
            }

            String message = "'" + link.getName() + "' was saved with grading " + link.getGrading();
            Toast.makeText(LinkActivity.this, message, Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean hasIntentionToUpdate() {
        return getIntent().hasExtra("link");
    }

    private Link getOriginalLinkToUpdate() {
        Intent intent = getIntent();
        Link link = (Link) intent.getSerializableExtra("link");
        return link;
    }
}
