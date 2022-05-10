import config.ConfigHelper;
import dao.Parser;
import factory.Factory;
import factory.ReaderFactory;
import helper.OrderHelper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Billing {

    private static final ConfigHelper configHelper = ConfigHelper.getInstance();

    public static void main(String[] args) throws IOException {
        Factory readerFactory = new ReaderFactory();

        Parser itemDataParser = readerFactory.getInstance("item");
        itemDataParser.parseFile(configHelper.getStr("baseRepoPath") + configHelper.getStr("itemFilePath"));

        Parser cardDataParser = readerFactory.getInstance("card");
        cardDataParser.parseFile(configHelper.getStr("baseRepoPath") + configHelper.getStr("cardFilePath"));

        Scanner in = new Scanner(System.in);
        System.out.println("Enter Input CSV Name");
        String inputFile = in.nextLine();

        processOrder(configHelper.getStr("baseRepoPath") + configHelper.getStr("inputFilePrefix") + inputFile + ".csv", inputFile);
    }

    public static void processOrder(String filePath, String inputFile) throws IOException {
        String cardNumber = "";
        HashMap<String, Integer> order = new LinkedHashMap<>();

        Scanner scanner = new Scanner(new File(filePath));
        scanner.nextLine();

        while (scanner.hasNext()){
            String orderRecord = scanner.nextLine();
            String[] orderItem = orderRecord.split(",");
            order.put(orderItem[0].toLowerCase(), order.getOrDefault(orderItem[0].toLowerCase(), 0) + Integer.parseInt(orderItem[1]));
            cardNumber = (orderItem.length > 2) ? orderItem[2] : cardNumber;
        }

        OrderHelper helper = new OrderHelper();
        helper.validateOrder(order);
        helper.placeFinalOrder(cardNumber, order, inputFile);
    }
}
