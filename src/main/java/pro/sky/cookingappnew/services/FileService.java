package pro.sky.cookingappnew.services;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    boolean saveToFile1(String json);

    String readFromFile1();

    boolean cleanDataFile1();
}
