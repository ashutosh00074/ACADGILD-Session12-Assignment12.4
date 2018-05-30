package com.tech.gigabyte.intermediatealertdiaglog;

/**
 * Created by GIGABYTE on 5/30/2017.
 * Getting Saved Data
 */

class Eachrow {
    private String txtname, txtphone, txtdob;

    Eachrow(String tname, String tphone, String tdob) {
        txtname = tname;
        txtphone = tphone;
        txtdob = tdob;
    }

    //Entered Name
    String getTxtname() {
        return txtname;
    }

    //Entered Phone No.
    String getTxtphone() {
        return txtphone;
    }

    //Entered Data Of Birth
    String getTxtdob() {
        return txtdob;
    }

}