package study.munna.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.internal.build.AllowSysOut;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="Candidate")
public class CandidateMaster 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private long mobile;
	private long aaharNo;
	private String gender;
	private LocalDate dob;
	private String accountStatus;
   private String password;
	private LocalDate createDate;
	private LocalDate updateDate;
	private String createdBy;
	private String updatedBy;
	
   
	
	

}
