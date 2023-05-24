import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class Component2 {
    private static final String BATCH_FOLDER_PATH = "/app/data/batch/";
    private static final String CSV_FILE_PATH = "/app/data/batch/student_data.csv";
    private static final String CSV_DELIMITER = ",";

    private static int numOfUser = 0;
    private static HashMap<String, Integer> map = new HashMap<>();

    private static void displayStatistics() {
        System.out.println("Number of users: " + numOfUser);
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Number of students registered in " + entry.getKey() + " course: " + entry.getValue());
        }
        System.out.println("Number of batch files: " + listFiles());
        System.out.println("Number of verified batch files: " + listEligibleFiles());
    }

    private static void processBatchData() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                numOfUser++;
                String[] values = line.split(CSV_DELIMITER);

                // Process the values as needed
                int flag = 0;
                for (String value : values) {
                    if (flag < 2) {
                        flag++;
                        continue;
                    }
                    String subject = value.toLowerCase();
                    if (map.containsKey(subject)) {
                        int count = map.get(subject) + 1;
                        map.put(subject, count);
                    } else {
                        map.put(subject, 1);
                    }
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int listEligibleFiles() {
        int verifiedFiles = 0;

        java.io.File batchFolder = new java.io.File(BATCH_FOLDER_PATH);
        java.io.File[] files = batchFolder.listFiles();
        if (files != null) {
            for (java.io.File file : files) {
                if (file.isFile() && file.getName().contains("verified")) {
                    verifiedFiles++;
                }
            }
        }
        return verifiedFiles;
    }

    private static int listFiles() {
        int filesCount = 0;

        java.io.File batchFolder = new java.io.File(BATCH_FOLDER_PATH);
        java.io.File[] files = batchFolder.listFiles();
        if (files != null) {
            filesCount = files.length;
        }

        return filesCount;
    }

    public static void main(String[] args) {
        while (true) {
            processBatchData();
            displayStatistics();

            System.out.println("Main Menu");
            System.out.println("1. Display Statistics");
            System.out.println("2. Quit");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 2) {
                break;
            }

            // Clear the data for the next batch
            numOfUser = 0;
            map.clear();
        }
    }
}
