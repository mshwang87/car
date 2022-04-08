package socar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

 @RestController
 public class CarController {

        @Autowired
        CarRepository carRepository;

@RequestMapping(value = "/check/chkAndReqReserve",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")

public boolean chkAndReqReserve(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
                System.out.println("##### /check/chkAndReqReserve  called #####");

                // Parameter로 받은 CarID 추출
                long carId = Long.valueOf(request.getParameter("carId"));
                System.out.println("######################## chkAndReqReserve carId : " + carId);

                // CarId 데이터 조회
                Optional<Car> res = carRepository.findById(carId);
                Car car = res.get(); // 조회한 car 데이터
                System.out.println("######################## chkAndReqReserve car.getStatus() : " + car.getStatus());

                // car의 상태가 available이면 true
                boolean result = false;
                if(car.getStatus().equals("available")) {
                        result = true;
                } 

                System.out.println("######################## chkAndReqReserve Return : " + result);
                return result;
        }
 }