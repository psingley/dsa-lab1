package lab;
import java.util.Arrays;

public class Player {

	
	public Player(){
	
	}
	
	String[][] frames = {
			{"A"},
			{"111"},
			{"0","9","0","9"},
			{"0","9"},
			{"0","9"},
			{"0","9"},
			{"0","9"},
			{"0","9"},
			{"0","9"},
			{"0","9","X"}
	};
	
	int numSpares = 0;
	int numStrikes = 0;
	int frameIndex = 0;
	boolean bonusFrame = false;
	boolean junkDataWasIgnored = false;
	String errorOutput = "";

	public Player(String[][] parsedFrames){
		
		this.frames = parsedFrames;
		
	}
	
	public String getScore(){
		
		 int score = 0;
		 while (frameIndex < 8){
			 try{
				 if(calcFrame(frames[frameIndex])==10  && frames[frameIndex][0]=="X"){
					 if(frames[frameIndex+1][0]=="X"){
						 System.out.println("Current score is: " + score);
						 System.out.println("Attempting to calculate score for Strike at line " + getCurrentFrame() + " using these bowls: "  + Arrays.deepToString(frames[frameIndex]) + ", " + Arrays.deepToString(frames[frameIndex+1]) + ", " + "[" + frames[frameIndex+2][0].toString() + "]" );
						 if(frames[frameIndex+2][0]!="/"&&(frames[frameIndex+2][0]=="X"||calcBowl(frames[frameIndex+2][0])>= 0 && calcBowl(frames[frameIndex+2][0])<10)){
							 score = score + calcFrame(frames[frameIndex]) + calcFrame(frames[frameIndex+1]) + calcBowl(frames[frameIndex+2][0]);
						 } else {
							 score = score + calcFrame(frames[frameIndex]) + calcFrame(frames[frameIndex+1]) + calcFrame(frames[frameIndex+2]);
						 }
						 System.out.println("New score is: " + score);
						 if(errorOutput!=""){
							 System.out.println("Calculation at line " + getCurrentFrame() + " was adjusted to include one or more gutters because of the following error: ");
							 System.out.println(errorOutput);
						 }
						 if(junkDataWasIgnored){
							 System.out.println("Frame at line " + getCurrentFrame() + " included junk data which was ignored");
							 junkDataWasIgnored=false;
						 }
						 numStrikes++;
						 frameIndex++;
						 errorOutput="";
					 }else {
						 System.out.println("Current score is: " + score);
						 System.out.println("Attempting to calculate score for Strike at line " + getCurrentFrame() + " using these bowls: " + Arrays.deepToString(frames[frameIndex]) + ", " + Arrays.deepToString(frames[frameIndex+1]));
						 score = score + calcFrame(frames[frameIndex]) + calcFrame(frames[frameIndex+1]);
						 System.out.println("New score is: " + score);
						 if(errorOutput!=""){
							 System.out.println("Calculation at line " + getCurrentFrame() + " was adjusted to include one or more gutters because of the following error: ");
							 System.out.println(errorOutput);
						 }
						 if(junkDataWasIgnored){
							 System.out.println("Frame at line " + getCurrentFrame() + " included junk data which was ignored");
							 junkDataWasIgnored=false;
						 }
						 numStrikes++;
						 frameIndex++;
						 errorOutput="";
					 }
				 } else if( calcFrame(frames[frameIndex])==10 && calcBowl(frames[frameIndex][0])>= 0 && calcBowl(frames[frameIndex][0])<10 && frames[frameIndex][0]!="X" && frames[frameIndex][1]=="/") {
					 System.out.println("Current score is: " + score);
					 System.out.println("Attempting to calculate score for Spare at line " + getCurrentFrame() + " using these bowls: " + Arrays.deepToString(frames[frameIndex]) + ", " + "[" + frames[frameIndex+1][0].toString() + "]" );
					 score = score + calcFrame(frames[frameIndex]) + calcBowl(frames[frameIndex+1][0]);
					 System.out.println("New score is: " + score);
					 if(errorOutput!=""){
						 System.out.println("Calculation at line " + getCurrentFrame() + " was adjusted to include one or more gutters because of the following error: ");
						 System.out.println(errorOutput);
					 }
					 if(junkDataWasIgnored){
						 System.out.println("Frame at line " + getCurrentFrame() + " included junk data which was ignored");
						 junkDataWasIgnored=false;
					 }
					 numSpares++;
					 frameIndex++;
					 errorOutput="";
				 } else if(calcFrame(frames[frameIndex])<10&&calcBowl(frames[frameIndex][0])>= 0 && calcBowl(frames[frameIndex][0])<10 && calcBowl(frames[frameIndex][1])>= 0 && calcBowl(frames[frameIndex][1])<10 && frames[frameIndex][0]!="X" && frames[frameIndex][1]!="/") {
					 System.out.println("Current score is: " + score);
					 System.out.println("Attempting to calculate score for bowls at line " + getCurrentFrame() + " using these bowls: " + Arrays.deepToString(frames[frameIndex]));
					 score = score + calcFrame(frames[frameIndex]);
					 System.out.println("New score is: " + score);
					 if(errorOutput!=""){
						 System.out.println("Calculation at line " + getCurrentFrame() + " was adjusted to include one or more gutters because of the following error: ");
						 System.out.println(errorOutput);
					 }
					 if(junkDataWasIgnored){
						 System.out.println("Frame at line " + getCurrentFrame() + " included junk data which was ignored");
						 junkDataWasIgnored=false;
					 }
					 frameIndex++;
					 errorOutput="";
				 } else {
					 errorOutput = ("Malformed frame data at line " + getCurrentFrame() + ", guttered for calculation");
					 System.out.println(errorOutput);
					 frameIndex++;
					 errorOutput="";
				 }
			 } catch(Exception e){
				 System.out.println(errorOutput);
				 frameIndex++;
			 }
			 
		 }
		 
		 while(frameIndex==8){ //POTENTIAL WEIRDNESS FROM BONUS BOWL HANDLING
			 if(calcFrame(frames[frameIndex]) == 10 && frames[frameIndex][0]=="X"){
			 	System.out.println("Current score is: " + score);
			 	System.out.println("Attempting to calculate score for Strike at line " + getCurrentFrame() + " using these bowls: "  + Arrays.deepToString(frames[frameIndex]) + ", " + "[" + frames[frameIndex+1][0].toString() + "]" + ", " + "[" + frames[frameIndex+1][1].toString() + "]"); 
			 	score = score + calcFrame(frames[frameIndex]) + calcBowl(frames[frameIndex+1][0]) + calcBowl(frames[frameIndex+1][1]);
			 	System.out.println("New score is: " + score);
				 if(errorOutput!=""){
					 System.out.println("Calculation at line " + getCurrentFrame() + "was adjusted to include one or more gutters because of the following error: ");
					 System.out.println(errorOutput);
				 }
				 if(junkDataWasIgnored){
					 System.out.println("Frame at line " + getCurrentFrame() + " included junk data which was ignored");
					 junkDataWasIgnored=false;
				 }
				 numStrikes++;
				 frameIndex++;
				 errorOutput="";
			 } else if( calcFrame(frames[frameIndex])==10 && calcBowl(frames[frameIndex][0])>= 0 && calcBowl(frames[frameIndex][0])<10 && frames[frameIndex][0]!="X" && frames[frameIndex][1]=="/") {
				 System.out.println("Current score is: " + score);
				 System.out.println("Attempting to calculate score for Spare at line " + getCurrentFrame() + " using these bowls: " + Arrays.deepToString(frames[frameIndex]) + ", " + "[" + frames[frameIndex+1][0].toString() + "]" );
				 score = score + calcFrame(frames[frameIndex]) + calcBowl(frames[frameIndex+1][0]);
				 System.out.println("New score is: " + score);
				 if(errorOutput!=""){
					 System.out.println("Calculation at line " + getCurrentFrame() + " was adjusted to include one or more gutters because of the following error: ");
					 System.out.println(errorOutput);
				 }
				 if(junkDataWasIgnored){
					 System.out.println("Frame at line " + getCurrentFrame() + " included junk data which was ignored");
					 junkDataWasIgnored=false;
				 }
				 numSpares++;
				 frameIndex++;
				 errorOutput="";
			 } else if( calcBowl(frames[frameIndex][0])>= 0 && calcBowl(frames[frameIndex][0])<10 && calcBowl(frames[frameIndex][1])>= 0 && calcBowl(frames[frameIndex][1])<10 && frames[frameIndex][0]!="X" && frames[frameIndex][1]!="/") {
				 System.out.println("Current score is: " + score);
				 System.out.println("Attempting to calculate score for bowls at line " + getCurrentFrame() + " using these bowls: " + Arrays.deepToString(frames[frameIndex]));
				 score = score + calcFrame(frames[frameIndex]);
				 System.out.println("New score is: " + score);
				 if(errorOutput!=""){
					 System.out.println("Calculation at line " + getCurrentFrame() + "was adjusted to include one or more gutters because of the following error: ");
					 System.out.println(errorOutput);
				 }
				 if(junkDataWasIgnored){
					 System.out.println("Frame at line " + getCurrentFrame() + " included junk data which was ignored");
					 junkDataWasIgnored=false;
				 }
				 frameIndex++;
				 errorOutput="";
			 } else {
				 errorOutput=("Malformed frame data at line " + getCurrentFrame() + ", guttered for calculation" );
				 System.out.println(errorOutput);
				 frameIndex++;
				 errorOutput="";
			 }
		 }
		
		 while(frameIndex==9){ //POTENTIAL BONUS BOWL HANDLING
			 if(calcBonusFrame(frames[frameIndex]) >= 10 && frames[frameIndex][0]=="X"){
					 bonusFrame=true;
					 System.out.println("Current score is: " + score);
					 System.out.println("Attempting to calculate score for Strike at line " + getCurrentFrame() + " using these bowls: " + Arrays.deepToString(frames[frameIndex]));
					 score = score + calcBonusFrame(frames[frameIndex]);
					 System.out.println("New score is: " + score);
					 if(junkDataWasIgnored){
						 System.out.println("Final Frame included junk data which was ignored");
						 junkDataWasIgnored=false;
					 }
				 	 numStrikes++;
				 	 if(frames[frameIndex][1]=="X"){
				 		 numStrikes++;
				 		System.out.println("TWO IN A ROW!");
				 		 if(frames[frameIndex][2]=="X")
				 		 {
				 			 numStrikes++;
				 			System.out.println("THREE IN A ROW!");
				 			if(getNumStrikes()==12){
				 				System.out.println("!!!!PERFECT GAME!!!!");
				 			}
				 		 }
				 	 }
					 frameIndex++;
			 } else if( calcBonusFrame(frames[frameIndex]) >=10 && calcBowl(frames[frameIndex][0])>= 0 && calcBowl(frames[frameIndex][0])<10 && frames[frameIndex][0]!="X" && frames[frameIndex][1]=="/") {
				 bonusFrame=true;
				 System.out.println("Current score is: " + score);
				 System.out.println("Attempting to calculate score for Spare at line " + getCurrentFrame() + " using these bowls: " + Arrays.deepToString(frames[frameIndex]));
				 score = score + calcBonusFrame(frames[frameIndex]);
				 System.out.println("New score is: " + score);
			  	 if(frames[frameIndex][2]=="X"){
		 			 numStrikes++;
		 		  }
		         if(junkDataWasIgnored){
					 System.out.println("Final Frame included junk data which was ignored");
					 junkDataWasIgnored=false;
				 }
			  	 if(errorOutput!=""){
			 	     System.out.println("Calculation at line " + getCurrentFrame() + " was adjusted to include one or more gutters because of the following error: ");
				     System.out.println(errorOutput);
				  }
				 frameIndex++;
			 } else if(!isBonusFrame(frames[frameIndex])&& calcBowl(frames[frameIndex][0])>= 0 && calcBowl(frames[frameIndex][0])<10 && calcBowl(frames[frameIndex][1])>= 0 && calcBowl(frames[frameIndex][1])<10 && frames[frameIndex][0]!="X" && frames[frameIndex][1]!="/") {
				 System.out.println("Current score is: " + score);
				 System.out.println("Attempting to calculate score for bowls at line " + getCurrentFrame() + " using these bowls: " + Arrays.deepToString(frames[frameIndex]));
				 score = score + calcFrame(frames[frameIndex]);
				 System.out.println("New score is: " + score);
				 if(junkDataWasIgnored){
					 System.out.println("Final Frame included junk data which was ignored");
					 junkDataWasIgnored=false;
				 }
				 if(errorOutput!=""){
					 System.out.println("Calculation at line " + getCurrentFrame() + " was adjusted to include one or more gutters because of the following error: ");
					 System.out.println(errorOutput);
				 }
				 frameIndex++;
			 } else {
				 errorOutput="Malformed data in final frame";
				 System.out.println(errorOutput);
				 frameIndex++;
			 }
		 }
		 
		 return Integer.toString(score);
	}
	
