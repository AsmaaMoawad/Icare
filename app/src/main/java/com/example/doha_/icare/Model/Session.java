package com.example.doha_.icare.Model;

import com.cocosw.favor.AllFavor;
import com.cocosw.favor.Default;

/**
 * Created by doha_ on 4/15/2017.
 */

@AllFavor
public interface Session {

    @Default("0")
    int getId();
    String getAge();
    String getPass();
    String getFname();
    String getLname();
    String getPhone();
    String getMail();
    int[] disease_ids = new int[0];


    void setId(int id);
    void setAge(String age);
    void setPass(String pass);
    void setFname(String fname);
    void setLname(String lname);
    void setPhone(String phone);
    void setMail(String mail);
    void setDisease_ids(int[] disease_ids);

}
