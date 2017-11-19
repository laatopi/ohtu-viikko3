package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);
        System.out.println("");

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        url = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        bodyText = Request.Get(url).execute().returnContent().asString();
        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);
        System.out.println("");

        String url2 = "https://studies.cs.helsinki.fi/ohtustats/stats";
        String statsResponse = Request.Get(url2).execute().returnContent().asString();
        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();

        System.out.println("Jees");
        System.out.println(parsittuData);
        int statsSum = 0;
        float statsAika = 0;
        for (int i = 1; i <= parsittuData.size(); i++) {
            String apu = "" + i;
            JsonObject parse = parsittuData.getAsJsonObject(apu);
            statsSum += parse.get("exercise_total").getAsInt();
            statsAika += parse.get("hour_total").getAsFloat();
        }

        Course info = mapper.fromJson(bodyText, Course.class);
        System.out.println(info + "\n");

        System.out.println("Opiskelijanumero: " + studentNr + "\n");
        float tuntiSumma = 0;
        int tehtavaSumma = 0;
        int i = 0;
        for (Submission submission : subs) {
            System.out.println(submission.toString(info.getExercises().get(i).toString()));
            tuntiSumma = tuntiSumma + submission.getHours();
            tehtavaSumma = tehtavaSumma + submission.getExercises().size();
            i++;
        }
        System.out.println("\nYhteensä: " + tehtavaSumma + " tehtävää " + tuntiSumma
                + " tuntia\n");

        System.out.println("kurssilla yhteensä " + statsAika + " palautusta, palautettuja tehtäviä  " + statsSum + " kpl");
    }
}
