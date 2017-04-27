public class Bot {

    private String botN;
    private double botT;

    public String getBotN() {
        return botN;
    }

    public void setBotN(String botN) {
        this.botN = botN;
    }

    public double getBotT() {
        return botT;
    }

    public void setBotT(double botT) {
        this.botT = botT;
    }

    public Bot(String botN, double botT) {
        this.botN = botN;
        this.botT = botT;
    }
}
