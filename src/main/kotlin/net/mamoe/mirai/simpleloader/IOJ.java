package net.mamoe.mirai.simpleloader;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IOJ {

    public static void Save(){
        Gson gson = new Gson();
        List chars = MyBotKt.getChars();
        try (FileWriter file = new FileWriter("C:\\Users\\cheng\\IdeaProjects\\untitled1\\src\\main\\kotlin\\net\\mamoe\\mirai\\simpleloader\\sss")) {
            String json = gson.toJson(chars);
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Character[] Load(){
        Character[] load = new Character[0];
        Gson gson = new Gson();
        try {

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\cheng\\IdeaProjects\\untitled1\\src\\main\\kotlin\\net\\mamoe\\mirai\\simpleloader\\sss"));

            // convert JSON string to User object
            load = gson.fromJson(reader,Character[].class);


            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return load;
    }

}
