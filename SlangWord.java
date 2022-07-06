import java.io.Serializable;

public class SlangWord {
    private String Slag;
    private String Mean;

    public String getSlag() {
        return Slag;
    }

    public void setSlag(String slag) {
        Slag = slag;
    }

    public String getMean() {
        return Mean;
    }

    public void setMean(String mean) {
        Mean = mean;
    }

    public SlangWord(String slag, String mean) {
        Slag = slag;
        Mean = mean;
    }
}
