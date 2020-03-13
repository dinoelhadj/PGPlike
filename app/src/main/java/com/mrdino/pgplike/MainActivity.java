package com.mrdino.pgplike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText et_message = (EditText) findViewById(R.id.et_message);
        final EditText et_key = (EditText) findViewById(R.id.et_key);
        final EditText et_result = (EditText) findViewById(R.id.et_result);
        final TextView tv = (TextView)findViewById(R.id.textView2);
        Button btn_crypt = (Button) findViewById(R.id.btn_crypt);
        Button btn_decrypt = (Button) findViewById(R.id.btn_decrypt);
        Button btn_keyGenerator = (Button) findViewById(R.id.btn_keyGenerator);

        btn_crypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = et_message.getText().toString();
                if (et_key.getText().toString().contains("@")){
                    String key = et_key.getText().toString();
                    tv.setText(CryptDino(message,key));
                    et_result.setText(CryptDino(message,key));
                } else {
                    int key = Integer.parseInt(et_key.getText().toString());
                    tv.setText(CryptDino(message,key));
                    et_result.setText(CryptDino(message,key));

                }
            }
        });

        btn_decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = et_message.getText().toString();
                if (et_key.getText().toString().contains("@")){
                    String key = et_key.getText().toString();
                    tv.setText(DecryptDino(message,key));
                    et_result.setText(DecryptDino(message,key));
                } else {
                    int key = Integer.parseInt(et_key.getText().toString());
                    tv.setText(DecryptDino(message,key));
                    et_result.setText(DecryptDino(message,key));
                }
            }
        });

        btn_keyGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, keyGenerator.class);
                startActivity(intent);
            }
        });

    }

    String CryptDino(String message, int key){
        String result = "";
        char a;
        for (int i = 0; i < message.length() ; i++) {
            a = (char)(((int)(message.charAt(i))) + key);
            result = result + a;
        }
        return result;
    }
    String CryptDino(String message, String keyString){
        String result = "";
        char a;
        int key = publicKeyToPrivateKey(keyString);
        for (int i = 0; i < message.length() ; i++) {
            a = (char)(((int)(message.charAt(i))) + key);
            result = result + a;
        }
        return result;
    }

    String DecryptDino(String message, int key){
        String result = "";
        char a;
        for (int i = 0; i < message.length() ; i++) {
            a = (char)(((int)(message.charAt(i))) - key);
            result = result + a;
        }
        return result;
    }

    String DecryptDino(String message, String keyString){
        String result = "";
        char a;
        int key = publicKeyToPrivateKey(keyString);
        for (int i = 0; i < message.length() ; i++) {
            a = (char)(((int)(message.charAt(i))) - key);
            result = result + a;
        }
        return result;

    }


    int publicKeyToPrivateKey(String publicKeyString){
        int result = 0;
        publicKeyString = publicKeyString.substring(0, publicKeyString.length()-2);
        int publicKey = Integer.parseInt(publicKeyString);
        result = publicKey / 2;
        return result;
    }
}
