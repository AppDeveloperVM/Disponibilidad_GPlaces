package application.android.vicinflames.disponibilidad_gplaces.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import application.android.vicinflames.disponibilidad_gplaces.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch( item.getItemId() ) {
            case R.id.new_place:
                Toast.makeText(this, "Item " + item.getItemId() + "elegido!" , Toast.LENGTH_SHORT).show();

        }
        return true;
    }


}
