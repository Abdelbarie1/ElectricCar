package com.sqli.exercise.electric_trip;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class ElectricTrip {

	private final String pathDesc;

	private List<Participant> participants=new ArrayList<>();



	public ElectricTrip(String pathDesc) {
		this.pathDesc=pathDesc;
	}

	public int startTripIn(String startCityName, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {
		String[] pathDescSeparated=pathDesc.split("-");
		int indexBeginPath=0;
		while (!pathDescSeparated[indexBeginPath].equals(startCityName)){
			indexBeginPath+=2;
		}
		List<Path> paths1=new ArrayList<>();
		for( int count=indexBeginPath;count<pathDescSeparated.length-1;count+=2) {
			City startLocation=new City();
			City endLocation=new City();
			if(pathDescSeparated[count].split(":").length>1){
				startLocation.setCityName(pathDescSeparated[count].split(":")[0]);
				startLocation.setKwhChargedPerHour(Double.parseDouble(pathDescSeparated[count].split(":")[1]));
				startLocation.setChargePlace(true);
			}else {
				startLocation.setCityName(pathDescSeparated[count]);
			}
			if(pathDescSeparated[count+2].split(":").length>1){
				endLocation.setCityName(pathDescSeparated[count+2].split(":")[0]);
				endLocation.setKwhChargedPerHour(Double.parseDouble(pathDescSeparated[count+2].split(":")[1]));
				endLocation.setChargePlace(true);
			}else {
				endLocation.setCityName(pathDescSeparated[count+2]);
			}
			Path path=new Path(startLocation,Integer.parseInt(pathDescSeparated[count+1]),endLocation);
			paths1.add(path);
		}
		Participant participant=new Participant(this.participants.size()+1,startCityName,batterySize,lowSpeedPerformance,highSpeedPerformance,paths1);
		participant.setCharge(participant.getBatterySize());
		this.participants.add(participant);
		return participant.getId();
	}

	public void go(int participantId) {
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();
		List<Path> paths=participantLookingFor.getPaths();
		for (int count = 0; count < paths.size(); count++) {
				if (isEnoughtCharge(paths.get(count), participantLookingFor.getCharge(), participantLookingFor.getLowSpeedPerformance())) {
					consome(participantLookingFor, paths.get(count), participantLookingFor.getLowSpeedPerformance());
					participantLookingFor.setLocation(paths.get(count).getArriveLocation());
				}

		}
	}


	public void consome(Participant participant,Path path, int speed){
		//participant.setConsume(participant.getConsume()+(path.getDistance()/speed));
		participant.setCharge(participant.getCharge()-path.getDistance()/speed);
	}

	public void sprint(int participantId) {
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();
		List<Path> paths=participantLookingFor.getPaths();
		int indexBeginPath=0;
		String startCityName=participantLookingFor.getLocation().getCityName();
		if(!StringUtils.equals(startCityName,"NAN")){
			while (!StringUtils.equals(startCityName
					,participantLookingFor.getPaths().get(indexBeginPath).getStartLocation().getCityName())
					&& indexBeginPath<participantLookingFor.getPaths().size()-1){
				indexBeginPath++;
			}
		}

		for(int count=indexBeginPath;count<paths.size();count++){
			if(isEnoughtCharge(paths.get(count),participantLookingFor.getCharge(),participantLookingFor.getHighSpeedPerformance())){
				consome(participantLookingFor,paths.get(count),participantLookingFor.getHighSpeedPerformance());
				participantLookingFor.setLocation(paths.get(count).getArriveLocation());
			}
		}
	}

	public String locationOf(int participantId) {
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();
		return participantLookingFor.getLocation().getCityName();
	}

	public String chargeOf(int participantId) {
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();
		return  (int)Math.round(((participantLookingFor.getCharge()/participantLookingFor.getBatterySize())*100))+"%";
	}
	
	public void charge(int participantId, int hoursOfCharge) {
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();
		if(participantLookingFor.getLocation().isChargePlace()){
			participantLookingFor.setCharge(participantLookingFor.getCharge()+(hoursOfCharge*participantLookingFor.getLocation().getKwhChargedPerHour()));
			System.out.println(participantLookingFor.getCharge());
		}
		System.out.println(participantLookingFor.getCharge());
	}

	public  boolean isEnoughtCharge(Path path,double charge ,int speed){
		return charge>=(path.getDistance()/speed)?true:false;
	}

}
