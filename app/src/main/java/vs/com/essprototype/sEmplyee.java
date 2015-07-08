package vs.com.essprototype;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class sEmplyee extends ActionBarActivity {
    String products[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
            "iPhone 4S", "Samsung Galaxy Note 800", "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};

    private Toolbar mToolbar;
    private EditText edt;
    private int position;


    private CustomAdapter adapter;

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

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

        final ListView listView = (ListView) findViewById(R.id.list_view);
        adapter = new CustomAdapter(this, products);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setItemsCanFocus(false);
        listView.setTextFilterEnabled(true);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.toggle(position);
                CheckBox cb = (CheckBox) view.findViewById(R.id.check);

                cb.setChecked(true);
            }
        });
        Button b = (Button) findViewById(R.id.done);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String result = "";
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (adapter.mCheckStates.valueAt(i)) {
                        result += " " + products[adapter.mCheckStates.keyAt(i)];
                    }
                }
                
                Toast.makeText(sEmplyee.this, result, Toast.LENGTH_SHORT).show();
            }

        });

        //lv = (ListView) findViewById(R.id.list_view);
        edt = (EditText) findViewById(R.id.editText3);


        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                SparseBooleanArray checked = listView.getCheckedItemPositions();
//                for (int i = 0; i < products.length; i++) {
//                    if (checked.get(i) == true) {
//                        Object o =adapter.getItem(i);
//                        String name = o.toString();
//                        // if the arraylist does not contain the name, add it
//                        if (selected.contains(name)){
//                            // Do Nothing
//                        } else {
//                            selected.add(name);
//                        }
//                    }
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Uncheck everything:
//                for (int i = 0; i < listView.getCount(); i++){
//                    listView.setItemChecked(i, false);
//                }
//
//                adapter.getFilter().filter(s, new Filter.FilterListener() {
//                    public void onFilterComplete(int count) {
//                        adapter.notifyDataSetChanged();
//
//                        for (int i = 0; i < adapter.getCount(); i ++) {
//                            // if the current (filtered)
//                            // listview you are viewing has the name included in the list,
//                            // check the box
//                            Object o = adapter.getItem(i);
//                            String name = o.toString();
//                            if (selected.contains(name)) {
//                                listView.setItemChecked(i, true);
//                            } else {
//                                listView.setItemChecked(i, false);
//                            }
//                        }
//
//                    }
//                });
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_s_emplyee, menu);
        return true;
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }
*/
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
        if (id == android.R.id.home) {

            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onClick(View v) {
//        adapter.notifyDataSetChanged();
//        SparseBooleanArray checked = lv.getCheckedItemPositions();
//        ArrayList<String> selectedItems = new ArrayList<String>();
//        for (int i = 0; i < checked.size(); i++) {
//            // Item position in adapter
//            int position = checked.keyAt(i);
//            // Add sport if it is checked i.e.) == TRUE!
//            if (checked.valueAt(i))
//                selectedItems.add(adapter.getItem(position));
//        }
//
//        String[] outputStrArr = new String[selectedItems.size()];
//
//        for (int i = 0; i < selectedItems.size(); i++) {
//            outputStrArr[i] = selectedItems.get(i);
//        }
//
//        Intent intent = new Intent();
//
//        intent.putExtra("Selected",outputStrArr);
//        setResult(2,intent);
//        finish();
//
//    }


    private class CustomAdapter extends BaseAdapter implements Filterable {
        String[] products;
        ArrayList<String> stringlol;
        ArrayList<String> defaultList;
        Context context = null;
        private SparseBooleanArray mCheckStates;

        public CustomAdapter(Context context, String[] products) {
            this.products = products;
            this.context = context;
            stringlol = new ArrayList<String>();
            defaultList = new ArrayList<String>();

            for (String product : products) {
                stringlol.add(product);
                defaultList.add(product);
            }
            mCheckStates = new SparseBooleanArray();
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return stringlol.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return stringlol.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View vi = convertView;

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            if (convertView == null)
                vi = inflater.inflate(R.layout.list_item, null);


            Spinner spinner = (Spinner) vi.findViewById(R.id.spinnn);
            CheckBox cb = (CheckBox) vi.findViewById(R.id.check);
            cb.setTag(position);
            cb.setChecked(mCheckStates.get(position, false));

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SharedPreferences.Editor editor = getSharedPreferences("LOL", MODE_PRIVATE).edit();
                    // editor.clear();

//                    editor.putInt("pos" +position, position);
//                        editor.putBoolean("value" +position, isChecked);
//
//                    editor.apply();   // I missed to save the data to preference here,.
                    mCheckStates.put((Integer) buttonView.getTag(), isChecked);
                }
            });
            TextView title = (TextView) vi.findViewById(R.id.product_name);

            title.setText(stringlol.get(position));

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(context,
                    R.array.reason_array, android.R.layout.simple_spinner_item);

            spinner.setAdapter(adapter2);
            return vi;
        }

        public boolean isChecked(int position) {
            return mCheckStates.get(position, false);
        }

        public void setChecked(int position, boolean isChecked) {
            mCheckStates.put(position, isChecked);

        }

        public void toggle(int position) {
            setChecked(position, !isChecked(position));

        }


        @Override
        public Filter getFilter() {
            return new filter_here();
        }

        public class filter_here extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                // TODO Auto-generated method stub

                FilterResults Result = new FilterResults();
                // if constraint is empty return the original names
                if (constraint.length() == 0) {
                    Result.values = defaultList;
                    Result.count = defaultList.size();
                    return Result;
                }

                ArrayList<String> Filtered_Names = new ArrayList<String>();
                String filterString = constraint.toString().toLowerCase();
                String filterableString;

                for (int i = 0; i < stringlol.size(); i++) {
                    filterableString = stringlol.get(i);
                    if (filterableString.toLowerCase().contains(filterString)) {
                        Filtered_Names.add(filterableString);
                    }
                }
                Result.values = Filtered_Names;
                Result.count = Filtered_Names.size();

                return Result;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // TODO Auto-generated method stub
                stringlol = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }

        }
    }
}
