package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
//import java.text.SimpleDateFormat;
@XmlRootElement(name="healthprofile")
@XmlType(propOrder = { "lastupdate","weight", "height", "BMI" })
@XmlAccessorType(XmlAccessType.FIELD)
public class HealthProfile {
        private String lastupdate;
	private double weight; // in kg
	private double height; // in m
      //  private double bmi; 

	public HealthProfile(String lastupdate,double weight, double height) {

                this.lastupdate=lastupdate;
		this.weight = weight;
		this.height = height;
               // this.bmi=31.14;
	}

	public HealthProfile() {
                this.lastupdate="2014-09-20T18:00:00.000+02:00";
		this.weight = 85.5;
		this.height = 1.72;
               // this.bmi=31.14;

	}

        public String getLastupdate() {
		return lastupdate;
}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	public String toString() {
		return "Height="+height+", Weight="+weight;
	}

	@XmlElement(name="bmi")
	public double getBMI() {
		return this.weight/(Math.pow(this.height, 2));
	}
}
