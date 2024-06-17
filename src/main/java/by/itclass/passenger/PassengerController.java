package by.itclass.passenger;

import by.itclass.fligth.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PassengerController {
    private PassengerRepository repository;
    private FlightRepository flightRepository;

    @Autowired
    public void setRepository(PassengerRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @PostMapping("/savePassenger")
    public String savePassenger(
            @RequestParam(name = "passengerId", required = false) Integer passengerId,
            @RequestParam(name = "flightId") int flightId,
            @RequestParam(name = "fio") String fio,
            @RequestParam(name = "place") String place) {
        var passenger = passengerId==null
                ? new Passenger(fio, place, flightRepository.findById(flightId).get())
                : new Passenger(passengerId, fio, place, flightRepository.findById(flightId).get());
        repository.save(passenger);
        return "redirect:/viewFlight/" + flightId;
    }

    @GetMapping("/delPassenger/{flightId}/{id}")
    public String delPassenger(
            @PathVariable(name = "flightId") int flightId,
            @PathVariable(name = "id") int passengerId) {
        //var flightId = repository.findById(passengerId).get().getFlight().getId();
        repository.deleteById(passengerId);
        return "redirect:/viewFlight/" + flightId;
    }

    @GetMapping("/updPassenger/{id}")
    public String updPassenger(
            @PathVariable(name = "id") int passengerId,
            Model model) {
        model.addAttribute("passenger", repository.findById(passengerId).get());
        return "upd-passenger";
    }
}
