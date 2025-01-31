package frontend.manager;

import frontend.App;
import frontend.model.Collection;
import frontend.model.Item;
import frontend.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;


public class CollectionManager {
    private ItemManager itemManager = App.getItemManager();
    private SceneManager sceneManager = App.getSceneManager();

    // request na ziskanie zoznamu vsetkych kolekcii
    public ArrayList<Collection> getAllCollections(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/collection/userId/{userId}";


        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ResponseEntity<Collection[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Collection[].class, id);

        return new ArrayList<>(Arrays.asList(responseEntity.getBody()));
    }

    // request na vytvorenie kolekcie
    public void createCollection(Collection collection) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/collection";

        HttpEntity<Collection> requestEntity = new HttpEntity<Collection>(collection, headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
    }

    //request na vymazanie kolekcie
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

    // request na vymazanie kolekcie
    public void removeCollection(Collection collection) {
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

    // vymazanie vsetkych kolekcii pouzivatela
    public void removeAllCollections(User user) {
        ArrayList<Collection> collections = App.getCollectionManager().getAllCollections(user.getId());

        for(Collection collection : collections) {
            this.removeCollection(collection);
        }
    }

    // request na upravenie kolekcie
    public void updateCollection(String name) {
        Collection collection = App.getCollection();

        if(!name.equals("") && !name.equals(collection.getName())) {
            collection.setName(name);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = new RestTemplate();

            String url = "http://localhost:8080/collection/update";

            HttpEntity<Collection> requestEntity = new HttpEntity<Collection>(collection, headers);

            ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
        }
    }
}
