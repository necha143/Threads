import java.io.*;

/*
Последовательный вывод файлов
*/

public class Files {
    public static String firstFileName;
    public static String secondFileName;

    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            secondFileName = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {
        String fileName;
        String fileContent = "";

        @Override
        public void setFileName(String fullFileName) {
            this.fileName = fullFileName;
        }

        @Override
        public void run() {
            String line;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                while ((line = reader.readLine()) != null) {
                    fileContent += line + " ";
                }
                reader.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String getFileContent() {
            return fileContent;
        }
    }
}