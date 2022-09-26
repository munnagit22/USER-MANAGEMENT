package study.munna.bindingclass;

import lombok.Data;

@Data
public class AccountActivate
{
   private String email;
   private String newPassword;
   private String tempPassword;
   private String confirmPassword;
}
