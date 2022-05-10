package dao;

import model.StockItem;
import builder.StockItemBuilder;
import repository.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ItemParser implements Parser {
    private final Repository repository = Repository.getInstance();

    @Override
    public void parseFile(String filePath) {
        String currentLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            bufferedReader.readLine();
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] itemData = currentLine.split(",");
                String itemName = itemData[0].toLowerCase();

                StockItem stockItem = new StockItemBuilder(itemData[1],itemName,Double.parseDouble(itemData[3]),Integer.parseInt(itemData[2])).build();

                repository.getItems().put(itemName, stockItem);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
