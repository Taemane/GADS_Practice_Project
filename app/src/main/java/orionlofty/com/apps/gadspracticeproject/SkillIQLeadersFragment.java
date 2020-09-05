package orionlofty.com.apps.gadspracticeproject;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import orionlofty.com.apps.gadspracticeproject.adapters.SkillLeadersRecyclerviewAdapter;
import orionlofty.com.apps.gadspracticeproject.model.SkillsLeaders;
import orionlofty.com.apps.gadspracticeproject.services.LeadersService;
import orionlofty.com.apps.gadspracticeproject.services.ServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQLeadersFragment extends Fragment {

    private static final String TAG = "SkillIQLeadersFragment";

    private RecyclerView recyclerView;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill_leaders, container, false);
        recyclerView = view.findViewById(R.id.skill_iq_rv);
        initRecyclerView();
        setupRetrofit();
        return view;
    }

    private void initRecyclerView(){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setupRetrofit(){
        final LeadersService leadersService = ServiceBuilder.buildService(LeadersService.class);
        Call<List<SkillsLeaders>> call = leadersService.getSkillsLeaders();

        call.enqueue(new Callback<List<SkillsLeaders>>() {
            @Override
            public void onResponse(Call<List<SkillsLeaders>> call, Response<List<SkillsLeaders>> response) {
                Log.d(TAG, "onResponse: Server response "+ response.toString());
                recyclerView.setAdapter(new SkillLeadersRecyclerviewAdapter(context, response.body()));
            }

            @Override
            public void onFailure(Call<List<SkillsLeaders>> call, Throwable t) {
                Log.d(TAG, "onFailure: Unable to retrieve");
                Toast.makeText(getContext(), "Unable to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}