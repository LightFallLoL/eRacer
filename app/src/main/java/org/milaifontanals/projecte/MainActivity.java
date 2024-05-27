package org.milaifontanals.projecte;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.cursesFragment);


        // Abre el DrawerLayout al inicio
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int sportTypeId = -1;  // Valor por defecto
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        int id = item.getItemId();
        if (id == R.id.nav_natacio) {
            sportTypeId = 1;
        } else if (id == R.id.nav_cycling) {
            sportTypeId = 2;
        } else if (id == R.id.nav_trail) {
            sportTypeId = 3;
        } else if (id == R.id.nav_moto) {
            sportTypeId = 4;
        } else if (id == R.id.nav_running) {
            sportTypeId = 5;
        }else if (id == R.id.nav_etc) {
            sportTypeId = -1;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("sportTypeId", sportTypeId);
        navController.navigate(R.id.cursesFragment, bundle);

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp();
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
