package socar;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;


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
        // 차량 등록 
        // 초기값 세팅 
        status = "available";    // 최초 등록시 항상 이용가능
        carType = "register";    

        CarRegistered carRegistered = new CarRegistered();
        BeanUtils.copyProperties(this, carRegistered);
        carRegistered.publishAfterCommit();

    }

    @PostUpdate
    public void onPostUpdate(){
        System.out.println("carType : " + carType);
        // 차량 정보 수정 
        if(carType.equals("modify")) {
            CarModified carModified = new CarModified();
            BeanUtils.copyProperties(this, carModified);
            carModified.publishAfterCommit();
        }

        // 차량 예약 
        if(carType.equals("reserved")) {
            CarReserved carReserved = new CarReserved();
            BeanUtils.copyProperties(this, carReserved);
            carReserved.publishAfterCommit();
        }

        // 차량 예약취소  
        if(carType.equals("cancelled")) {
            CarCancelled carCancelled = new CarCancelled();
            BeanUtils.copyProperties(this, carCancelled);
            carCancelled.publishAfterCommit();
        }

    }
    @PrePersist
    public void onPrePersist(){
    }
    @PreUpdate
    public void onPreUpdate(){
    }
    @PreRemove
    public void onPreRemove(){
        // 차량 정보 삭제 
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
