package orionlofty.com.apps.gadspracticeproject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import orionlofty.com.apps.gadspracticeproject.adapters.LearningLeadersRecyclerviewAdapter;
import orionlofty.com.apps.gadspracticeproject.model.LearningLeaders;
import orionlofty.com.apps.gadspracticeproject.services.LeadersService;
import orionlofty.com.apps.gadspracticeproject.services.ServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersFragment extends Fragment{

    private static final String TAG = "LearningLeadersFragment";

    private RecyclerView recyclerView;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        recyclerView = view.findViewById(R.id.learning_leaders_rv);
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
        LeadersService leadersService = ServiceBuilder.buildService(LeadersService.class);
        Call<List<LearningLeaders>> call = leadersService.getLearningLeaders();

        call.enqueue(new Callback<List<LearningLeaders>>() {
            @Override
            public void onResponse(Call<List<LearningLeaders>> call, Response<List<LearningLeaders>> response) {
                Log.d(TAG, "onResponse: Server response "+ response.toString());
                recyclerView.setAdapter(new LearningLeadersRecyclerviewAdapter(context, response.body()));
            }

            @Override
            public void onFailure(Call<List<LearningLeaders>> call, Throwable t) {
                Log.d(TAG, "onFailure: Unable to retrieve");
                Toast.makeText(getContext(), "Unable to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}