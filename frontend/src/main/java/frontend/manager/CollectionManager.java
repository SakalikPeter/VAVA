package frontend.manager;

import frontend.model.Collection;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;


public class CollectionManager {

    public ArrayList<Collection> getAllCollections(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/collection/userId/{userId}";


        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ResponseEntity<Collection[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Collection[].class, id);

        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }

    public void createCollection(Collection collection) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/collection";

        HttpEntity<Collection> requestEntity = new HttpEntity<Collection>(collection, headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
    }

}
