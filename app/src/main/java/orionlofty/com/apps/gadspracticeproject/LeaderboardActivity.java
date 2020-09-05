package orionlofty.com.apps.gadspracticeproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import orionlofty.com.apps.gadspracticeproject.adapters.MyViewPagerAdapter;

public class LeaderboardActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView buttonSubmit;

    private LearningLeadersFragment learningLeadersFragment;
    private SkillIQLeadersFragment skillIQLeadersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        buttonSubmit = findViewById(R.id.button_to_submit);
        viewPager = findViewById(R.id.viewpager_container);

        setupViewPager();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSubmitIntent = new Intent(LeaderboardActivity.this, ProjectSubmissionActivity.class);
                startActivity(toSubmitIntent);
            }
        });
    }

    //Setting up View Pager
    private void setupViewPager(){
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        learningLeadersFragment = new LearningLeadersFragment();
        skillIQLeadersFragment = new SkillIQLeadersFragment();

        adapter.addFragment(learningLeadersFragment);
        adapter.addFragment(skillIQLeadersFragment);

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Learning Leaders");
        tabLayout.getTabAt(1).setText("Skill IQ Leaders");
    }
}