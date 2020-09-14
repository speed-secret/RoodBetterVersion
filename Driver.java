package Main;
public class Driver {
    private String name;
    private double sumOfDistance;
    private double averageSpeed;
    private double preTime;

    //@getter && setter
    public void setPreTime(double preTime) {
        this.preTime = preTime;
    }

    public double getPreTime() {
        return this.preTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSumOfDistance(int sumOfDistance) {
        this.sumOfDistance = sumOfDistance;
    }

    public double getSumOfDistance() {
        return sumOfDistance;
    }

    public void setAverageSpeed(int averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public Driver(String name, double sumOfDistance, double averageSpeed, double preTime) {
        this.name = name;
        this.sumOfDistance = sumOfDistance;
        this.averageSpeed = averageSpeed;
        this.preTime = preTime;
    }
}
