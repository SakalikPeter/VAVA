package frontend.manager;

import frontend.App;
import frontend.model.Collection;
import frontend.model.Item;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;


public class CollectionManager {
    private ItemManager itemManager = App.getItemManager();
    private SceneManager sceneManager = App.getSceneManager();

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

    public void removeCollection() {
        Collection collection = App.getCollection();
        
        ArrayList<Item> items = App.getItemManager().getAllItems(collection.getId());

        if(items.size() > 0) {
            App.getItemManager().removeAllItems(items);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/collection/id/{id}";

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, collection.getId());
    }



}
