import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class main {
    private static final String BATCH_FOLDER_PATH= "E:\\college\\level_4\\last_semester\\cloud_computing\\assignment_2\\component_1\\app\\data\\batch";

    private static Integer listEligibleFiles() {
        int verified_files = 0;

        File batchFolder = new File(BATCH_FOLDER_PATH);
        File[] files = batchFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().contains("verified")) {
                    verified_files++;
                }
            }
        }

        return verified_files;
    }
    private static Integer listFiles() {
        int Files=0;

        File batchFolder = new File(BATCH_FOLDER_PATH);
        File[] files = batchFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                Files++;

            }
        }

        return Files;
    }

    public static void main(String []args){
        String csvFile = "E:\\college\\level_4\\last_semester\\cloud_computing\\assignment_2\\component_1\\student_data.csv";
        String line;
        String csvDelimiter = ",";
        int numOfUser = 0;
        HashMap<String,Integer > map = new HashMap<String, Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                numOfUser++;
                String[] values = line.split(csvDelimiter);

                // Process the values as needed
                int flag=0;
                for (String value : values) {
                    if (flag<2)
                    {
                        flag++;
                        continue;
                    }


                    String subject = value.toLowerCase();
                    if (map.containsKey(subject)){
                        int x = map.get(subject)+1;
                        map.remove(subject);
                        map.put(subject, x);
                    }
                    else{
                        map.put(subject, 1);
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Number of users"+ " "+numOfUser);
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("number of students registered in "+entry.getKey() + " course: " + entry.getValue());
        }
        System.out.println("Number of batch files: "+listFiles());
        System.out.println("Number of verified batch files: "+listEligibleFiles());
    }
}
