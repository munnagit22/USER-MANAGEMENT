package study.munna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import study.munna.bindingclass.AccountActivate;
import study.munna.bindingclass.Candidate;
import study.munna.repo.CandidateRepo;
import study.munna.service.CandidateServiceImp;

@RestController
public class CandidateController 
{

	 @Autowired
	private CandidateServiceImp candidateServiceImp;
	
	 @PostMapping("/register")
	 public ResponseEntity<String> registerCandidate(@RequestBody Candidate candidate)
	 {
		      boolean isSave;
		      isSave=candidateServiceImp.saveCandidate(candidate);
		      if(isSave)
		      {
		    	  return new ResponseEntity<String>("Registration successfull", HttpStatus.OK);
		      }
		      else {
		    	  return new ResponseEntity<String>("Registration not successfull", HttpStatus.OK);
	
			}
	 }
	 
	 @GetMapping("/candidates")
	 public ResponseEntity< List<Candidate>> getAllCandidate()
	 {
		return new ResponseEntity< List<Candidate>>(candidateServiceImp.getAllCandidate(),HttpStatus.OK);
	 } 
	 
	 @GetMapping("/candidate/{id}")
	 public ResponseEntity<Candidate>getCandidateById(@PathVariable Integer id)
	 {
		     Candidate candidateById = candidateServiceImp.getCandidateById(id);
		     if(candidateById!=null)
		     {
		    	 return new ResponseEntity<Candidate>(candidateById,HttpStatus.OK);
		     }
		     else {
				return null;
			}
	 }
	 @DeleteMapping("/delete/{id}")
	 public ResponseEntity<String> deleteCandidateById(@PathVariable Integer id)
	 {
		 boolean isDelete;
		    isDelete=candidateServiceImp.deleteCandidateById(id);
		    if(isDelete)
		    {
		    	return new ResponseEntity<String>("Record deleted....",HttpStatus.ACCEPTED);
		    }
		    else {
		    	return new ResponseEntity<String>("Record not deleted....",HttpStatus.BAD_REQUEST);
			}
	 }
	 
	 @PostMapping("/activate")
	 public ResponseEntity<String> accountActivation(@RequestBody AccountActivate accountActivate)
	 {
		        boolean isActive =candidateServiceImp.acativeateAccount(accountActivate);
		        if(isActive)
		        {
			    	return new ResponseEntity<String>("Account Activated",HttpStatus.ACCEPTED);
			    }
			    else {
			    	return new ResponseEntity<String>("Account not Activated..",HttpStatus.BAD_REQUEST);
				}
		 
	 }
	 
	 @PostMapping("/changestatus/{id}{status}")
	 public ResponseEntity<String> changeAccountStatus(@PathVariable Integer id, @PathVariable String status)
	 {
		 boolean isChange=candidateServiceImp.changeAccountStatus(id, status);
		 if(isChange)
	        {
		    	return new ResponseEntity<String>("Account status Changed",HttpStatus.ACCEPTED);
		    }
		    else {
		    	return new ResponseEntity<String>("Account status not changed..",HttpStatus.BAD_REQUEST);
			}
		 
	 }
	 
	 
	 
}
