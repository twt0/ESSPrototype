package vs.com.essprototype;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.Toast;



import java.util.ArrayList;
import java.util.HashMap;


public class sEmplyee extends ActionBarActivity implements View.OnClickListener {
    boolean isFiltered = false;
    String products[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
            "iPhone 4S", "Samsung Galaxy Note 800",
            "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};
    // Listview Adapter
    ArrayAdapter<String> adapter;
    // Search EditText
    EditText inputSearch;
    ArrayList<HashMap<String, String>> productList;
    private ListView lv;
    private Toolbar mToolbar;
    private Button btn;
    private EditText edt;
    private ArrayList<String> selected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_emplyee);
        setTitle("Select Employee");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.ic_action_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }




        lv = (ListView) findViewById(R.id.list_view);
        edt = (EditText) findViewById(R.id.editText3);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, products);
        setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String selected = (String) lv.getAdapter().getItem(position);
                Toast.makeText(sEmplyee.this, selected + "-" + position, Toast.LENGTH_SHORT).show();


            }
        });
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                SparseBooleanArray checked = lv.getCheckedItemPositions();
                for (int i = 0; i < products.length; i++) {
                    if (checked.get(i) == true) {
                        Object o =getAdapter().getItem(i);
                        String name = o.toString();
                        // if the arraylist does not contain the name, add it
                        if (selected.contains(name)){
                            // Do Nothing
                        } else {
                            selected.add(name);
                        }
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Uncheck everything:
                for (int i = 0; i < lv.getCount(); i++){
                    lv.setItemChecked(i, false);
                }

                adapter.getFilter().filter(s, new Filter.FilterListener() {
                    public void onFilterComplete(int count) {
                        adapter.notifyDataSetChanged();

                        for (int i = 0; i < adapter.getCount(); i ++) {
                            // if the current (filtered)
                            // listview you are viewing has the name included in the list,
                            // check the box
                            Object o = getAdapter().getItem(i);
                            String name = o.toString();
                            if (selected.contains(name)) {
                                lv.setItemChecked(i, true);
                            } else {
                                lv.setItemChecked(i, false);
                            }
                        }

                    }
                });
            }
        });

        btn = (Button) findViewById(R.id.done);
        btn.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_s_emplyee, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        adapter.notifyDataSetChanged();
        SparseBooleanArray checked = lv.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }

        String[] outputStrArr = new String[selectedItems.size()];

        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
        }

        Intent intent = new Intent();

        intent.putExtra("Selected",outputStrArr);
        setResult(2,intent);
        finish();

    }

    public ListAdapter getAdapter() {
        return lv.getAdapter();
    }

    public void setAdapter(ArrayAdapter<String> adapter) {
        lv.setAdapter(adapter);
    }
}
