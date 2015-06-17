package vs.com.essprototype;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class homepage extends ActionBarActivity {

    private static String TAG = homepage.class.getSimpleName();

    private Toolbar mToolbar;
    private ColorStateList myColorStateList;
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;
    //private FragmentDrawer drawerFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        myColorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked}, //1
                        new int[]{android.R.attr.state_focused}, //2
                        new int[]{android.R.attr.state_focused, android.R.attr
                                .state_pressed},
                        new int[]{} //3
                },
                new int[]{
                        Color.RED, //1
                        Color.GREEN, //2
                        Color.BLUE,
                        Color.BLACK//3
                }
        );
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);

                        navigationView.setItemTextColor(myColorStateList);
                        navigationView.setItemIconTintList(myColorStateList);
                        Fragment fragment = null;
                        mDrawerLayout.closeDrawers();
                        switch (menuItem.getItemId()) {
                            case R.id.homepage:
                                fragment = new HomeFragment();

                                break;
                            case R.id.personal:
                                fragment = new PersonalLeaveFragment();
                                break;
                            case R.id.onbehalf:
                                fragment = new OnBehalfLeaveFragment();
                                break;
                            case R.id.supervisor:
                                fragment = new Supervisorleave();
                                break;
                        }
                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container_body, fragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();

                            // set the toolbar title

                        }


                        return true;
                    }
                });

        //drawerFragment = (FragmentDrawer)
        //  getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        //drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id
        //  .drawer_layout), mToolbar);
        // drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch

        //displayView(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
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
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }


        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        int id;
        switch (position) {
            case 0:
                fragment = new HomeFragment();

                break;
            case 1:
                fragment = new PersonalLeaveFragment();


                break;
            case 2:
                fragment = new OnBehalfLeaveFragment();

                break;
            case 3:
                fragment = new Supervisorleave();

                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            // set the toolbar title

        }
    }*/




}

