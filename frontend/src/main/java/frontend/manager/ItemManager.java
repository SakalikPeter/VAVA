package frontend.manager;

import frontend.model.Item;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemManager {

    public ArrayList<Item> getAllItems(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/item/collId/{collectionId}";

        System.out.println("ja som id: " + id);

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ResponseEntity<Item[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Item[].class, id);

        ArrayList<Item> items = new ArrayList<>(Arrays.asList(responseEntity.getBody()));

        for(Item item : items){
            System.out.println(item.getName());
        }

//        if(item.length == 0){
//            System.out.println(item.length);
//            return new ArrayList<>();
//        }

        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }

    public void addItem(Item item) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/item";

        HttpEntity<Item> requestEntity = new HttpEntity<Item>(item, headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
    }

    public void removeItem(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/item/id/{id}";

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, id);
    }

    public void removeAllItems(ArrayList<Item> items) {
        for(Item item : items) {
            this.removeItem(item.getId());
        }
    }
}
