package goooey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class NeutralAgent {
	String AgentName;
	ArrayList<File> Target = new ArrayList<File>();
	ArrayList<File> Own = new ArrayList<File>();
	Random random = new Random();
	int Satisfaction = random.nextInt(5)+1;
	boolean ReceivedWarning = false; // One warning if caught by PoliceAgent
	boolean MarkedForDeath = false; // removes this agent during next iteration
	boolean FutureUploaderAgent = false; // makes this Agent an UploaderAgent during next iteration
	boolean FutureDownloaderAgent = false; // makes this Agent an DownloaderAgent during next iteration
	boolean FuturePoliceAgent = false; // makes this Agent an PoliceAgent during next iteration

	public String getAgentName() {
		return this.AgentName;
	}

	public void setAgentName(String newAgentName) {
		this.AgentName = newAgentName;
	}

	public void SendRequest(ArrayList<NeutralAgent> ExistingAgents) {
		// if the Agent doesn't have alot of files, it will become a downloader
		if (Own.size() > (ManagerAgent.ExistingFiles.size())-(Own.size())) {
			// This "if" is for when the Agent has no target files remaining
			if (this.Target.size() > 0) {
				// shuffle used to randomly select target file
				Collections.shuffle(Target);
				File TargetFile = Target.get(0);
				// The temp Array stops the Agent sending a request to itself
				ArrayList<NeutralAgent> tmpExistingAgents = new ArrayList<NeutralAgent>(
						ExistingAgents);
				tmpExistingAgents.remove(this);
				// shuffle used to randomly select target Agent
				Collections.shuffle(tmpExistingAgents);
				NeutralAgent TargetAgent = tmpExistingAgents.get(0);
				// request is then sent via the managerAgent
				ManagerAgent.ProcessSendRequest(NeutralAgent.this, TargetFile,
						TargetAgent);
			} else {
				// The "else" removes the Agent from the system once they have
				// no more files to target
				ManagerAgent.UnSubscribe(this);
			}
		} else {
			try {
				Thread.sleep(ManagerAgent.dly);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			System.out.println(this.AgentName
					+ " has very few files and will now focus on downloading");
			FutureDownloaderAgent = true;
			++ManagerAgent.DownloadersGenerated;
		}
	}

	public void RecieveRequest(File File, NeutralAgent Sender) {
		// This method handles an incoming request for a file
		if (Own.contains(File) == true) {
			// This confirms the request when this agent has the file
			ManagerAgent.ProcessAccept(Sender, File, NeutralAgent.this);
		} else {
			// This rejects the request when this Agent doesn't have the File
			ManagerAgent.ProcessReject(Sender, File, NeutralAgent.this);
		}
	}

	public void RecieveAccept(NeutralAgent target, File File) {
		// when this Agents request is accepted the File is added to their owned files
		Own.add(File);
		// This file is removed from the list of files the agent will request in the future
		Target.remove(File);
		++Satisfaction;
		// After this, the next SendRequest method is called by some other Agent
	}

	public void RecieveReject() {
		// When the Agent request is not met, satisfaction drops
		--Satisfaction;
		// if Satisfaction drops to zero, this is where the Agent leaves the system
		if (Satisfaction < 0) {
			TerminateAgent();
		}
		// After this, the next SendRequest method is called by some other Agent
	}

	public void TerminateAgent() {
		// This removes the Agent from the system
		ManagerAgent.UnSubscribe(this);
	}

}