package com.example.the_youthempire;

public class List_Data_sl {
    private String namep;
    private String emailp;
    private String php;
    private String addrp;
    private String active;
    private String task_point;
    private String reffer_point;
    private String extra_point;
    private String r_id;


    public List_Data_sl(String namep, String emailp, String php, String addrp, String active,String task_point,String reffer_point,String extra_point,String r_id) {
        this.namep = namep;
        this.emailp=emailp;
        this.php=php;
        this.addrp=addrp;
        this.active=active;
        this.task_point=task_point;
        this.reffer_point=reffer_point;
        this.extra_point=extra_point;
        this.r_id=r_id;

    }

    public String getNamep() {
        return namep;
    }
    public String getEmailp() {
        return emailp;
    }
    public String getPhp(){
        return php;
    }
    public String getAddrp() {
        return addrp;
    }
    public String getActive() {
        return active;
    }
    public String getTask_point() {
        return task_point;
    }
    public String getReffer_point() {
        return reffer_point;
    }
    public String getExtra_point() {
        return extra_point;
    }
    public String getR_id() {
        return r_id;
    }
}