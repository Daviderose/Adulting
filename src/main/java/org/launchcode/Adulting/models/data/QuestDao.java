package org.launchcode.Adulting.models.data;

import org.launchcode.Adulting.models.Quest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

// Interfaces with Quest object
@Repository
@Transactional
public interface QuestDao extends CrudRepository<Quest, Integer > {
}
