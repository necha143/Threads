import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Bytes {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name;
        while (!(name = reader.readLine()).equals("exit")) {
            new ReadThread(name).start();
        }
        reader.close();
    }

    public static class ReadThread extends Thread {
        private String name;

        public ReadThread(String fileName) {
            this.name = fileName;
        }

        @Override
        public void run() {
            int[] buf = new int[1000];
            try {
                FileInputStream inputStream = new FileInputStream(name);
                while (inputStream.available() > 0) {
                    buf[inputStream.read()]++;
                }
                inputStream.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int maxCount = 0;
            int maxCountByte = 0;
            for (int i = 0; i < buf.length; i++) {
                if (buf[i] > maxCount) {
                    maxCount = buf[i];
                    maxCountByte = i;
                }
            }

            resultMap.put(name, maxCountByte);
        }
    }
}