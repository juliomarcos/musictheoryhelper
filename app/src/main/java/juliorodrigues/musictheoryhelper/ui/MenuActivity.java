package juliorodrigues.musictheoryhelper.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import juliorodrigues.musictheoryhelper.R;

public class MenuActivity extends AppCompatActivity {

    private GridView menuGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle(R.string.app_name);

        menuGridView = (GridView) findViewById(R.id.menuGridView);
        menuGridView.setAdapter(new GridMenuAdapter(this));
        menuGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: openToneCalculatorScreen();
                }
            }
        });
    }

    private void openToneCalculatorScreen() {
        startActivity(new Intent(this, ToneCalculatorActivity.class));
    }
}
