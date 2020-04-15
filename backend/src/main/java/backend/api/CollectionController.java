package backend.api;

import backend.model.Collection;
import backend.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("collection")
public class CollectionController {
    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping()
    public void addCollection(@RequestBody Collection collection) {
        System.out.println(collection.getName() + collection.getUserId() + collection.getSize());
        collectionService.addCollection(collection);
    }

    @GetMapping("/userId/{userId}")
    public ArrayList<Collection> getCollections(@PathVariable int userId) {
        ArrayList<Collection> collections = collectionService.getCollections(userId);
        for (Collection collection : collections) {
            System.out.println(collection.getName());
        }
        return collections;
    }

    @DeleteMapping("/id/{id}")
    public void removeCollection(@PathVariable int id) {
        collectionService.removeCollection(id);
    }
}
