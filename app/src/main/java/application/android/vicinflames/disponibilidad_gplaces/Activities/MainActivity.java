package application.android.vicinflames.disponibilidad_gplaces.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import application.android.vicinflames.disponibilidad_gplaces.Fragments.MpFragment;
import application.android.vicinflames.disponibilidad_gplaces.Fragments.Welcome_Fragment;
import application.android.vicinflames.disponibilidad_gplaces.R;

public class MainActivity extends AppCompatActivity {

    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //comprobar disposición del activity ( portrait / landscape
        if (savedInstanceState == null) {
            currentFragment = new Welcome_Fragment();
            changeFragment(currentFragment);
        }

    }

    //-- MENÚ OPCIONES --
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch( item.getItemId() ) {
            //opcion 1 - pantalla de bienvenida
            case R.id.welcome_screen:
                currentFragment = new Welcome_Fragment();
                Toast.makeText(this, "Item 1 elegido!" , Toast.LENGTH_SHORT).show();
                break;

            //opcion 2 - pantalla de mapa
            case R.id.new_place:
                currentFragment = new MpFragment();
                Toast.makeText(this, "Item 2 elegido!" , Toast.LENGTH_SHORT).show();
                break;
        }

        changeFragment(currentFragment);
        return super.onOptionsItemSelected(item);
    }
    // ------

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }

}
