package com.sqli.exercise.electric_trip;

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
			Path path=new Path(pathDescSeparated[count],Integer.parseInt(pathDescSeparated[count+1]),pathDescSeparated[count+2]);
			paths1.add(path);
		}
		Participant participant=new Participant(this.participants.size()+1,startCityName,batterySize,lowSpeedPerformance,highSpeedPerformance,paths1);
		this.participants.add(participant);
		return participant.getId();
	}

	public void go(int participantId) {
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();
		List<Path> paths=participantLookingFor.getPaths();
		for (int count = 0; count < paths.size(); count++) {
				double availableCharge = participantLookingFor.getBatterySize() - participantLookingFor.getConsume();
				if (isEnoughtCharge(paths.get(count), availableCharge, participantLookingFor.getLowSpeedPerformance())) {

					consome(participantLookingFor, paths.get(count), participantLookingFor.getLowSpeedPerformance());
					participantLookingFor.setLocation(paths.get(count).getArriveLocation());
				}

			}
			System.out.println("Available Consume is : " + participantLookingFor.getConsume());

		}


	public void consome(Participant participant,Path path, int speed){
		participant.setConsume(participant.getConsume()+(path.getDistance()/speed));
	}

	public void sprint(int participantId) {
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();
		List<Path> paths=participantLookingFor.getPaths();
		for(int count=0;count<paths.size();count++){
			double availableCharge=participantLookingFor.getBatterySize()-participantLookingFor.getConsume();
			if(isEnoughtCharge(paths.get(count),availableCharge,participantLookingFor.getHighSpeedPerformance())){
				consome(participantLookingFor,paths.get(count),participantLookingFor.getHighSpeedPerformance());
				participantLookingFor.setLocation(paths.get(count).getArriveLocation());
			}
		}
	}

	public String locationOf(int participantId) {
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();
		return participantLookingFor.getLocation();
	}

	public String chargeOf(int participantId) {
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();
		return  (int)Math.round(100-((participantLookingFor.getConsume()/participantLookingFor.getBatterySize())*100))+"%";
	}
	
	public void charge(int participantId, int hoursOfCharge) {
    	throw new UnsupportedOperationException("Still to be implemented");
	}

	public  boolean isEnoughtCharge(Path path,double charge ,int speed){
		return charge>=(path.getDistance()/speed)?true:false;
	}

}
