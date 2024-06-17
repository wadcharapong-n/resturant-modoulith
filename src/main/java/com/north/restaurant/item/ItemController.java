package com.north.restaurant.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {

    final ItermService itermService;

    @GetMapping
    public List<Item> getItems() {
        return itermService.getAllItems();
    }

    @GetMapping("/{itemId}")
    public Optional<Item> getItems(@PathVariable Long itemId) {
        return itermService.getItemById(itemId);
    }

    @PostMapping
    public void addItem(@RequestBody Item item) {
        itermService.addItem(item);
    }

    @PutMapping
    public void updateItem(@RequestBody Item item) {
        itermService.updateItem(item);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        itermService.deleteItem(itemId);
    }
}
