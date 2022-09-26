package study.munna.service;

import java.util.List;

import org.springframework.stereotype.Component;

import study.munna.bindingclass.AccountActivate;
import study.munna.bindingclass.Candidate;
import study.munna.bindingclass.Login;
import study.munna.entity.CandidateMaster;
@Component
public interface CandidateServiceInf 
{

	  public boolean saveCandidate(Candidate candidate);
	 
	  public List<Candidate> getAllCandidate();
	  
	  public boolean acativeateAccount(AccountActivate accountActivate);
	  public Candidate getCandidateById(Integer candidateId);
	  public boolean deleteCandidateById(Integer candidateId);
	  public boolean changeAccountStatus(Integer candidateId,String status);
	 // public String resetPassword(String email);
	 // public String login(Login login);
	  
	  
	  
	  
	  
}
