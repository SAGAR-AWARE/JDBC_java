

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class RestaurantDao implements DaoInterface<Restaurant, Integer> {

	@Override
	public Collection<Restaurant> getAll() {
		String sqlQuery = "select rest_id, rest_name, rest_cuisine, rest_branches from restaurant_master";
		Collection<Restaurant> allRestaurants = new ArrayList<>();
		try(
				Connection conn = JdbcUtils.buildConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlQuery);
				){
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String cuisine = rs.getString(3);
				int branchCount = rs.getInt(4);
				
				//Populating a Restaurant object based upon the values fetched from the record.
				Restaurant currentRestaurant = new Restaurant(id, name, cuisine, branchCount);
				//Adding this Restaurant object into the Collection
				allRestaurants.add(currentRestaurant);
				
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		 
		return allRestaurants;
	}

	@Override
	public Restaurant getOne(Integer id) {
		// TODO Auto-generated method stub
		Restaurant foundRestaurant = null;
		try(
				Connection conn = JdbcUtils.buildConnection();
				){
			String sqlQuery = 
					"select rest_id, rest_name, rest_cuisine, rest_branches from restaurant_master where rest_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
			pstmt.setInt(1, id);
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()) {
				int r_id = rs.getInt(1);
				String name = rs.getString(2);
				String cuisine = rs.getString(3);
				int branchCount = rs.getInt(4);
				
				foundRestaurant = new Restaurant(r_id, name, cuisine, branchCount);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return foundRestaurant;
	}

	@Override
	public void create(Restaurant newRestaurant) {
		String sqlQuery =
		"insert into restaurant_master(rest_id, rest_name,rest_cuisine,rest_branches) values(?,?,?,?)";
		try(
				Connection conn = JdbcUtils.buildConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				){
				//Extracting the values from the incoming Restaurant object
				int id = newRestaurant.getRestaurantId();
				String name = newRestaurant.getName();
				String cuisine = newRestaurant.getCuisine();
				int branchCount = newRestaurant.getBranchCount();
				//Substituting the values for unknown parameters
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, cuisine);
				pstmt.setInt(4, branchCount);
				
				int count = pstmt.executeUpdate();
				System.out.println(count + " record inserted");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void deleteOne(Integer id) {
		String sqlQuery = "delete from restaurant_master where rest_id = ?";
		try(
				Connection conn = JdbcUtils.buildConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				){
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			System.out.println(count + " record deleted");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void update(Restaurant modifiedRestaurant) {
		String sqlQuery = "update restaurant_master set rest_name = ?, rest_cuisine = ?, rest_branches = ? where rest_id = ?";
		try(
				Connection conn = JdbcUtils.buildConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				){
			int id = modifiedRestaurant.getRestaurantId();
			String name = modifiedRestaurant.getName();
			String cuisine = modifiedRestaurant.getCuisine();
			int branchCount = modifiedRestaurant.getBranchCount();
			
			pstmt.setInt(4, id);
			pstmt.setString(1, name);
			pstmt.setString(2, cuisine);
			pstmt.setInt(3, branchCount);
			
			int count = pstmt.executeUpdate();
			System.out.println(count + " record updated");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	

}
