package goooey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class PoliceAgent extends NeutralAgent {
	
	boolean Discovered = false;
	boolean BeatTheSystem = false;

	public PoliceAgent(NeutralAgent agent) {
		this.AgentName = "Police"+agent.AgentName;
		this.Own = new ArrayList<File>();
		this.Target = ManagerAgent.ExistingFiles;
		this.Satisfaction = 10;
		this.FuturePoliceAgent = false;
	}

	public PoliceAgent() {
		this.AgentName = "Police"+ManagerAgent.ActiveAgents.size();
		this.FuturePoliceAgent = false;
	}

	public void SendRequest(ArrayList<NeutralAgent> ExistingAgents) {
		//Need to check if only PoliceAgents remaining
		for (NeutralAgent a : ManagerAgent.ActiveAgents) {
			if ((a instanceof PoliceAgent) == false){
				// as soon as one not PoliceAgent has been identified, this loop is broken
				BeatTheSystem = false;
				break;
			}
			else {
				BeatTheSystem = true;
			}
		}
		// if Agents other than PoliceAgents still exist
		if (BeatTheSystem == false) {
		// First "if" is for when the Agent has no target files remaining
		if(Discovered == false){
		// shuffle used to randomly select target file
			Target = ManagerAgent.ExistingFiles;
		Collections.shuffle(Target);
		File TargetFile = Target.get(0);
		// shuffle used to randomly select target Agent
		Collections.shuffle(ExistingAgents);
		NeutralAgent TargetAgent = ExistingAgents.get(0);
		// The while loop stops the Agent sending a request to itself
		while (TargetAgent instanceof PoliceAgent) {
			Collections.shuffle(ExistingAgents);
			TargetAgent = ExistingAgents.get(0);
		}
		// request is then sent via the managerAgent
		ManagerAgent.ProcessSendRequest(this, TargetFile, TargetAgent);
		}
		else {
			try {
				Thread.sleep(ManagerAgent.dly);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			// The "else" removes the PoliceAgent from the system if they have been discovered
			System.out.println(this.AgentName+" has been identified in the system and will be removed");
			++ManagerAgent.PoliceAgentsDiscovered;
			ManagerAgent.UnSubscribe(this);
		}
	}
		// the "else" for when only PoliceAgents remain, it ends the system
		else {
			if (ManagerAgent.PoliceVictory == false) {
			try {
				Thread.sleep(ManagerAgent.dly);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			System.out.println("Police have overtaken the system");
			ManagerAgent.PoliceVictory = true;
			ManagerAgent.CapNotification = true;
			for (NeutralAgent a : ManagerAgent.ActiveAgents) {
				a.MarkedForDeath = true;
			} 
			} else {}
		}
	}
	
	public void RecieveRequest(File File, NeutralAgent Sender) {
			// PoliceAgent Automatically rejects file requests
			ManagerAgent.ProcessReject(Sender, File, this);
	}
	
	// If other Agent sends file to PoliceAgent, they are considered "caught"
	public void RecieveAccept(NeutralAgent target, File File) {
		// If the Agent hasn't received a warning yet, it gets one here
		if(target.ReceivedWarning == false) {
		target.ReceivedWarning = true;
		try {
			Thread.sleep(ManagerAgent.dly);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println(this.AgentName+" has issued a warning to "+target.AgentName);
		++ManagerAgent.WarningsIssued;
		// There is a chance the PoliceAgent is caught issuing a warning
		Random random = new Random();
		int suspicious = random.nextInt(100);
		if (suspicious>70){
			try {
				Thread.sleep(ManagerAgent.dly);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			System.out.println(this.AgentName+" has been discovered as a PoliceAgent");
			Discovered = true;
		}
		}
		// If Agent has previous warning, they are forced to leave the system
		else {
			try {
				Thread.sleep(ManagerAgent.dly);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			System.out.println(this.AgentName+" has issued a takedown to "+target.AgentName);
			// There is a larger chance the PoliceAgent is caught issuing a take-down
			Random random = new Random();
			int suspicious = random.nextInt(100);
			if (suspicious>50){
				try {
					Thread.sleep(ManagerAgent.dly);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				++ManagerAgent.TakedownsIssued;
				System.out.println(this.AgentName+" has been discovered as a PoliceAgent");
				Discovered = true;
			}
			target.TerminateAgent();
		}
	}
	
	public void RecieveReject() {
		this.Satisfaction = 10; // Stops PoliceAgent leaving the system due to rejected requests
	}
	
	public void TerminateAgent() {
		// This removes the Agent from the system
		ManagerAgent.UnSubscribe(this);
	}
}
