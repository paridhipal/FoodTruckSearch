import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*************************************************
 * Read from and Parse CSV file. CSV file contains food truck details of
 * SanFranciso including their locations (latitude and longitude) Find food
 * trucks in an area based on the latitude and longitude details.
 *
 * @author Paridhi Pal
 * 
 */

public class FindFoodTrucks {

	/*******************************
	 * Parse CSV file and find FoodTrucks
	 *
	 * @param latitude  of an area
	 * @param longitude of an area
	 * @throws IOException In case of failure in reading CSV file.
	 * @return list of FoodTrucks in an area
	 */
	public static List<FoodTruck> findFoodTruck(String latitude, String longitude) throws IOException {
		String line = "";
		List<FoodTruck> listFoodTruck = new ArrayList<FoodTruck>();

		/*************************************************
		 * Read from the CSV file and find trucks in a specific latitude and longitude
		 * Delimiter and CSV file path in the constants class
		 */
		try {
			BufferedReader br = new BufferedReader(new FileReader(FoodTruckConstants.CSVPath));
			// Reading each row
			while ((line = br.readLine()) != null) {
				// Splitting the row based on a delimiter
				String[] truck = line.split(FoodTruckConstants.delimiter);
				// Check for the latitude and longitude of the truck
				if (truck[14].contains(latitude) && truck[15].contains(longitude)) {
					// Converting the row to FoodTruck object
					FoodTruck foodTruck = createFoodTruck(truck);
					// Add the foodTruck to the output list
					listFoodTruck.add(foodTruck);
				}
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(listFoodTruck);
		return listFoodTruck;
	}

	/*************************************************
	 * Convert a line of the CSV file to FoodTruck object
	 * 
	 * @param array of strings when the row is split
	 * @return FoodTruck
	 */
	private static FoodTruck createFoodTruck(String[] metadata) {
		String locationid = metadata[0];
		String applicant = metadata[1];
		String locDes = metadata[4];
		String address = metadata[5];
		String status = metadata[10];
		String fooditems = metadata[11];
		String latitude = metadata[14];
		String longitude = metadata[15];
		return new FoodTruck(locationid, applicant, locDes, address, status, fooditems, latitude, longitude);
	}
}