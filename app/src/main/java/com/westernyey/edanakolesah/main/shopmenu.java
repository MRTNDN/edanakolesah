package com.westernyey.edanakolesah.main;

import static android.text.TextUtils.replace;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.westernyey.edanakolesah.R;

import java.sql.Connection;

public class shopmenu extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopmenuw);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_search_up, new fragment_search())
                    .replace(R.id.fragment_content_container, new ())
                    .replace(R.id.fragment_buttons_container, new Fragment())
                    .commit();

            // Создаем экземпляр ButtonsFragment
            ButtonsFragment buttonsFragment = new ButtonsFragment();

            // Добавляем ButtonsFragment в контейнер
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_buttons_container, buttonsFragment)
                    .commit();
        }
    }

    private Connection replace(int fragmentButtonsContainer, Fragment fragment) {
    }

    public static class fragment_search extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_search, container, false);
        }
    }

    public static class ContentFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.stocks, container, false);
        }
    }

    public static class ButtonsFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.main, container, false);

            // Находим кнопки по их идентификаторам
            Button buttonRular = view.findViewById(R.id.RELAr);
            Button buttonRelap = view.findViewById(R.id.RELAP);
            Button buttonRela = view.findViewById(R.id.RELA);

            buttonRular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceContentFragment(new RestaurantFragment());
                }
            });

            buttonRelap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceContentFragment(new ProductFragment());
                }
            });

            return view;
        }

        private void replaceContentFragment(Fragment fragment) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity != null) {
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content_container, fragment)
                        .commit();
            }
        }
    }
}

class RestaurantFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.restaurant_fragment, container, false);
    }
}

class ProductFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_fragment, container, false);
    }
}
