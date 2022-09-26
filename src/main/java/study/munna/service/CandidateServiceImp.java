package study.munna.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import study.munna.bindingclass.AccountActivate;
import study.munna.bindingclass.Candidate;
import study.munna.bindingclass.Login;
import study.munna.entity.CandidateMaster;
import study.munna.repo.CandidateRepo;

@Service
public class CandidateServiceImp implements CandidateServiceInf {

	   @Autowired
	  private CandidateRepo candidateRepo;
	
	@Override
	public boolean saveCandidate(Candidate candidate) {
		
		CandidateMaster candidateMaster=new CandidateMaster();
		
	     BeanUtils.copyProperties(candidate, candidateMaster);
	      candidateMaster.setPassword(generatePwd());
	      candidateMaster.setAccountStatus("in-active");
	     candidateRepo.save(candidateMaster);
		
		return true;
	}
	
	public String generatePwd()
	{
		StringBuilder sb=new StringBuilder();
		 String upperLetter="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		 String lowerLetter="abcdefghijklmnopqrstuvwxyz";
		 String number="0123456789";
		 Random random=new Random();
		 int passlen=6;
		 int index;
		 String passChar=upperLetter+lowerLetter+number;
		 for(int i=0;i<passlen;i++)
		 {
			  index=random.nextInt(62);
			 sb.append(passChar.charAt(index));
		 }
		
		return sb.toString();
	}
	
	
	

	@Override
	public List<Candidate> getAllCandidate() {
	
		List<CandidateMaster>masterList=candidateRepo.findAll();
		List<Candidate>candidateList=new ArrayList<>();
		
		for(CandidateMaster cand:masterList)
		{
			Candidate candidate=new Candidate();
			BeanUtils.copyProperties(cand,candidate);
			candidateList.add(candidate);
		}
		
		return candidateList;
		
		
	}

	@Override
	public boolean acativeateAccount(AccountActivate accountActivate) {
		
		CandidateMaster entity=new CandidateMaster();
		entity.setEmail(accountActivate.getEmail());
		entity.setPassword(accountActivate.getTempPassword());
		
		//Example<CandidateMaster> of = Example.of(entity);
		          
		         List<CandidateMaster> list=candidateRepo.findAll();
		      List<CandidateMaster> collect = list.stream().filter(cand->cand.getEmail().equals(accountActivate.getEmail())).collect(Collectors.toList());
		
		//  List<CandidateMaster> find = candidateRepo.findAll(of);
		
		  if(!collect.isEmpty())
          {
        	  CandidateMaster candidateMaster=collect.get(0);
        	  candidateMaster.setAccountStatus("Active");
        	  candidateMaster.setPassword(accountActivate.getNewPassword());
        	  candidateRepo.save(entity);
        	  return true;
          }
          else {
        	  return false;
		}
		
		
	}

	@Override
	public Candidate getCandidateById(Integer candidateId) 
	{
		 CandidateMaster masterList=new CandidateMaster();
		 Optional<CandidateMaster> findById = candidateRepo.findById(candidateId);
		 if(findById.isPresent())
		 {
			 Candidate candidate=new Candidate();
			 masterList=findById.get();
			 BeanUtils.copyProperties(masterList, candidate);
			 return candidate;
		 }
		return null;
	}

	@Override
	public boolean deleteCandidateById(Integer candidateId) {

		try {
			candidateRepo.deleteById(candidateId);
			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean changeAccountStatus(Integer candidateId, String status) {
		
		
		    Optional  < CandidateMaster> findById = candidateRepo.findById(candidateId);
		      if(findById.isPresent()) 
		      {  
		    	  CandidateMaster candidateMaster=findById.get();
		    	   
		    	  candidateMaster.setAccountStatus(status);
		    	  candidateRepo.save(candidateMaster);
		    	  return true;
		      }
		      else
		      {
		    	  return false;
		      }
		
	}
/*
	@Override
	public String resetPassword(String email) {
		
		
		return null;
	}

	@Override
	public String login(Login login) {
		// TODO Auto-generated method stub
		return null;
	}
  */
}
