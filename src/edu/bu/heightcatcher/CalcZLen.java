package edu.bu.heightcatcher;

public class CalcZLen{
    double[] m; //median height for age
    double[] l; //Box Cox parameter for age
    double[] s; //coefficient of variance for age
    
    CalcZLen(){
        //load m
        //load l
        //load s

    }

    public double zlen(double height, int age, boolean sex){
        
        double z=(Math.pow((height/m[age]),l[age])-1)/s[age]*l[age];

        return z;
    }

}
