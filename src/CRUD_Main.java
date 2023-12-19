

import java.util.Collection;

public class CRUD_Main {
	
	private static void updateRestaurant() {
		DaoInterface<Restaurant, Integer> idao = new RestaurantDao();
		//Obtaining the Restaurant which is to be modified
		Restaurant currentRestaurant = idao.getOne(2);
		//Making some changes using setters
		currentRestaurant.setName("Green and Red Signal");
		currentRestaurant.setCuisine("Multicuisine");
		//Sending this modified state to update() method to reflect the changes into the DB
		idao.update(currentRestaurant);
	}
	
	private static void deleteRestaurant() {
		DaoInterface<Restaurant, Integer> idao = new RestaurantDao();
		idao.deleteOne(3);
	}
	private static void createNewRestaurant() {
		DaoInterface<Restaurant, Integer> idao = new RestaurantDao();
		Restaurant rst = new Restaurant(2, "Green Signal", "Oriental", 15);
		idao.create(rst);
	}
	
	private static void showOneRestaurant() {
		DaoInterface<Restaurant, Integer> idao = new RestaurantDao();
		Restaurant rst = 	idao.getOne(3);
		if(rst != null)
			System.out.println(rst);
		else
			System.out.println("Restaurant with this ID does not exist");
	}
	private static void showAllRestaurants() {
		DaoInterface<Restaurant, Integer> idao = new RestaurantDao();
		Collection<Restaurant> allAvailableRestaurants  = idao.getAll();
		//allAvailableRestaurants.stream().forEach((rst) -> System.out.println(rst));
		for(Restaurant rst : allAvailableRestaurants)
			System.out.println(rst);
	}

	public static void main(String[] args) {
		//showAllRestaurants();
		//showOneRestaurant();
		//createNewRestaurant();
		//deleteRestaurant();
		updateRestaurant();
		

	}

}
