package socar;


public class CarReserved extends AbstractEvent {

    private Long carId;
    private String status;
    private String amount;
    private String carName;
    private String carType;

    public CarReserved(){
        super();
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long CarId) {
        this.carId = carId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = status;
    }
    public String getAmount() {
        return amount;
    }

    public void setAmount(String Amount) {
        this.amount = amount;
    }
    public String getCarName() {
        return carName;
    }

    public void setCarName(String CarName) {
        this.carName = carName;
    }
    public String getCarType() {
        return carType;
    }

    public void setCarType(String CarType) {
        this.carType = carType;
    }
}
