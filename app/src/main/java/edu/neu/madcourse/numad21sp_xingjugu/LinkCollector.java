package edu.neu.madcourse.numad21sp_xingjugu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity {

    //Creating the essential parts needed for a Recycler view to work: RecyclerView, Adapter, LayoutManager
    private ArrayList<URLItem> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        init(savedInstanceState);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 0;
                LinearLayout dialog_layout = new LinearLayout(LinkCollector.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(LinkCollector.this);
                builder.setTitle("Input: name and url");

                final EditText input_name = new EditText(LinkCollector.this);
                final EditText input_url = new EditText(LinkCollector.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text

                input_name.setInputType(InputType.TYPE_CLASS_TEXT);
                input_url.setInputType(InputType.TYPE_CLASS_TEXT);
                dialog_layout.addView(input_name);
                dialog_layout.addView(input_url);
                builder.setView(dialog_layout);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url_name = input_name.getText().toString();
                        String url_link = input_url.getText().toString();
                        if (URLUtil.isValidUrl(url_link)) {
                            addItem(pos, url_name, url_link);
                        } else {
                            Toast.makeText(LinkCollector.this, "Invaldi URL", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

                //pop up window to input url name and link


            }
        });
    }


    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        for (int i = 0; i < size; i++) {
            // put itemName information into instance
            outState.putString(KEY_OF_INSTANCE + i + "0", itemList.get(i).getURLName());
            // put itemDesc information into instance
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getURLLink());
        }
        super.onSaveInstanceState(outState);

    }


    private void init(Bundle savedInstanceState) {

        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String itemDesc = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");

                    URLItem url_item = new URLItem(itemName, itemDesc);

                    itemList.add(url_item);
                }
            }
        }

    }

    private void createRecyclerView() {
        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(itemList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public Intent onItemClick(int position) {
                //attributions bond to the item has been changed
                Intent intent = itemList.get(position).onItemClick(position);
                rviewAdapter.notifyItemChanged(position);
                return intent;
            }
        };
        rviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);


    }

    private void addItem(int position, String URL_name, String URL_link) {
        itemList.add(position, new URLItem(URL_name, URL_link));
        Toast.makeText(LinkCollector.this, "Successfully added new link", Toast.LENGTH_SHORT).show();
        rviewAdapter.notifyItemInserted(position);
    }
}