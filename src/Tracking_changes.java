import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
Отслеживаем изменения
*/

public class Tracking_changes {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name1 = reader.readLine();
        String name2 = reader.readLine();
        reader.close();

        FileReader fileReader_1 = new FileReader(name1);
        FileReader fileReader_2 = new FileReader(name2);

        ArrayList<String> arr_1 = new ArrayList<>();
        ArrayList<String> arr_2 = new ArrayList<>();

        BufferedReader fReader_1 = new BufferedReader(fileReader_1);
        BufferedReader fReader_2 = new BufferedReader(fileReader_2);

        String line;
        while ((line = fReader_1.readLine()) != null) {
            arr_1.add(line);
        }
        while ((line = fReader_2.readLine()) != null) {
            arr_2.add(line);
        }

        int first_file_line = 0;
        int second_file_line = 0;
        while ((first_file_line < arr_1.size()) && (second_file_line < arr_2.size())) {
            if (arr_1.get(first_file_line).equals(arr_2.get(second_file_line))) {
                lines.add(new LineItem(Type.SAME, arr_1.get(first_file_line)));
                first_file_line++;
                second_file_line++;
            } else if (arr_1.get(first_file_line).equals(arr_2.get(second_file_line + 1))) {
                lines.add(new LineItem(Type.ADDED, arr_2.get(second_file_line)));
                second_file_line++;
            } else if (arr_1.get(first_file_line + 1).equals(arr_2.get(second_file_line))) {
                lines.add(new LineItem(Type.REMOVED, arr_1.get(first_file_line)));
                first_file_line++;
            }
        }

        while (first_file_line < (arr_1.size())) {
            lines.add(new LineItem(Type.REMOVED, arr_1.get(first_file_line)));
            first_file_line++;
        }
        while (second_file_line < (arr_2.size())) {
            lines.add(new LineItem(Type.ADDED, arr_2.get(second_file_line)));
            second_file_line++;
        }

        fileReader_1.close();
        fileReader_2.close();
        fReader_1.close();
        fReader_2.close();
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
