package org.milaifontanals.projecte;



import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
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

        navController = Navigation.findNavController(this, R.id.nav_controller);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Abre el DrawerLayout al inicio
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String sportType = "All";  // Valor por defecto
        NavController nav = Navigation.findNavController(this, R.id.nav_host_fragment);

        int id = item.getItemId();
        if (id == R.id.nav_natacio) {
            sportType = "Natacio";
        } else if (id == R.id.nav_running) {
            sportType = "Running";
        } else if (id == R.id.nav_cycling) {
            sportType = "Cycling";
        } else if (id == R.id.nav_moto) {
            sportType = "Moto";
        } else if (id == R.id.nav_trail) {
            sportType = "Trail";
        } else {
            sportType = "All";  // Valor por defecto o manejar otros casos
        }

        Bundle bundle = new Bundle();
        bundle.putString("sportType", sportType);
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
