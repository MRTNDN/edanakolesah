package com.westernyey.edanakolesah.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.westernyey.edanakolesah.R;

public class shopmenu extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopmenuw);

        // Получение данных из Intent
        Intent intent = getIntent();
        if (intent != null) {
            // Определение, какой фрагмент отобразить в середине
            Fragment fragment = null;
            String fragmentType = intent.getStringExtra("fragmentType");

            if ("RELA".equals(fragmentType)) {
                fragment = new FragmentForRELA();
            } else if ("RELAP".equals(fragmentType)) {
                fragment = new FragmentForRELAP();
            } else if ("RELAr".equals(fragmentType)) {
                fragment = new FragmentForRELAr();
            }

            // Замена фрагмента в середине
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                Log.d("shopmenu", "fragmentType: " + fragmentType);
            }
        }
    }

    public class FragmentForRELA extends Fragment implements com.westernyey.edanakolesah.main.FragmentForRELA {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Загружаем разметку фрагмента из XML
            return inflater.inflate(R.layout.stocks, container, false);
        }
    }

    public static class FragmentForRELAP extends Fragment {
    }

    public static class FragmentForRELAr extends Fragment {
    }
}
