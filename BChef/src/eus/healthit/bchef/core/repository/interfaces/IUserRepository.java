package eus.healthit.bchef.core.repository.interfaces;

import eus.healthit.bchef.core.models.User;

public interface IUserRepository {

	public User getUserById(Long id);

	public boolean insertUser(User user);

	public boolean deleteUser(User user);

	public boolean deleteUser(Long id);

}
