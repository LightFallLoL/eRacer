package org.milaifontanals.projecte;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import org.milaifontanals.projecte.Fragments.CursesFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // Abre el DrawerLayout al inicio
        drawerLayout.openDrawer(GravityCompat.START);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                   /* case R.id.nav_natacio:
                        fragment = new CursesFragment();
                        break;
                    case R.id.nav_running:
                        fragment = new CursesFragment();
                        break;
                    case R.id.nav_walking:
                        fragment = new CursesFragment();
                        break;
                    case R.id.nav_etc:
                        fragment = new CursesFragment();
                        break;
                    case R.id.nav_configuracio:
                        fragment = new CursesFragment();
                        break;*/
                    default:
                        fragment = new CursesFragment();
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });

        // Cargar fragmento inicial
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CursesFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_natacio);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
