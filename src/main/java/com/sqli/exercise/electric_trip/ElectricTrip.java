package com.sqli.exercise.electric_trip;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ElectricTrip {

	private final String pathDesc;

	private List<Participant> participants=new ArrayList<>();

	public ElectricTrip(String pathDesc) {
		this.pathDesc=pathDesc;
	}

	public int startTripIn(String startCityName, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {

		Participant participant=new Participant(this.participants.size()+1,startCityName,batterySize,lowSpeedPerformance,highSpeedPerformance);
		this.participants.add(participant);
		System.out.println(participant.getId());
		return participant.getId();
	}

	public void go(int participantId) {
		String[] pathDescSeparated=pathDesc.split("");
		this.participants.stream().filter(participant -> participant.getId()==participantId).map(participant ->
				{
					participant.setConsume(consome(participant));
					participant.setLocation(pathDescSeparated[2]);
					System.out.println("--> "+participant.getLocation());
					return participant;
				}
		);

	}

	public int consome(Participant participant){
		String[] pathDescSeparated=pathDesc.split("");
		return  Integer.parseInt(pathDescSeparated[1])/participant.getLowSpeedPerformance();
	}

	public void sprint(int participantId) {
    	throw new UnsupportedOperationException("Still to be implemented");
	}

	public String locationOf(int participantId) {
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();
		return participantLookingFor.getLocation();
	}

	public String chargeOf(int participantId) {
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();
		return  (100-(participantLookingFor.getConsume()*100/participantLookingFor.getBatterySize()))+"%";
	}
	
	public void charge(int participantId, int hoursOfCharge) {
    	throw new UnsupportedOperationException("Still to be implemented");
	}

}
