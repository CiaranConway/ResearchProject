package goooey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DownloaderAgent extends NeutralAgent {

	boolean CreateNewAgent = false;
	boolean CreateNewPoliceAgent = false;

	public DownloaderAgent(NeutralAgent agent) {
		this.AgentName = "Downloader" + agent.AgentName;
		this.Own = agent.Own;
		this.Target = agent.Target;
		this.Satisfaction = agent.Satisfaction;
		this.FutureDownloaderAgent = false;
	}

	public DownloaderAgent() {
		this.AgentName = "Downloader" + ManagerAgent.ActiveAgents.size();
		this.FutureDownloaderAgent = false;
	}

	public void SendRequest(ArrayList<NeutralAgent> ExistingAgents) {
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
			ManagerAgent.ProcessSendRequest(this, TargetFile, TargetAgent);
		} else {
			// The "else" removes the Agent from the system once they have
			// no more files to target
			ManagerAgent.UnSubscribe(this);
		}
	}

	public void RecieveAccept(NeutralAgent target, File File) {
		// when this Agents request is accepted the File is added to their owned
		// files
		Own.add(File);
		// This file is removed from the list of files the agent will request in
		// the future
		Target.remove(File);
		++Satisfaction;
		Random random = new Random();
		int recommend = random.nextInt(100);
		// The DownloaderAgent ReceiveAccept method has a chance of attracting
		// new Agents to the system
		if (recommend > 40) {
			try {
				Thread.sleep(ManagerAgent.dly);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			Random random2 = new Random();
			int alrtPlc = random2.nextInt(100);
			if (alrtPlc > 40) {
				System.out.println(this.AgentName + " has recommended the system to a new Agent.");
				CreateNewAgent = true;
				++ManagerAgent.NewAgentsAdded;
			} else if (alrtPlc > 20) {
			} // does nothing
			else {
				System.out.println(this.AgentName + " recommendation has alerted a Police Agent");
				CreateNewPoliceAgent = true;
				++ManagerAgent.PoliceGenerated;
			}
		}

	}

	public void RecieveRequest(File File, NeutralAgent Sender) {
		// Automatic rejection of requested files
		ManagerAgent.ProcessReject(Sender, File, this);
	}

}
