package pl.pjatk.kprusak.controller;

import org.springframework.http.ResponseEntity;
import pl.pjatk.kprusak.service.MovieServiceClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/movies")
public class RentalController {

    private final MovieServiceClient movieServiceClient;

    public RentalController(MovieServiceClient movieServiceClient) {
        this.movieServiceClient = movieServiceClient;
    }

    // Testing Errors

    @GetMapping("/400")
    public ResponseEntity<Object> get400() {
        return ResponseEntity.ok(movieServiceClient.badRequestException());
    }

    @GetMapping("/404")
    public ResponseEntity<Object> get404() {
        return ResponseEntity.ok(movieServiceClient.notFoundException());
    }

    @GetMapping("/500")
    public ResponseEntity<Object> get500() {
        return ResponseEntity.ok(movieServiceClient.internalServerErrorException());
    }

    //

    @GetMapping("/find/{id}")
    public Object getMovie(@PathVariable Integer id) {
        return movieServiceClient.getMovie(id);
    }

    @PatchMapping("/available/{id}")
    public Object returnMovie(@PathVariable Integer id) {
        return movieServiceClient.returnMovie(id);
    }

    @PatchMapping("/unavailable/{id}")
    public Object rentMovie(@PathVariable Integer id) {
        return movieServiceClient.rentMovie(id);
    }
}
