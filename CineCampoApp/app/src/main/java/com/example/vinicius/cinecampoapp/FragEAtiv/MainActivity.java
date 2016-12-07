package com.example.vinicius.cinecampoapp.FragEAtiv;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.vinicius.cinecampoapp.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    private static final int PROFILE_SETTING = 1;

    private Drawer result = null;
    private AccountHeader headerResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withIcon(R.drawable.popcorn);

        headerResult = new AccountHeaderBuilder()
                .withActivity(MainActivity.this)
                .withTranslucentStatusBar(false)
                .withHeaderBackground(R.drawable.header_background)
                .addProfiles(
                        profile
                )
                .withSavedInstance(savedInstanceState)
                .build();


        // Handle Toolbar
        result = new DrawerBuilder()
                .withActivity(MainActivity.this)
                .withAccountHeader(headerResult)
                .withSavedInstance(savedInstanceState)
                .withDisplayBelowStatusBar(false)
                .withTranslucentStatusBar(false)
                .withDrawerLayout(R.layout.material_drawer_fits_not)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home"),
                        new PrimaryDrawerItem().withName("Filmes em exibição"),
                        new PrimaryDrawerItem().withName("Em breve"),
                        new SectionDrawerItem().withName("Informações"),
                        new PrimaryDrawerItem().withName("Contato")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                Fragment FragmentHome = new Home();
                                getFragmentManager().beginTransaction().replace(R.id.activity_main, FragmentHome).addToBackStack(null).commit();
                                CloseDrawer();
                                break;
                            case 2:
                                Fragment FragmentExibicao = new Exibicao();
                                getFragmentManager().beginTransaction().replace(R.id.activity_main, FragmentExibicao).addToBackStack(null).commit();
                                CloseDrawer();
                                break;
                            case 3:
                                Fragment FragmentEmBreve = new EmBreves();
                                getFragmentManager().beginTransaction().replace(R.id.activity_main, FragmentEmBreve).addToBackStack(null).commit();
                                CloseDrawer();
                                break;
                        }

                        return false;
                    }
                })
                .build();
        Fragment FragmentHome = new Home();
        getFragmentManager().beginTransaction().replace(R.id.activity_main, FragmentHome).commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    public void CloseDrawer(){
        if(result.isDrawerOpen()){
            result.closeDrawer();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
