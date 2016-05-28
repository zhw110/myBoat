package mycode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCsvDatatoMemory {

	List <JsonFormat> list  = myReadData("vessels.csv");

	private List<JsonFormat> myReadData(String path) {
		List<JsonFormat> list = new ArrayList<JsonFormat>();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        if (is == null) { 
            return null;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        try {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arr = line.split("\\|");
                JsonFormat dataDO = new JsonFormat();
                dataDO.setVesselname(arr[0].trim()); //Calling a copy of the string object, 
                dataDO.setImo(arr[1].trim());        //but all of the starting and ending spaces are removed
                dataDO.setMmsi(arr[2].trim());
                dataDO.setType(arr[3].trim());
                dataDO.setLongitude(arr[4].trim());
                dataDO.setLatitude(arr[5].trim());
                list.add(dataDO);
            }
        } catch (IOException e) {
            return null;
        }
        return list;
	}
}
