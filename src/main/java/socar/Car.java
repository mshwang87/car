package socar;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;


@Entity
@Table(name="Car_table")
public class Car  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long carId;

    private String status;

    private String carName;

    private Long amount;

    private String carType;


    @PostPersist
    public void onPostPersist(){
        CarRegistered carRegistered = new CarRegistered();
        BeanUtils.copyProperties(this, carRegistered);
        carRegistered.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){
        CarModified carModified = new CarModified();
        BeanUtils.copyProperties(this, carModified);
        carModified.publishAfterCommit();

        CarReserved carReserved = new CarReserved();
        BeanUtils.copyProperties(this, carReserved);
        carReserved.publishAfterCommit();

        CarCancelled carCancelled = new CarCancelled();
        BeanUtils.copyProperties(this, carCancelled);
        carCancelled.publishAfterCommit();

    }
    @PrePersist
    public void onPrePersist(){
    }
    @PreUpdate
    public void onPreUpdate(){
    }
    @PreRemove
    public void onPreRemove(){
        CarDeleted carDeleted = new CarDeleted();
        BeanUtils.copyProperties(this, carDeleted);
        carDeleted.publishAfterCommit();

    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
    
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
    
    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
    



}
