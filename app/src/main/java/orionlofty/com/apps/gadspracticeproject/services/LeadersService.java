package orionlofty.com.apps.gadspracticeproject.services;

import java.util.List;
import orionlofty.com.apps.gadspracticeproject.model.LearningLeaders;
import orionlofty.com.apps.gadspracticeproject.model.SkillsLeaders;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LeadersService {
    @GET("api/hours")
    Call<List<LearningLeaders>> getLearningLeaders();

    @GET("api/skilliq")
    Call<List<SkillsLeaders>> getSkillsLeaders();
}