	public int getNumStrikes(){
		return numStrikes;
		
	}
	
	public int getNumSpares(){
		return numSpares;
		
	}
	
	private int calcFrame(String[] frames)
	{
		int parsedBowl1 = 0;
		int parsedBowl2 = 0;
		
		boolean isStrikeFrame = false;
		if(frames.length==1){
			isStrikeFrame = true;
		}
		
		if (frames.length>2){
			junkDataWasIgnored = true;
		}
		
		
		
		if(frames[0]=="X"&&!isStrikeFrame&&!bonusFrame){
			errorOutput=("Impossible frame: bowled pins indicated after Strike in non-bonus frame. Guttered for calculation");
			return 0;
		} else if (frames[0]=="X"&&isStrikeFrame&&!bonusFrame) {
			return 10;
		} else if (frames[0]=="/") {
			errorOutput=("Impossible frame: Spare indicated at beginning of frame. Guttered for calculation");
			return 0;
	    } else if (frames[0]!="X"&&isStrikeFrame) {
	    	errorOutput=("Impossible frame: Non-strike indicated in single-bowl frame, "  + frames[0] + "Guttered for calculation");
	    	return 0;
		} else { 	
			if(!isStrikeFrame){
				if(frames[1]=="/"){
					return 10;
				} else {
				try{
					parsedBowl1 = Integer.parseInt(frames[0]);
				} catch (Exception ImpossibleBowl1){
					errorOutput=("Skipping frame because " + frames[0] + " is not a number. Guttered for calculation");
					return 0;
				}
				try{
					parsedBowl2 = Integer.parseInt(frames[1]);
				} catch (Exception ImpossibleBowl1){
					errorOutput=("Skipping frame because " + frames[1] + " is not a number. Guttered for calculation");
					return 0;
				}
				if((parsedBowl1<0||parsedBowl1>9)){
					errorOutput=("Skipping frame because " + parsedBowl1 + " is an unrecognized pin value. Guttered for calculation");
					return 0;
				} else if ((parsedBowl2<0||parsedBowl2>9)){
					errorOutput=("Skipping frame because " + parsedBowl2 + " is an unrecognized pin value. Guttered for calculation");
					return 0;
				} else if (parsedBowl1+parsedBowl2>10) {
					errorOutput=("Skipping frame because " + parsedBowl1 + " and " + parsedBowl2 + " is an impossible pin combination. Guttered for calculation");
					return 0;
				} else {
					return parsedBowl1 + parsedBowl2;
				}
			}
			}
		}
		errorOutput=("Skipping frame because of improperly formed data: " + frames + ". Guttered for calculation");
		return 0;
	}
	
