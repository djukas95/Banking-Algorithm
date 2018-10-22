package Main;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Unesite procese:(A,B,C,maxA,maxB,maxC)");
	    String[] procesi = new Scanner(System.in).nextLine().split(" ");
	    System.out.println("Unesite globalne resure:(A,B,C)");
	    String[] resursi = new Scanner(System.in).nextLine().split(",");

	    String firstProcess = procesi[0];
	    String secondProcess = procesi[1];
	    String thirdProcess = procesi[2];
	    String fourthProcess = procesi[3];
	    String fiveProcess = procesi[4];

	    Integer[] firstArr = new Integer[6];
	    Integer[] secondArr = new Integer[6];
	    Integer[] thirdArr = new Integer[6];
	    Integer[] fourthArr = new Integer[6];
	    Integer[] fiveArr = new Integer[6];

	    for(int i=0;i<6;i++){
            firstArr[i] = Integer.parseInt(firstProcess.split(",")[i]);
            secondArr[i] = Integer.parseInt(secondProcess.split(",")[i]);
            thirdArr[i] = Integer.parseInt(thirdProcess.split(",")[i]);
            fourthArr[i] = Integer.parseInt(fourthProcess.split(",")[i]);
            fiveArr[i] = Integer.parseInt(firstProcess.split(",")[i]);
        }

        Map<Integer,Integer[]> processList = new LinkedHashMap<>();
	    processList.put(1,firstArr);
	    processList.put(2,secondArr);
	    processList.put(3,thirdArr);
	    processList.put(4,fourthArr);
	    processList.put(5,firstArr);

	    int AG = Integer.parseInt(resursi[0]);
	    int BG = Integer.parseInt(resursi[1]);
	    int CG = Integer.parseInt(resursi[2]);

		System.out.println("Unesite redni broj zahtjeva: ");
		int zahtjev = new Scanner(System.in).nextInt();

		System.out.println("Unesite nove zahtjeve:(A,B,C)");
		String[] noviZahtjevi = new Scanner(System.in).nextLine().split(",");
		Integer[] odabraniProces = processList.get(zahtjev);

		odabraniProces[0] += Integer.parseInt(noviZahtjevi[0]);
		odabraniProces[1] += Integer.parseInt(noviZahtjevi[1]);
		odabraniProces[2] += Integer.parseInt(noviZahtjevi[2]);

		AG -= Integer.parseInt(noviZahtjevi[0]);
		BG -= Integer.parseInt(noviZahtjevi[1]);
		CG -= Integer.parseInt(noviZahtjevi[2]);
		System.out.println(AG+" "+BG+" "+CG);

		processList.remove(zahtjev);
		processList.put(zahtjev,odabraniProces);

	    boolean success = false;
	    while(processList.size() > 0 && success == false){
	        for(Integer[] resources : processList.values()){
	            if((resources[3] - resources[0]) <= AG && (resources[4]-resources[1]) <= BG && (resources[5]-resources[2]) <= CG){
	                success=true;
	                AG += resources[0];
	                BG += resources[1];
	                CG += resources[2];

	                int id=0;
	                for(Integer tempKey: processList.keySet()){
	                    if(processList.get(tempKey) == resources) id=tempKey;
                    }
                    System.out.println("Zahtjev "+ id+" je uspjesno izvrsen!");
	                System.out.println("Trenutno stanje globalnih resursa: ("+AG+", "+BG+", "+CG+" )");

	                processList.remove(id);
	                break;
                }
            }
            if(!success){
	            System.out.println("Sistem nije u stabilnom stanju!");
	            break;
            }
            success=false;
        }
    }
}
