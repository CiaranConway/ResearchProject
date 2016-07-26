package goooey;
import java.util.ArrayList;
import java.util.Random;

class ManagerAgent {
	static int TotalActions = 100; // The cut-off point for the session, adjust as needed
	static int dly = 500; // Change if you want messages faster or slower
	static ArrayList<NeutralAgent> ActiveAgents = new ArrayList(); // All the Agents
	static ArrayList<File> ExistingFiles = new ArrayList();	// All the Files
	public static int TotalNumFiles;
	static boolean CapNotification = false; // stops multiple notifications for system shutdown
	static boolean PoliceVictory = false; // stops multiple notifications after PoliceAgent dominance
	// The below integers are sent back to the tester once the system shuts down
	public static int RequestsSent = 0;
	public static int RequestsAccepted = 0;
	public static int RequestsRejected = 0;
	public static int AgentsLeft = 0;
	
	public static int UploadersGenerated = 0;
	public static int FilesAdded = 0;
	
	public static int PoliceGenerated = 0;
	public static int WarningsIssued = 0;
	public static int TakedownsIssued = 0;
	public static int PoliceAgentsDiscovered = 0; // value is not being passed
	
	public static int DownloadersGenerated = 0;
	public static int NewAgentsAdded = 0;
	
	public static int TotalRemainingAgents = 0;

	
public static void ProcessSendRequest(NeutralAgent Sender, File File, NeutralAgent Target) {
	// The TotalActions is checked before each request is allowed to continue
	if (TotalActions>0) {
		// the try catch is a basic delay between message posting
	try {
		Thread.sleep(dly);
	} catch (InterruptedException e) {
		Thread.currentThread().interrupt();
	}
	System.out.println(Sender.getAgentName() + " wants " + File.getFileName() + " from " + Target.getAgentName() +"."); // This will be replaced by UI display
	--TotalActions;
	++RequestsSent;
	// values for Sender, File and Target are sent from SendRequest method in Sender Agent.
	ProcessRecieveRequest(Sender, File, Target);
	}
	else{
		// How we end the system when TotalActions is reduced to zero
		if(CapNotification == false) {
		System.out.println("Cap on actions has been reached");
		CapNotification = true;
		TotalRemainingAgents = ActiveAgents.size();
		for (NeutralAgent a : ActiveAgents) {
			a.MarkedForDeath = true;
		} 
		} else {}
	}
}

public static void ProcessRecieveRequest(NeutralAgent Sender, File File, NeutralAgent Target) {
	// The TotalActions is checked before each request is allowed to continue
	if (TotalActions>0) {
		// the try catch is a basic delay between message posting
	try {
		Thread.sleep(dly);
	} catch (InterruptedException e) {
		Thread.currentThread().interrupt();
	}
	System.out.println(Target.getAgentName() + " recieved request for " + File.getFileName() + " from " + Sender.getAgentName() +"."); // This will be replaced by UI display
	--TotalActions;
	// values for Sender, File and Target are sent from RecieveRequest method in Target Agent.
	Target.RecieveRequest(File, Sender);
	}
	else {
		// How we end the system when TotalActions is reduced to zero
		if(CapNotification == false) {
		System.out.println("Cap on actions has been reached");
		CapNotification = true;
		TotalRemainingAgents = ActiveAgents.size();
		for (NeutralAgent a : ActiveAgents) {
			a.MarkedForDeath = true;
		} 
		} else {}
	}
}

public static void ProcessAccept(NeutralAgent Sender, File File, NeutralAgent Target) {
	// The TotalActions is checked before each request is allowed to continue
	if (TotalActions>0) {
		// the try catch is a basic delay between message posting
	try {
		Thread.sleep(dly);
	} catch (InterruptedException e) {
		Thread.currentThread().interrupt();
	}
	System.out.println(Target.getAgentName() + " has " + File.getFileName() + " and will send it to " + Sender.getAgentName() +"."); // This will be replaced by UI display
	Sender.RecieveAccept(Target, File);
	--TotalActions;
	++RequestsAccepted;
	}
	else {
		// How we end the system when TotalActions is reduced to zero
		if(CapNotification == false) {
		System.out.println("Cap on actions has been reached");
		CapNotification = true;
		TotalRemainingAgents = ActiveAgents.size();
		for (NeutralAgent a : ActiveAgents) {
			a.MarkedForDeath = true;
		}
		} else {}
	}
}

public static void ProcessReject(NeutralAgent Sender, File File, NeutralAgent Target) {
	// The TotalActions is checked before each request is allowed to continue
	if (TotalActions>0) {
		// the try catch is a basic delay between message posting
	try {
		Thread.sleep(dly);
	} catch (InterruptedException e) {
		Thread.currentThread().interrupt();
	}
	if (Target instanceof DownloaderAgent){
		System.out.println(Target.getAgentName() + " ignores " + Sender.getAgentName() + "'s request for " + File.getFileName()  +".");
	}
	else {
	System.out.println(Target.getAgentName() + " does not have " + File.getFileName() + " for " + Sender.getAgentName() +"."); // This will be replaced by UI display
	Sender.RecieveReject();
	--TotalActions;
	++RequestsRejected;
	}
	}
	else {
		// How we end the system when TotalActions is reduced to zero
		if(CapNotification == false) {
		System.out.println("Cap on actions has been reached");
		CapNotification = true;
		TotalRemainingAgents = ActiveAgents.size();
		for (NeutralAgent a : ActiveAgents) {
			a.MarkedForDeath = true;
		} 
		} else {}
	}
}
public static void UnSubscribe(NeutralAgent Agent) {
	// the try catch is a basic delay between message posting
	try {
		Thread.sleep(dly);
	} catch (InterruptedException e) {
		Thread.currentThread().interrupt();
	}
	// Agents exit the system under different conditions, which are outlined below
	
	if (Agent.Satisfaction<1){
	System.out.println(Agent.getAgentName() + " is no longer satisfied with the system."); // This will be replaced by UI display
	
	// The Agent has a chance to become a PoliceAgent once they leave the system with zero Satisfaction
	Random random = new Random();
	int vengeful = random.nextInt(100);
	
	if (vengeful>60) {
		try {
			Thread.sleep(dly);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println(Agent.getAgentName() + " has become a Police Agent.");
		Agent.FuturePoliceAgent = true;
		++PoliceGenerated;
	}
	// Agent just leaves normally if they do not become a PoliceAgent
	else{
		try {
			Thread.sleep(dly);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println(Agent.getAgentName() + " has left the system"); // This will be replaced by UI display
		++AgentsLeft;
		--TotalRemainingAgents;
		Agent.MarkedForDeath = true;
	}
	
	}
	
	// if Agent gets all files on the system, they are converted into an UploaderAgent
	else if (Agent.Target.size()<1){
		// This is not applied if the Agent is already an UploaderAgent
		if (Agent instanceof UploaderAgent){
			try {
				Thread.sleep(dly);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			System.out.println(Agent.getAgentName() + " has left the system"); // This will be replaced by UI display
			++AgentsLeft;
			--TotalRemainingAgents;
			Agent.MarkedForDeath = true;
		}
		else{
		try {
			Thread.sleep(dly);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println( Agent.AgentName + " has all files available on the system and has become an Uploader Agent");
		Agent.FutureUploaderAgent = true;
		++UploadersGenerated;
		}
	}
	// Default agent removal
	else {
		try {
			Thread.sleep(dly);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println(Agent.getAgentName() + " has left the system"); // This will be replaced by UI display
		++AgentsLeft;
		--TotalRemainingAgents;
		Agent.MarkedForDeath = true;
	}
 }
}