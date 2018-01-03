package mpsp.homegrown;

/**
 * Created by rokso on 28. 12. 2017.
 */

public class Badge {
    private int id;
    private String name;
    private int amount;
    private int progress;
    private String description;
    private String reward;


    public Badge(int id, int progress){
        this.id = id;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getProgress() {
        return progress;
    }

    public String getDescription() {
        return description;
    }

    public String getReward() {
        return reward;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
