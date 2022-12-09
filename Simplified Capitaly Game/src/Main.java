import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, InvalidInputException, NoSuchElementException {
        Database data = new Database();

        try {
            data.read("input.txt");
        } catch (IOException ex) {
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.exit(-1);
        } catch (NoSuchElementException ex) {
            System.exit(-1);
        }

        data.report();
        data.clear();
    }
}
