package com.westernyey.edanakolesah.main;

import android.os.Bundle;
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

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_search_up, new fragment_search())
                    .replace(R.id.fragment_content_container, new ContentFragment())
                    .replace(R.id.fragment_buttons_container, new ButtonsFragment())
                    .commit();
        }
    }

    public static class fragment_search extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Ваши действия по созданию вида для фрагмента fragment_search
            return inflater.inflate(R.layout.fragment_search, container, false);
        }
    }

    public static class ContentFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Ваши действия по созданию вида для фрагмента ContentFragment
            return inflater.inflate(R.layout.stocks, container, false);
        }
    }

    public static class ButtonsFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Ваши действия по созданию вида для фрагмента ButtonsFragment
            return inflater.inflate(R.layout.fragment_buttons, container, false);
        }
    }
}
