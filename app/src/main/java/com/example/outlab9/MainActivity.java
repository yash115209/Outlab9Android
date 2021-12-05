package com.example.outlab9;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import com.example.outlab9.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private static final int NUM_PAGES = 4;
    public static ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    boolean isAllFabsVisible;
    // Make sure to use the FloatingActionButton
    // for all the FABs
    FloatingActionButton mAddFab, mAddExamFab, mAddAssFab, mAddLecFab, mAddPlanFab;
    // These are taken to make visible and invisible along
    // with FABs
    TextView addAssText, addExamText, addLecText, addPlanText;

    private String[] titles = new String[]{"Study Plans", "Exams", "Lectures", "Assignments"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Register all the FABs with their IDs
        // This FAB button is the Parent
        mAddFab = findViewById(R.id.add_fab);
        // FAB button
        mAddAssFab = findViewById(R.id.add_ass_fab);
        mAddExamFab = findViewById(R.id.add_exam_fab);
        mAddLecFab = findViewById(R.id.add_lec_fab);
        mAddPlanFab = findViewById(R.id.add_plan_fab);
        // Also register the action name text, of all the FABs.
        addAssText = findViewById(R.id.add_ass_text);
        addExamText = findViewById(R.id.add_exam_text);
        addLecText = findViewById(R.id.add_lec_text);
        addPlanText = findViewById(R.id.add_plan_text);

        // Now set all the FABs and all the action name
        // texts as GONE
        mAddAssFab.setVisibility(View.GONE);
        mAddExamFab.setVisibility(View.GONE);
        mAddLecFab.setVisibility(View.GONE);
        mAddPlanFab.setVisibility(View.GONE);
        addAssText.setVisibility(View.GONE);
        addExamText.setVisibility(View.GONE);
        addLecText.setVisibility(View.GONE);
        addPlanText.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are invisible
        isAllFabsVisible = false;

        setSupportActionBar(binding.appBarMain.toolbar);
        Log.d("MainActivity.java", "onClick: HI");
        //assert binding.appBarMain.addFab!= null;
        assert mAddFab!= null;
        mAddFab.bringToFront();
//        mAddFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("MainActivity.java", "onClick: HI");
//                if (!isAllFabsVisible) {
//                    Log.d("MainActivity.java", "onClick: HI");
//            // when isAllFabsVisible becomes
//            // true make all the action name
//            // texts and FABs VISIBLE.
//                mAddAssFab.show();
//                mAddExamFab.show();
//                mAddLecFab.show();
//                mAddPlanFab.show();
//                addAssText.setVisibility(View.VISIBLE);
//                addPlanText.setVisibility(View.VISIBLE);
//                addLecText.setVisibility(View.VISIBLE);
//                addExamText.setVisibility(View.VISIBLE);
//
//            // make the boolean variable true as
//            // we have set the sub FABs
//            // visibility to GONE
//                isAllFabsVisible = true;
//        } else {
//
//            // when isAllFabsVisible becomes
//            // true make all the action name
//            // texts and FABs GONE.
//            mAddAssFab.hide();
//            mAddExamFab.hide();
//            mAddLecFab.hide();
//            mAddPlanFab.hide();
//            addLecText.setVisibility(View.GONE);
//            addPlanText.setVisibility(View.GONE);
//            addAssText.setVisibility(View.GONE);
//            addExamText.setVisibility(View.GONE);
//
//            // make the boolean variable false
//            // as we have set the sub FABs
//            // visibility to GONE
//            isAllFabsVisible = false;
//        }
//            }
//
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        viewPager = findViewById(R.id.mypager);
        pagerAdapter = new MyPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        //inflating tab layout
        TabLayout tabLayout =( TabLayout) findViewById(R.id.tab_layout);
//displaying tabs
        new TabLayoutMediator(tabLayout, viewPager,(tab, position) -> tab.setText(titles[position])).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private class MyPagerAdapter extends FragmentStateAdapter {

        public MyPagerAdapter(FragmentActivity fa) {
            super(fa);
        }


        @Override
        public Fragment createFragment(int pos) {
            switch (pos) {
                case 0: {
                    return FirstFragment.newInstance("fragment 1");
                }
                case 1: {

                    return SecondFragment.newInstance("fragment 2");
                }
                case 2: {
                    return ThirdFragment.newInstance("fragment 3");
                }
                case 3: {
                    return FourthFragment.newInstance("fragment 4");
                }
                default:
                    return FirstFragment.newInstance("fragment 1, Default");
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
// If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.d
            super.onBackPressed();
        } else {
// Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public void onClickFAB(View view) {
        Log.d("MainActivity.java", "onClick: HI");
        if (!isAllFabsVisible) {
            Log.d("MainActivity.java", "onClick: HI");
            // when isAllFabsVisible becomes
            // true make all the action name
            // texts and FABs VISIBLE.
            mAddAssFab.show();
            mAddExamFab.show();
            mAddLecFab.show();
            mAddPlanFab.show();
            addAssText.setVisibility(View.VISIBLE);
            addPlanText.setVisibility(View.VISIBLE);
            addLecText.setVisibility(View.VISIBLE);
            addExamText.setVisibility(View.VISIBLE);

            // make the boolean variable true as
            // we have set the sub FABs
            // visibility to GONE
            isAllFabsVisible = true;
        } else {

            // when isAllFabsVisible becomes
            // true make all the action name
            // texts and FABs GONE.
            mAddAssFab.hide();
            mAddExamFab.hide();
            mAddLecFab.hide();
            mAddPlanFab.hide();
            addLecText.setVisibility(View.GONE);
            addPlanText.setVisibility(View.GONE);
            addAssText.setVisibility(View.GONE);
            addExamText.setVisibility(View.GONE);

            // make the boolean variable false
            // as we have set the sub FABs
            // visibility to GONE
            isAllFabsVisible = false;
        }
    }

}