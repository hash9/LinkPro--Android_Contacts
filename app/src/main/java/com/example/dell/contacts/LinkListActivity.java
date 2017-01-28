package com.example.dell.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class LinkListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        getLinksList().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> linksList, View item, int position, long id) {
                Link link = (Link) linksList.getItemAtPosition(position);
                Intent intention = new Intent(LinkListActivity.this, LinkActivity.class);
                intention.putExtra("link", link);

                startActivity(intention);
            }
        });

        registerForContextMenu(getLinksList());

        Button newLink = (Button) findViewById(R.id.student_list_new_student);
        newLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intention = new Intent(LinkListActivity.this, LinkActivity.class);
                startActivity(intention);
            }
        });
    }

    private ListView getLinksList() {
        return (ListView) findViewById(R.id.student_list_list);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLinks();
    }

    private void loadLinks() {
        //Load the links from the database
        LinkDAO dao = new LinkDAO(this);
        List<Link> links = dao.listALL();
        dao.close();

        ArrayAdapter<Link> adapter = new ArrayAdapter<Link>(this, android.R.layout.simple_list_item_1, links);
        getLinksList().setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.equals(getLinksList())) {

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Link link = (Link) getLinksList().getItemAtPosition(info.position);

            showContextMenuForLink(menu, link);
        }
    }

    //// Remove a link, Long press context menu
    private void showContextMenuForLink(ContextMenu menu, final Link link) {
        MenuItem remove = menu.add("Remove");
        remove.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                LinkDAO dao = new LinkDAO(LinkListActivity.this);
                dao.remove(link);
                dao.close();
                Toast.makeText(LinkListActivity.this, "Removing " + link.getName(), Toast.LENGTH_SHORT).show();
                loadLinks();
                return true;
            }
        });
    }

}