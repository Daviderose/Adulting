package org.launchcode.Adulting.models.data;

import org.launchcode.Adulting.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


// Interfaces with Category object
@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer > {
}