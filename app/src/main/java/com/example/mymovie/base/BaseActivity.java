package com.example.mymovie.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymovie.R;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    public String apiKeyV3 = "b753852a01fe0f123476f4a30dcbacc2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String getCountryCode() {
//        return getResources().getConfiguration().locale.getCountry().toUpperCase();
        return "US";
    }

    public String getLanguageCode() {
//        return getResources().getConfiguration().locale.getLanguage().toLowerCase() + "-" + getResources().getConfiguration().locale.getCountry().toUpperCase();
        return "en-US";
    }

    public void setupToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
