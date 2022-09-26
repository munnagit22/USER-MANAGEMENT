package study.munna.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import study.munna.entity.CandidateMaster;

public interface CandidateRepo extends JpaRepository<CandidateMaster,Integer> {

}
