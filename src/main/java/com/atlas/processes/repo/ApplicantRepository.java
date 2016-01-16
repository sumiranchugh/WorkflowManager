package com.atlas.processes.repo;

import com.atlas.processes.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sumiran Chugh on 1/12/2016.
 */
public interface ApplicantRepository extends JpaRepository<Applicant,Long> {
}
