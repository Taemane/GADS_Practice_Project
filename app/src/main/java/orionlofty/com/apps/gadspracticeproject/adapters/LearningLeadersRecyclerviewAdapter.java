package orionlofty.com.apps.gadspracticeproject.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import orionlofty.com.apps.gadspracticeproject.model.LearningLeaders;
import orionlofty.com.apps.gadspracticeproject.R;

public class LearningLeadersRecyclerviewAdapter extends RecyclerView.Adapter<LearningLeadersRecyclerviewAdapter
        .LearningLeadersViewHolder> {
    private static final String TAG = "LearningLeadersRecycler";
    private List<LearningLeaders> mLearningLeaders;
    Context context;

    public LearningLeadersRecyclerviewAdapter(Context context, List<LearningLeaders> mLearningLeaders) {
        this.mLearningLeaders = mLearningLeaders;
        this.context = context;
    }

    @NonNull
    @Override
    public LearningLeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learning_leaders_row, parent, false);
        LearningLeadersViewHolder llvh = new LearningLeadersViewHolder(view);
        return llvh;
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLeadersViewHolder holder, int position) {

        holder.learnerName.setText(mLearningLeaders.get(position).getName());
        holder.learnerHours.setText(mLearningLeaders.get(position).getHours());
        holder.learnerCountry.setText(mLearningLeaders.get(position).getCountry());
        Picasso.get().load(mLearningLeaders.get(position).getBadgeUrl())
                .into(holder.learnerBadge);
    }

    @Override
    public int getItemCount() {
        return mLearningLeaders.size();
    }

    public static class LearningLeadersViewHolder extends RecyclerView.ViewHolder {

        //things that are going to appear on Recyclerview row
        private TextView learnerName;
        private TextView learnerHours;
        private TextView learnerCountry;
        private ImageView learnerBadge;

        public LearningLeadersViewHolder(@NonNull View itemView) {
            super(itemView);
            learnerName = itemView.findViewById(R.id.top_learning_learner_name);
            learnerHours = itemView.findViewById(R.id.top_learning_learner_hours);
            learnerCountry = itemView.findViewById(R.id.top_learning_learner_country);
            learnerBadge = itemView.findViewById(R.id.top_learning_learner_badge);
        }
    }
}
