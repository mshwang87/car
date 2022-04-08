package socar;

import socar.config.kafka.KafkaProcessor;

import java.util.Optional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired CarRepository carRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationConfirmed_ConfirmReserve(@Payload ReservationConfirmed reservationConfirmed){

        if(!reservationConfirmed.validate()) return;

        System.out.println("\n\n##### 예약 확인 : " + reservationConfirmed.toJson() + "\n\n");


        long carId = reservationConfirmed.getCarId(); // 삭제된 리뷰의 carId

        updateRoomStatus(carId, "reserved", "reserved"); // Status Update
        

        // Sample Logic //
        // Car car = new Car();
        // carRepository.save(car);

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationCancelled_Cancel(@Payload ReservationCancelled reservationCancelled){

        if(!reservationCancelled.validate()) return;

        System.out.println("\n\n##### 예약 취소 : " + reservationCancelled.toJson() + "\n\n");

        long carId = reservationCancelled.getCarId(); // 예약취소한  CarID

        updateRoomStatus(carId, "available", "cancelled"); // Status Update

    }

    private void updateRoomStatus(long carId, String status, String carType)     {

        //////////////////////////////////////////////
        // carId 룸 데이터의 status, lastAction 수정
        //////////////////////////////////////////////

        // Car 테이블에서 carId의 Data 조회 -> car
        Optional<Car> res = carRepository.findById(carId);
        Car car = res.get();

        System.out.println("carId      : " + car.getCarId());
        System.out.println("status      : " + car.getStatus());
        System.out.println("carType  : " + car.getCarType());

        // room 값 수정
        car.setStatus(status); // status 수정 
        car.setCarType(carType);  // lastAction 값 셋팅

        System.out.println("Edited status     : " + car.getStatus());
        System.out.println("Edited carType : " + car.getCarType());

        /////////////
        // DB Update
        /////////////
        carRepository.save(car);

    }


}


