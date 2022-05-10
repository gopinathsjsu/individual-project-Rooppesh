package factory;

import dao.Parser;

public interface Factory {
    public Parser getInstance(String type);
}
