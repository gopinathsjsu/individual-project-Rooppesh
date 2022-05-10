package dao;

import repository.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CardParser implements Parser {
    private final Repository repository = Repository.getInstance();
    @Override
    public void parseFile(String filePath) {
        String cardNumber = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));) {

            bufferedReader.readLine();
            while ((cardNumber = bufferedReader.readLine()) != null)
                repository.getCards().add(cardNumber);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
