import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
Транзакционность
*/

public class Transactionality {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public void joinData() throws IOException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new IOException();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String way1 = reader.readLine();
        String way2 = reader.readLine();
        reader.close();

        BufferedReader reader1 = new BufferedReader(new FileReader(way1));

        String input;
        while ((input = reader1.readLine()) != null) {
            allLines.add(input);
        }
        reader1.close();

        BufferedReader reader2 = new BufferedReader(new FileReader(way2));

        String inputt;
        while ((inputt = reader2.readLine()) != null) {
            forRemoveLines.add(inputt);
        }
        reader2.close();

        new Transactionality().joinData();
    }
}