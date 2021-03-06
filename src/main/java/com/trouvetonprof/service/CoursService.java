package com.trouvetonprof.service;

import com.trouvetonprof.domain.Cours;
import com.trouvetonprof.repository.CoursRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Cours.
 */
@Service
@Transactional
public class CoursService {

    private final Logger log = LoggerFactory.getLogger(CoursService.class);

    private final CoursRepository coursRepository;

    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    /**
     * Save a cours.
     *
     * @param cours the entity to save
     * @return the persisted entity
     */
    public Cours save(Cours cours) {
        log.debug("Request to save Cours : {}", cours);
        return coursRepository.save(cours);
    }

    /**
     * Get all the cours.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Cours> findAll() {
        log.debug("Request to get all Cours");
        return coursRepository.findAll();
    }


    /**
     * Get one cours by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Cours> findOne(Long id) {
        log.debug("Request to get Cours : {}", id);
        return coursRepository.findById(id);
    }

    /**
     * Delete the cours by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Cours : {}", id);
        coursRepository.deleteById(id);
    }

    /**
     * Get moyenne of notes by annoce id.
     *
     * @param id the id of the entity
     * @return moyenne of notes
     */
    @Transactional(readOnly = true)
    public double findNoteMoyenneByAnnonceId(Long id) {
        log.debug("Request to get moyenne of notes by annonce id : {}", id);
        return coursRepository.findByAnnonceId(id).stream()
                .mapToDouble(Cours::getNote)
                .average()
                .orElse(-1);
    }

    /**
     * Get moyenne of notes by annoce id.
     *
     * @param id the id of the entity
     * @return moyenne of notes
     */
    @Transactional(readOnly = true)
    public List<String> findCommentairesByAnnonceId(Long id) {
        log.debug("Request to get commentaires by annonce id : {}", id);
        return coursRepository.findCommentaireByAnnonceId(id).stream().map(Cours::getCommentaire).collect(Collectors.toList());
    }

}