	private int calcBowl(String bowl)
	{
		try{
			int parsedBowl = 0;
			if(bowl=="X"){
				return 10;
				
			} else if(bowl=="/"){
				return 10;
				
			} else {
				try{
					parsedBowl = Integer.parseInt(bowl);
				} catch (Exception ImpossibleBowl1){
					errorOutput=("Skipping this frame, " + bowl + " is not a number. Guttered for calculation");
					return 0;
				}
				if((parsedBowl<0||parsedBowl>9)){
					errorOutput=("Skipping this frame, " + bowl + " is an unrecognized pin value. Guttered for calculation");
					return 0;
				} else {
					return parsedBowl;
				}
			}
		} catch (Exception e) {
			System.out.println(errorOutput);
			return 0;
		}
	}
	
	private int calcBonusFrame(String[] frames)
	{
		int parsedBowl1 = 0;
		int parsedBowl2 = 0;
		int parsedBowl3 = 0;
		
		if (frames.length>3){
			junkDataWasIgnored = true;
		}
		
		
		if(frames[0]=="X"){
			parsedBowl1 = 10;
		} else {
			try{
				parsedBowl1 = Integer.parseInt(frames[0]);
			} catch (Exception ImpossibleBowl1){
				errorOutput=("Skipping frame at " + getCurrentFrame() + " because " + frames[0] + " is not a number. Must be a valid pin amount or a Strike. Guttered for calculation");
				return 0;
			}
			if((parsedBowl1<0||parsedBowl1>9)){
				errorOutput=("Skipping frame at " + getCurrentFrame() + " because " + parsedBowl1 + " is an unrecognized pin value. Guttered for calculation");
				return 0;
			} 			
		}
		
		if(frames[1]=="/"&&parsedBowl1!=10){
			parsedBowl2 = 10;
		} else if(frames[1]=="X"&&parsedBowl1==10) {
			parsedBowl2 = 10;
		} else {
			try{
				parsedBowl2 = Integer.parseInt(frames[1]);
			} catch (Exception ImpossibleBowl1){
				errorOutput=("Skipping frame because " + frames[1] + " is not a number. Guttered for calculation");
				return 0;
			}
			if ((parsedBowl2<0||parsedBowl2>9)){
				errorOutput=("Skipping frame because " + parsedBowl2 + " is an unrecognized pin value. Guttered for calculation");
				return 0;
			}
		}
		
		if(frames[2]=="/" && parsedBowl2==10){
			errorOutput=("Skipping this frame, can not roll a spare directly after a strike or spare. Guttered for calculation");
		} else if (frames[2]=="X"){
			parsedBowl3 = 10;
		} else if (frames[2]=="/" && parsedBowl2!=10){
			parsedBowl3 = 10;
		} else {
			try{
				parsedBowl3 = Integer.parseInt(frames[2]);
			} catch (Exception ImpossibleBowl1){
				errorOutput=("Skipping frame because " + frames[2] + " is not a viable bonus bowl. Guttered for calculation");
				return 0;
			}
			if ((parsedBowl3<0||parsedBowl3>9)){
				errorOutput=("Skipping frame because " + parsedBowl2 + " is an unrecognized pin value. Guttered for calculation");
				return 0;
			}
		}
		
		if (parsedBowl1+parsedBowl2+parsedBowl3>30) {
			errorOutput=("Skipping frame because " + parsedBowl1 + " and " + parsedBowl2 + " and " + parsedBowl3 + " is an impossible pin combination. Guttered for calculation");
			return 0;
		} else {
			return parsedBowl1 + parsedBowl2 + parsedBowl3;
		}
		}
	
	
	private int getCurrentFrame(){
		return frameIndex+1;
	}
	
	private boolean isBonusFrame(String[] frame){
		if(frame.length==3){
			return true;
		} else{
			return false;
		}
		}
	}

