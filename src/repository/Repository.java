package repository;

import model.StockItem;

import java.util.*;

public class Repository {
    private static Repository repository;
    private final HashSet<String> cards = new HashSet<>();
    private final HashMap<String, StockItem> items = new HashMap<>();
    private final List<String> errorMessages = new ArrayList<>();

    private Repository() {}

    public static Repository getInstance() {
        if (repository == null) repository = new Repository();
        return repository;
    }

    public HashSet<String> getCards() {
        return this.cards;
    }

    public StockItem getItem(String itemName) {
        return this.items.get(itemName);
    }

    public HashMap<String, StockItem> getItems() {
        return this.items;
    }

    public List<String> getErrorMessages() {
        return this.errorMessages;
    }
}
