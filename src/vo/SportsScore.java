package vo;


public class SportsScore {

    private String id;
    private String project;
    private String score;
    private String ranking;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getproject() {
        return project;
    }

    public String getscore() {
        return score;
    }

    public String getranking() {
        return ranking;
    }

    public void setproject(String project) {
        this.project = project;
    }

    public void setscore(String score) {
        this.score = score;
    }

    public void setranking(String ranking) {
        this.ranking = ranking;
    }
}

