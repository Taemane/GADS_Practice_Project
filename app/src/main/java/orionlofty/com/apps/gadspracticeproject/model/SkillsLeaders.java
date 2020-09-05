package orionlofty.com.apps.gadspracticeproject.model;

public class SkillsLeaders {
    private String name;
    private String score;
    private String country;
    private String badgeUrl;

    public SkillsLeaders() {
    }

    public SkillsLeaders(String name, String score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "SkillsLeaders{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
