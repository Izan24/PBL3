package eus.healthit.bchef.core.models;

import java.awt.Image;
import java.awt.font.ImageGraphicAttribute;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class User {

	public String getImgString() {
		return imgString;
	}

	public void setImgString(String imgString) {
		this.imgString = imgString;
	}

	int id;
	String name, surname;
	Image profilePic;
	String email;
	String username;
	String password;
	String imgString;
	List<Integer> followed;
	int followers;
	List<Recipe> published;
	List<Recipe> saved;
	List<Item> shopList;
	List<Recipe> history;

	/**
	 * Creates a new User with Profile pic
	 */
	public User(int id, String name, String surname, Image profilePic, String email, String username, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.profilePic = profilePic;
		this.email = email;
		this.username = username;
		this.password = password;
		followed = new ArrayList<>();
		followers = 0;
		published = new ArrayList<>();
		saved = new ArrayList<>();
		shopList = new ArrayList<>();
		history = new ArrayList<>();
	}

	/**
	 * Creates a ppipippip user
	 */

	public User(int id, String name, String surname, String email) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	/**
	 * Creates a new User with Profile pic and without username and password
	 */
	public User(int id, String name, String surname, Image profilePic, String email) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.profilePic = profilePic;
		this.email = email;
		this.username = "";
		this.password = "";
		followed = new ArrayList<>();
		followers = 0;
		published = new ArrayList<>();
		saved = new ArrayList<>();
		shopList = new ArrayList<>();
		history = new ArrayList<>();
	}

	/**
	 * Creates a new User without Profile pic
	 */
	public User(int id, String name, String surname, String email, String username, String password) {

		this.id = id;
		this.name = name;
		this.surname = surname;
		try {
			this.profilePic = ImageIO.read(new File("resources/user/default_user.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.email = email;
		this.username = username;
		this.password = password;
		followed = new ArrayList<>();
		followers = 0;
		published = new ArrayList<>();
		saved = new ArrayList<>();
		shopList = new ArrayList<>();
		history = new ArrayList<>();
	}

	/**
	 * Load an existing user
	 */
	public User(int id, String name, String surname, Image profilePic, String email, String username, String password,
			List<Integer> followed, int followers, List<Recipe> published, List<Recipe> saved, List<Item> shopList,
			List<Recipe> history) {

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
		this.shopList = shopList;
		this.history = history;
	}

	public User(String name, String surname, String email, String username, String pwd) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = pwd;
		this.imgString = "default";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// ------------------------------------------------------------------------

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	// ------------------------------------------------------------------------

	public Image getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(Image profilePic) {
		this.profilePic = profilePic;
	}

	public void removeProfilePic() {
		try {
			this.profilePic = ImageIO.read(new File("resources/user/default_user.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// ------------------------------------------------------------------------

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// ------------------------------------------------------------------------

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// ------------------------------------------------------------------------

	public int getId() {
		return id;
	}

	// ------------------------------------------------------------------------

	public List<Integer> getFollowed() {
		return followed;
	}

	public void addFollowedUser(Integer userID) {
		followed.add(userID);
	}

	public void removeFollowedUser(Integer userID) {
		followed.remove(userID);
	}

	public int getFollowedNumber() {
		return followed.size();
	}

	public void setFollowed(List<Integer> followed) {
		this.followed = followed;

	}

	// ------------------------------------------------------------------------

	public void setFollowers(int followers) {
		this.followers = followers;

	}

	public int getFollowers() {
		return followers;
	}

	public void addFollower() {
		followers++;
	}

	public void removeFollower() {
		followers--;
	}

	public int getFollowersNumber() {
		return followers;
	}

	// ------------------------------------------------------------------------

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

	// ------------------------------------------------------------------------

	public List<Recipe> getSaved() {
		return saved;
	}

	public void addSaved(Recipe recipe) {
		saved.add(recipe);
	}

	public void removeSaved(Recipe recipe) {
		saved.remove(recipe);
	}

	// ------------------------------------------------------------------------

	public List<Item> getShopList() {
		return shopList;
	}

	public void addShopElement(Item ingredient) {
		shopList.add(ingredient);
	}

	public void removeShopElement(Item ingredient) {
		shopList.remove(ingredient);
	}

	// ------------------------------------------------------------------------

	public List<Recipe> getHistory() {
		return history;
	}

	public void addHistory(Recipe recipe) {
		history.add(recipe);
	}

	@Override
	public boolean equals(Object obj) {
		User o = (User) obj;

		return id == o.getId();
	}

	@Override
	public String toString() {
		return name + "\n" + surname + "\n" + username + "\n" + email;
	}

}
