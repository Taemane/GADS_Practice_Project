package orionlofty.com.apps.gadspracticeproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import orionlofty.com.apps.gadspracticeproject.model.SkillsLeaders;
import orionlofty.com.apps.gadspracticeproject.R;

public class SkillLeadersRecyclerviewAdapter extends RecyclerView.Adapter<SkillLeadersRecyclerviewAdapter
        .SkillLeaderViewHolder> {

    private static final String TAG = "SkillLeadersRecyclerview";

    private List<SkillsLeaders> mSkillLeaders;
    Context context;

    public SkillLeadersRecyclerviewAdapter(Context context, List<SkillsLeaders> mSkillLeaders) {
        this.mSkillLeaders = mSkillLeaders;
        this.context = context;
    }

    @NonNull
    @Override
    public SkillLeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_iq_leaders_row, parent, false);
        SkillLeaderViewHolder slvh = new SkillLeaderViewHolder(view);
        return slvh;
    }

    @Override
    public void onBindViewHolder(@NonNull SkillLeaderViewHolder holder, int position) {

        holder.learnerName.setText(mSkillLeaders.get(position).getName());
        holder.learnerScore.setText(mSkillLeaders.get(position).getScore());
        holder.learnerCountry.setText(mSkillLeaders.get(position).getCountry());
        Picasso.get().load(mSkillLeaders.get(position).getBadgeUrl())
                .into(holder.learnerBadge);
    }

    @Override
    public int getItemCount() {
        return mSkillLeaders.size();
    }

    public static class SkillLeaderViewHolder extends RecyclerView.ViewHolder {

        //things that are going to appear on Recyclerview row
        private TextView learnerName;
        private TextView learnerScore;
        private TextView learnerCountry;
        private ImageView learnerBadge;

        public SkillLeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            learnerName = itemView.findViewById(R.id.top_skill_learner_name);
            learnerScore = itemView.findViewById(R.id.top_skills_learner_score);
            learnerCountry = itemView.findViewById(R.id.top_skill_learner_country);
            learnerBadge = itemView.findViewById(R.id.top_skill_learner_badge);
        }
    }
}
