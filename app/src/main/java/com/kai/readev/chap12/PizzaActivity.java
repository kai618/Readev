package com.kai.readev.chap12;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.kai.readev.R;
import com.kai.readev.chap8.OrderActivity;

import java.util.Stack;

public class PizzaActivity extends AppCompatActivity {

    private final Stack<Integer> tabBackStack = new Stack<>();
    private ViewPager2 viewPager;

    private final Fragment[] pages = new Fragment[]{
            new TopFragment(),
            new PizzaFragment(),
            new PastaFragment(),
            new StoreFragment()
    };

    private final int[] titleRes = new int[]{
            R.string.home_tab,
            R.string.pizza_tab,
            R.string.pasta_tab,
            R.string.store_tab
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.paper);
        viewPager.setAdapter(new SectionsPagerAdapter(this));
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (tabBackStack.isEmpty() || tabBackStack.lastElement() != position) tabBackStack.add(position);
            }
        });

        TabLayout tabLayout = findViewById(R.id.tabs);
        new TabLayoutMediator(
                tabLayout,
                viewPager,
                (tab, position) -> tab.setText(getResources().getString(titleRes[position]))
        ).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pizza, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_create_order) {
            startActivity(new Intent(this, OrderActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (tabBackStack.size() == 1) {
            super.onBackPressed();
        } else {
            tabBackStack.pop();
            viewPager.setCurrentItem(tabBackStack.lastElement());
        }
    }

    private class SectionsPagerAdapter extends FragmentStateAdapter {
        public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return pages[position];
        }

        @Override
        public int getItemCount() {
            return pages.length;
        }
    }
}