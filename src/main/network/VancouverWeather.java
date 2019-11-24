package network;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class VancouverWeather extends Subject {
    private String apiKey;
    private StringBuilder apiJson;
    private BufferedReader br;

    public VancouverWeather() {
        apiKey = "96a13e0f1cf25f5cdab7aaa826e95330";
    }

    //MODIFIES: this
    //EFFECTS: parses api JSON and gets the daily min temp and max temp and returns an ArrayList with two float values
    //         of temp_min and temp_max converted from kelvin to celsius
    public void setTemp() {
        ArrayList<Double> result = new ArrayList<>();
        int minTempIndex = apiJson.indexOf("temp_min");
        int maxTempIndex = apiJson.indexOf("temp_max");
        int startOfTemp = 10;
        int endOfTemp = 16;
        double conversionFactor = 273.15;

        result.add(Double.parseDouble(apiJson.substring(minTempIndex + startOfTemp, minTempIndex + endOfTemp)
            ) - conversionFactor);
        result.add(Double.parseDouble(apiJson.substring(maxTempIndex + startOfTemp, maxTempIndex + endOfTemp)
            ) - conversionFactor);

        notifyObservers(result);
    }

    //MODIFIES: this
    //EFFECTS: retrieves Vancouver weather JSON from URL and sets it to field apiJson
    public void retrieveJson() throws MalformedURLException, IOException {
        try {
            String theURL = "http://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=" + apiKey; //this can point to any URL
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            apiJson = sb;
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }




    public static void main(String[] args) throws IOException {
        VancouverWeather vw = new VancouverWeather();
        vw.addObserver(new Subscriber("Nate"));
        vw.addObserver(new Subscriber("Sasha"));
        vw.retrieveJson();
        vw.setTemp();
    }

}
