package pl.pjatk.kprusak.service;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;
import pl.pjatk.kprusak.exception.BadGatewayException;
import pl.pjatk.kprusak.exception.BadRequestException;
import pl.pjatk.kprusak.exception.GatewayTimeoutException;
import pl.pjatk.kprusak.exception.MovieNotFoundException;
import pl.pjatk.kprusak.model.Movie;

@Service
public class MovieServiceClient {

    private final RestTemplate restTemplate;
    private final String movieServiceUrl = "http://localhost:8080/api/v2/movies";

    public MovieServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Testing Errors

    public Object badRequestException() {
        String url = movieServiceUrl + "/400";
        try {
            return restTemplate.getForObject(url, Object.class);
        } catch (HttpClientErrorException ex) {
            throw new BadRequestException("Bad Request Exception - RentalService");
        } catch (ResourceAccessException ex) {
            throw new GatewayTimeoutException("Gateway Timeout Exception - RentalService");
        }
    }

    public Object notFoundException() {
        String url = movieServiceUrl + "/404";
        try {
            return restTemplate.getForObject(url, Object.class);
        } catch (HttpClientErrorException ex) {
            throw new MovieNotFoundException("Not Found Exception - RentalService");
        } catch (ResourceAccessException ex) {
            throw new GatewayTimeoutException("Gateway Timeout Exception - RentalService");
        }
    }

    public Object internalServerErrorException() {
        String url = movieServiceUrl + "/500";
        try {
            return restTemplate.getForObject(url, Object.class);
        } catch (HttpServerErrorException ex) {
            throw new BadGatewayException("Bad Gateway Exception - RentalService");
        } catch (ResourceAccessException ex) {
            throw new GatewayTimeoutException("Gateway Timeout Exception - RentalService");
        }
    }

    //

    public Movie getMovie(Integer id) {
        String url = movieServiceUrl + "/find/" + id;
        try {
            return restTemplate.getForEntity(url, Movie.class).getBody();
        } catch (HttpClientErrorException ex) {
            throw new MovieNotFoundException("Movie with ID: " + id + " not found");
        }
    }

    public Movie returnMovie(Integer id) {
        String url = movieServiceUrl + "/available/" + id;
        try {
            return restTemplate.exchange(url, HttpMethod.PATCH, null, Movie.class).getBody();
        } catch (HttpClientErrorException ex) {
            throw new MovieNotFoundException("Movie with ID: " + id + " not found");
        }
    }

    public Movie rentMovie(Integer id) {
        String url = movieServiceUrl + "/unavailable/" + id;
        try {
            return restTemplate.exchange(url, HttpMethod.PATCH, null, Movie.class).getBody();
        } catch (HttpClientErrorException ex) {
            throw new MovieNotFoundException("Movie with ID: " + id + " not found");
        }
    }
}
