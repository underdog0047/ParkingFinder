// 

import java.util.Random;
import java.util.Scanner;
import java.text.NumberFormat;


public class ParkingFinder
{
	public static void main(String[] args)
	{

		long seed;
		int parkingTime;
		final int CAR_X, CAR_Y;
		ParkingSpot spotOne, spotTwo, spotThree, spotFour;

		Random rand;
		Scanner scan;

		scan = new Scanner(System.in);

		// Get Random seed from user
		System.out.print("\nEnter your random seed:  ");
		seed = scan.nextLong();

		rand = new Random(seed);    // initilize random object with user's seed

		// Get parking time from user
		System.out.print("How long do you plan to be parked? (in minutes):  ");
		parkingTime = scan.nextInt();

		// Generae car's random location, (x and y values)
		CAR_X = rand.nextInt(100);
		CAR_Y = rand.nextInt(100);

		System.out.println("\nYour car is at:  X: " + CAR_X +
		                                     " Y: " + CAR_Y + "\n");

		spotOne = new ParkingSpot("1st Street", rand.nextInt(100), rand.nextInt(100));
		spotTwo = new ParkingSpot("2nd Street", rand.nextInt(100), rand.nextInt(100));
		spotThree = new ParkingSpot("3rd Street", rand.nextInt(100), rand.nextInt(100));
		spotFour = new ParkingSpot("4th Street", rand.nextInt(100), rand.nextInt(100));

		// Change the cost interval for the last two spots
		spotThree.setCostPerInterval(0.3);
		spotFour.setCostPerInterval(0.3);


		// Print the spots, distance, and cost to console
		printSpot(CAR_X, CAR_Y, parkingTime, spotOne);
		printSpot(CAR_X, CAR_Y, parkingTime, spotTwo);
		printSpot(CAR_X, CAR_Y, parkingTime, spotThree);
		printSpot(CAR_X, CAR_Y, parkingTime, spotFour);

		// Prints the closest distance
		System.out.println("The closest spot is:  " +
		                   getClosestSpot(CAR_X, CAR_Y, spotOne, spotTwo, spotThree, spotFour) + "\n");

	}
	// Support Methods

	private static int calculateDistance(int carX, int carY, ParkingSpot spot) {
	    // Returns the distance from cars location to spot using Manhattan Geometry
		return Math.abs(spot.getLocationX() - carX) + Math.abs(spot.getLocationY() - carY);
	}

	private static double calculateCost(int parkingTime, ParkingSpot spot) {
		// Returns the correct cost with the correct inteval
		return spot.getCostPerInterval() * Math.ceil(parkingTime / 10.0);
	}

	private static void printSpot(int carX, int carY, int parkingTime, ParkingSpot spot) {
		// Prints spot to console in formatted way.  Uses calculateDistance, and calculateCost
		NumberFormat costFormat;
	    costFormat = NumberFormat.getCurrencyInstance();  // Setup currency formatter

		System.out.println("\nSpot " + spot.getStreet().charAt(0) + ":  " + spot);
		System.out.println("\tThe distance to Spot" + spot.getStreet().charAt(0) + " is:\t" + calculateDistance(carX, carY, spot));
		System.out.println("\tThe total cost for Spot" + spot.getStreet().charAt(0) + " is:\t" + 
		                   costFormat.format(calculateCost(parkingTime, spot)));
	}

	private static ParkingSpot getClosestSpot(int carX, int carY, ParkingSpot spot1, ParkingSpot spot2, ParkingSpot spot3, ParkingSpot spot4) {
		ParkingSpot closestSpot;
		int distanceToSpotOne, distanceToSpotTwo;
		int distanceToSpotThree, distanceToSpotFour;

		// Calculate and store the distance to each spot
		distanceToSpotOne = calculateDistance(carX, carY, spot1);
		distanceToSpotTwo = calculateDistance(carX, carY, spot2);
		distanceToSpotThree = calculateDistance(carX, carY, spot3);
		distanceToSpotFour = calculateDistance(carX, carY, spot4);

		// Finds the minimum distance by calculating distance to each spot
		int closestDistance = Math.min(distanceToSpotFour,                                 // minimum from last min and spot4
		                               Math.min(distanceToSpotThree,                       // minimum from last min and spot3
									   Math.min(distanceToSpotTwo, distanceToSpotOne)));   // minimum either spot2 or spot1

		System.out.println("\nThe distance to the closest spot is:  " + closestDistance);

		// Checks which spot distance matches the closest distance
		if (closestDistance == distanceToSpotOne) {
			closestSpot = spot1;
		} else if (closestDistance == distanceToSpotTwo) {
			closestSpot = spot2;
		} else if (closestDistance == distanceToSpotThree) {
			closestSpot = spot3;
		} else {
			closestSpot = spot4;
		}
		return closestSpot;
	}
}
