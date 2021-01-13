package eus.healthit.bchef.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class User {

	int id;
	String name, surname;
	ImageIcon profilePic;
	String email;
	String username;
	String password;
	List<User> followed;
	List<User> followers;
	List<Recipe> published;
	List<Recipe> saved;
	List<Recipe> pending;
	List<Item> shopList; // HACEMOS QUE SEA INGREDIENT O UN STRING SIN MAS??
	List<Recipe> history;

	
	/**
	 * Creates a new User with Profile pic
	 */
	public User(int id, String name, String surname, ImageIcon profilePic, String email, String username,
			String password) {

		this.id = id;
		this.name = name;
		this.surname = surname;
		this.profilePic = profilePic;
		this.email = email;
		this.username = username;
		this.password = password;
		followed = new ArrayList<>();
		followers = new ArrayList<>();
		published = new ArrayList<>();
		saved = new ArrayList<>();
		pending = new ArrayList<>();
		shopList = new ArrayList<>();
		history = new ArrayList<>();
	}
	
	/**
	 * Creates a new User without Profile pic
	 */
	public User(int id, String name, String surname, String email, String username,
			String password) {

		this.id = id;
		this.name = name;
		this.surname = surname;
		this.profilePic = new ImageIcon("resources/user/default_user.png");
		this.email = email;
		this.username = username;
		this.password = password;
		followed = new ArrayList<>();
		followers = new ArrayList<>();
		published = new ArrayList<>();
		saved = new ArrayList<>();
		pending = new ArrayList<>();
		shopList = new ArrayList<>();
		history = new ArrayList<>();
	}

	/**
	 * Load an existing user
	 */
	public User(int id, String name, String surname, ImageIcon profilePic, String email, String username,
			String password, List<User> followed,List<User> followers, List<Recipe> published, List<Recipe> saved, List<Recipe> pending,
			List<Item> shopList, List<Recipe> history) {

		this.id = id;
		this.name = name;
		this.surname = surname;
		this.profilePic = profilePic;
		this.email = email;
		this.username = username;
		this.password = password;
		this.followed = followed;
		this.followers = followers;
		this.published = published;
		this.saved = saved;
		this.pending = pending;
		this.shopList = shopList;
		this.history = history;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//------------------------------------------------------------------------
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	//------------------------------------------------------------------------

	public ImageIcon getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(ImageIcon profilePic) {
		this.profilePic = profilePic;
	}
	
	public void removeProfilePic() {
		this.profilePic = new ImageIcon("resources/user/default_user.png");
	}
	
	//------------------------------------------------------------------------

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//------------------------------------------------------------------------

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//------------------------------------------------------------------------
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//------------------------------------------------------------------------

	public int getId() {
		return id;
	}

	//------------------------------------------------------------------------
	
	public List<User> getFollowed() {
		return followed;
	}
	
	public void addFollowedUser(User user) {
		followed.add(user);
	}
	
	public void removeFollowedUser(User user) {
		followed.remove(user);
	}
	
	public int getFollowedNumber() {
		return followed.size();
	}
	
	//------------------------------------------------------------------------
	
	public List<User> getFollowers() {
		return followers;
	}
	
	public void addFollower(User user) {
		followers.add(user);
	}
	
	public void removeFollower(User user) {
		followers.remove(user);
	}
	
	public int getFollowersNumber() {
		return followers.size();
	}
	
	//------------------------------------------------------------------------


	public List<Recipe> getPublished() {
		return published;
	}
	
	public int getPublishedNumber() {
		return published.size();
	}
	
	public void addPublication(Recipe recipe) {
		published.add(recipe);
	}
	
	public void removePublication(Recipe recipe) {
		published.remove(recipe);
	}
	
	//------------------------------------------------------------------------


	public List<Recipe> getSaved() {
		return saved;
	}
	
	public void addSaved(Recipe recipe) {
		saved.add(recipe);
	}
	
	public void removeSaved(Recipe recipe) {
		saved.remove(recipe);
	}

	//------------------------------------------------------------------------
	
	public List<Recipe> getPending() {
		return pending;
	}
	
	public void addPending(Recipe recipe) {
		pending.add(recipe);
	}
	
	public void removePending(Recipe recipe) {
		pending.remove(recipe);
	}
	
	//------------------------------------------------------------------------

	public List<Item> getShopList() {
		return shopList;
	}
	
	public void addShopElement(Item ingredient) {
		shopList.add(ingredient);
	}
	
	public void removeShopElement(Item ingredient) {
		shopList.remove(ingredient);
	}
	
	//------------------------------------------------------------------------


	public List<Recipe> getHistory() {
		return history;
	}
	
	public void addHistory(Recipe recipe) {
		history.add(recipe);
	}
}
