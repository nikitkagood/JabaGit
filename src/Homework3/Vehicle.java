package Homework3;

public class Vehicle
{
    private String vehicleName = "default name";
    private String frameInfo = "default";
    private int wheels = 0;
    private String engineInfo = "default";

    public Vehicle() {}

    public Vehicle(String vehicleName)
    {
        this.vehicleName = vehicleName;
    }

    public String getVehicleName() { return vehicleName; }

    public String getFrameInfo() { return frameInfo; }

    public int getWheels() { return wheels; }

    public String getEngineInfo() {return engineInfo; }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setFrameInfo(String frameInfo) {
        this.frameInfo = frameInfo;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public void setEngineInfo(String engineInfo) {
        this.engineInfo = engineInfo;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleName='" + vehicleName + '\'' +
                ", frameInfo='" + frameInfo + '\'' +
                ", wheels=" + wheels +
                ", engineInfo='" + engineInfo + '\'' +
                '}';
    }
}
