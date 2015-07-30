package com.amoiseev.model;

/**
 * Created by amoiseev on 30.07.2015.
 */
public class Student {
    private Integer Fil;
    private Integer Spec;
    private Integer In;

    public Student(){
        this.Fil=0;
        this.Spec=0;
        this.In=0;
    }
    public void print(String delimiter1, String delimiter2){
        System.out.println(Fil + delimiter1 + Spec + delimiter2 + In + delimiter2);
    }

    public Integer getSpec() {
        return Spec;
    }

    public void setSpec(Integer spec) {
        Spec = spec;
    }

    public Integer getFil() {
        return Fil;
    }

    public void setFil(Integer fil) {
        Fil = fil;
    }

    public Integer getIn() {
        return In;
    }

    public void setIn(Integer in) {
        In = in;
    }
}
