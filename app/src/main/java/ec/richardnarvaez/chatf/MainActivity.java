package ec.richardnarvaez.chatf;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import ec.richardnarvaez.chatf.activities.LoginActivity;
import ec.richardnarvaez.chatf.activities.SplashActivity;
import ec.richardnarvaez.chatf.chat.adapters.TabsPagesAdapter;
import ec.richardnarvaez.chatf.utils.FirebaseUtils;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();

    DatabaseReference mRootChild = mDatabaseReference.child("users");
    private FirebaseAuth mAuth;

    private TabLayout tabLayout;
    private ViewPager vistaP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabs);

        //creo el viewPager
        TabsPagesAdapter tabsPagerAdapter = new TabsPagesAdapter(this, getSupportFragmentManager());
        vistaP=findViewById(R.id.ContentScrol);
        //le mando el viewPager a vistaP
        vistaP.setAdapter(tabsPagerAdapter);
        //enlazo los Tabs con el movimiento del ViewPager
        tabLayout.setupWithViewPager(vistaP);
        //para poner toast en cada página
        vistaP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        FirebaseUtils.getCurrentUserRef().child("author").child("user_name").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                if (!snapshot.exists()) {
//                    NeftyUtil.goUserNameActivity(MainActivity.this);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
// Salida de sesion
        if(id == R.id.action_logout){
            Toast.makeText(this, "Closing session...", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}