package com.mrdino.pgplike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class keyGenerator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_generator);

        final EditText privateKeyInput = (EditText)findViewById(R.id.editText2);
        final EditText publicKeyInput = (EditText)findViewById(R.id.editText6);
        Button generatePk = (Button)findViewById(R.id.button4);

        generatePk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int privateKey = Integer.parseInt(privateKeyInput.getText().toString());
                int publicKey = privateKeyToPublicKey(privateKey);
                String result = Integer.toString(publicKey);
                publicKeyInput.setText((result + "@@"));
            }
        });
    }

    int privateKeyToPublicKey(int privateKey){
        int result = 0;
        result = 2 * privateKey;
        return result;
    }

    int publicKeyToPrivateKey(String publicKeyString){
        int result = 0;
        publicKeyString = publicKeyString.substring(0, publicKeyString.length()-1);
        int publicKey = Integer.parseInt(publicKeyString);
        result = publicKey / 2;
        return result;
    }
}
