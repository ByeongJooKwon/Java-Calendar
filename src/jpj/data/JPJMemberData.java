package jpj.data;

import java.io.*;
/*
 * create table Client (
no number(10),      
id varchar(50),
pwd varchar(50),
name varchar(10),
sex  number(1),
birth varchar(570),
mail varchar(50),
phone varchar(20)
);
 */

public class JPJMemberData implements Serializable {
	public JPJMemberData() {}
	public String id;
	public String password;
	public String name;
	public String sex;
	public String birthday;
	public String mail;
	public String phone;
	public String joinYN;// 회원가입상태 탈퇴 여부.  
}
