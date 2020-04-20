package frontend.manager;

import frontend.App;
import frontend.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class UserManager {

    public void createUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/user";

        HttpEntity<User> requestEntity = new HttpEntity<User>(user, headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
    }

    public User getUser(String name, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/user/{name}/{password}";

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User.class, name, password);

        return responseEntity.getBody();
    }

    public void removeUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/user/id/{id}";

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, user.getId());
    }

    public void updateUser(String name, String newPassword) {
        User user = App.getActivUser();
        User updatedUser = user;
        boolean changed = false;

        if(!name.equals(user.getUserName())) {
            updatedUser.setUserName(name);
            changed = true;
        }
        if(!newPassword.equals("")) {
            updatedUser.setPassword(newPassword);
            changed = true;
        }

        if(changed) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = new RestTemplate();

            String url = "http://localhost:8080/user/update";

            HttpEntity<User> requestEntity = new HttpEntity<User>(updatedUser, headers);

            ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);

            App.setActivUser(updatedUser);
        }
    }
}
