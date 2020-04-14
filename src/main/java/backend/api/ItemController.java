package backend.api;

import backend.model.Item;
import backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    public void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    @GetMapping("/collId/{collectionId}")
    public void getItem(@PathVariable int collectionId) {
        ArrayList<Item> items = itemService.getItems(collectionId);

        for(Item item : items) {
            System.out.println(item.getName());
        }
    }

    @DeleteMapping("id/{id}")
    public void removeItem(@PathVariable int id) {
        itemService.removeItem(id);
    }
}
