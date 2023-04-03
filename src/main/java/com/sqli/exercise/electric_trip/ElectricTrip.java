package com.sqli.exercise.electric_trip;

import java.util.ArrayList;
import java.util.List;

public class ElectricTrip {

	private final String pathDesc;

	private List<Participant> participants=new ArrayList<>();

	private List<Path> paths =new ArrayList<>();


	public ElectricTrip(String pathDesc) {
		String[] pathDescSeparated=pathDesc.split("-");
		for(int count=0;count<pathDescSeparated.length-1;count+=2) {
			Path path=new Path(pathDescSeparated[count],Integer.parseInt(pathDescSeparated[count+1]),pathDescSeparated[count+2]);
			System.out.println(path);
			paths.add(path);
		}
		this.pathDesc=pathDesc;
	}

	public int startTripIn(String startCityName, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {

		Participant participant=new Participant(this.participants.size()+1,startCityName,batterySize,lowSpeedPerformance,highSpeedPerformance);
		this.participants.add(participant);
		return participant.getId();
	}

	public void go(int participantId) {
		//String[] pathDescSeparated=pathDesc.split("-");
		Participant participantLookingFor= (Participant) this.participants.stream().filter(participant -> participant.getId()==participantId).findFirst().get();

		for(int count=0;count<paths.size();count++){
			int availableCharge=participantLookingFor.getBatterySize()-participantLookingFor.getConsume();
			System.out.println(availableCharge);
			if(isEnoughtCharge(paths.get(count),availableCharge,participantLookingFor.getLowSpeedPerformance())){
				consome(participantLookingFor,paths.get(count));
				participantLookingFor.setLocation(paths.get(count).getArriveLocation());
			}
		}

		/*participantLookingFor.setConsume(consome(participantLookingFor));
		participantLookingFor.setLocation(paths.get(paths.size()-1).getArriveLocation());
*/

		/*this.participants.stream().filter(participant -> participant.getId()==participantId).map(participant ->
				{
					participant.setConsume(consome(participant));
					participant.setLocation(pathDescSeparated[2]);
					System.out.println("--> "+participant.getLocation());
					return participant;
				}
		);*/

	}

	public void consome(Participant participant,Path path){
		participant.setConsume(participant.getConsume()+path.getDistance());
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

	public  boolean isEnoughtCharge(Path path,int charge ,int speed){
		//System.out.println(charge-path.getDistance()/speed);

		return charge-(path.getDistance()/speed)>0?true:false;
	}

}
