package helper;

import config.ConfigHelper;
import model.StockItem;
import repository.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class OrderHelper {

    private final Repository repository = Repository.getInstance();
    private final ConfigHelper configHelper = ConfigHelper.getInstance();

    public void validateOrder(HashMap<String, Integer> order){
        for (Map.Entry<String, Integer> record : order.entrySet()){
            String itemName = record.getKey();
            int quantity = record.getValue();
            StockItem stockItem = repository.getItem(itemName);
            validateStock(quantity, stockItem);
            validateLimits(quantity, stockItem);
        }
    }

    private void validateLimits(int quantity, StockItem stockItem){
        String category = stockItem.getCategory();
        switch (category){
            case "Essentials":
                if(quantity > configHelper.getInt("essentialItemsCap")) addErrorItem(stockItem);
                else configHelper.setInt("essentialItemsCap", configHelper.getInt("essentialItemsCap") - quantity);
                break;
            case "Luxury":
                if(quantity > configHelper.getInt("luxuryItemsCap")) addErrorItem(stockItem);
                else configHelper.setInt("luxuryItemsCap", configHelper.getInt("luxuryItemsCap") - quantity);
                break;
            case "Misc":
                if(quantity > configHelper.getInt("miscItemsCap")) addErrorItem(stockItem);
                else configHelper.setInt("miscItemsCap", configHelper.getInt("miscItemsCap") - quantity);
                break;
            default:
                throw new IllegalStateException("Unexpected Value: " + category);
        }
    }

    private void addErrorItem(StockItem stockItem) {
        String error = stockItem.getName() + " is more than the Cap Limit";
        repository.getErrorMessages().add(error);
    }

    private void validateStock(int requestedQuantity, StockItem stockItem){
        if(stockItem != null && stockItem.getQuantity()<requestedQuantity) {
            String error = stockItem.getName() + " is Out Of Stock";
            repository.getErrorMessages().add(error);
        }
    }

    public void placeFinalOrder(String cardNumber, HashMap<String, Integer> order, String inputFile) throws IOException {
        if (repository.getErrorMessages().size() == 0)
            processValidOrder(cardNumber, order, inputFile);
        else
            processErrors(inputFile);
    }

    private void processErrors(String inputFile) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(configHelper.getStr("baseRepoPath") + configHelper.getStr("outputFilePath")+"error-"+inputFile+".txt", true));
        for (String errorMessage: repository.getErrorMessages()){
            System.out.println(errorMessage);
            bufferedWriter.write(errorMessage);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    private void processValidOrder(String cardNumber, HashMap<String, Integer> order, String inputFile) throws IOException {
        double totalAmount = 0;
        repository.getCards().add(cardNumber);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(configHelper.getStr("baseRepoPath") + configHelper.getStr("outputFilePath")+"output-"+inputFile+".csv"));
        for (Map.Entry<String, Integer> record : order.entrySet()){
            String itemName = record.getKey();
            int requestedQuantity = record.getValue();
            StockItem stockItem = repository.getItem(itemName);
            totalAmount = totalAmount + stockItem.getPrice() * requestedQuantity;
            stockItem.setQuantity(stockItem.getQuantity() - requestedQuantity);
        }
        boolean flag = true;
        bufferedWriter.write("Item" + "," + "Quantity" + "," + "Price" + "," + "TotalPrice" + "\n");
        for (Map.Entry<String, Integer> record : order.entrySet()){
            String itemName = record.getKey();
            int requestedQuantity = record.getValue();
            StockItem stockItem = repository.getItem(itemName);
            System.out.println(itemName+","+requestedQuantity+","+ stockItem.getPrice()*requestedQuantity);
            if (flag) {
                flag = false;
                bufferedWriter.write(itemName + "," + requestedQuantity + "," + stockItem.getPrice() * requestedQuantity + "," + totalAmount + "\n");
            }
            bufferedWriter.write(itemName + "," + requestedQuantity + "," + stockItem.getPrice() * requestedQuantity + "\n");
        }
        System.out.println("Amount Paid: " + totalAmount);
        bufferedWriter.close();
    }
}

