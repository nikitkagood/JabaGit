package Homework3;

public class VehicleBuilder {
    private Vehicle vehicle;

    public VehicleBuilder(String vehicleName)
    {
        this.vehicle = new Vehicle(vehicleName);
    }

    public Vehicle getObject()
    {
        return vehicle;
    }

    public void reset(String vehicleName)
    {
        this.vehicle = new Vehicle(vehicleName);
    }

    public void setFrame(String frameInfo)
    {
        vehicle.setFrameInfo(frameInfo);
    }

    public void setWheels(int amount)
    {
        vehicle.setWheels(amount);
    }

    public void setEngine(String engineInfo)
    {
        vehicle.setEngineInfo(engineInfo);
    }


}
