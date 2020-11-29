package de.neuefische.allyourfavorites.db;

import de.neuefische.allyourfavorites.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDb extends PagingAndSortingRepository<User, String> {

}
