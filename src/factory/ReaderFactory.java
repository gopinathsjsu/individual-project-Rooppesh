package factory;

import dao.CardParser;
import dao.Parser;
import dao.ItemParser;

public class ReaderFactory implements Factory{

    public Parser getInstance(String type){
        if (type.equals("item")){
            return new ItemParser();
        }else if(type.equals("card")){
            return new CardParser();
        }else {
            throw new RuntimeException("Not Found!");
        }
    }
}
