package kau.msproject.searchaed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GeoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);

        Intent intent = getIntent();
        Double lat = intent.getExtras().getDouble("lat");
        Double lon = intent.getExtras().getDouble("lon");

        Button btnMain = (Button)findViewById(R.id.btn_main);
        btnMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mIntent);
            }
        });
        
    }
}
