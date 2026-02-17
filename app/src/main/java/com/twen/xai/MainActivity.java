
package com.twen.xai;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MaterialButton button = new MaterialButton(this);
        button.setText("TwenX CI Fixed ✅");
        setContentView(button);
    }
}
