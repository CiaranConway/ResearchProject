package goooey;
import java.util.ArrayList;
import java.util.Random;

public class UploaderAgent extends NeutralAgent {
	String[] Filetypes = { "Movie", "Game", "MP3", "Text" };

	public UploaderAgent(NeutralAgent agent) {
		this.AgentName = "Uploader" + agent.AgentName;
		this.Own = ManagerAgent.ExistingFiles;
		this.Target = agent.Target;
		this.Satisfaction = agent.Satisfaction;
		this.FutureUploaderAgent = false;
	}

	public UploaderAgent() {
		this.AgentName = "Uploader" + ManagerAgent.ActiveAgents.size();
		this.FutureUploaderAgent = false;
	}

	// UploaderAgent generates new files instead of requesting others
	public void SendRequest(ArrayList<NeutralAgent> ExistingAgents) {
		if (ManagerAgent.TotalActions > 0) {
			Random random0 = new Random();
			int findfile = random0.nextInt(11);
			if (findfile>3) {
			File file = new File();
			int rand = (int) (Math.random() * Filetypes.length);
			file.setType(Filetypes[rand]);
			file.setFileName(file.getType() + " " + ManagerAgent.TotalNumFiles);
			// Adds File to ManagerAgent ArrayList and Own list
			ManagerAgent.ExistingFiles.add(file);
			++ManagerAgent.TotalNumFiles;
			++ManagerAgent.FilesAdded;
			try {
				Thread.sleep(ManagerAgent.dly);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			System.out.println(this.AgentName + " has added "
					+ file.getFileName() + " to the system");
			for (NeutralAgent a : ManagerAgent.ActiveAgents) {
				if (a instanceof UploaderAgent) {
					a.Own = ManagerAgent.ExistingFiles;
				} else {
					Random random = new Random();
					int value = random.nextInt(11);
					if (value > 2) {
						// Agents won't look for the new file unless it is in their Target list
						a.Target.add(file);
					} else {
						// Distributes the new file to some Agents
						a.Own.add(file);
					}
				}
			}
			}
			else {
				System.out.println(this.AgentName+" is looking for a file");
			}
			++ManagerAgent.TotalActions;
		} 
		// This is the "else" for when the TotalActions limit has been reached
		else {
			if (ManagerAgent.CapNotification == false) {
				try {
					Thread.sleep(ManagerAgent.dly);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				System.out.println("Cap on actions has been reached");
				ManagerAgent.CapNotification = true;
				ManagerAgent.TotalRemainingAgents = ManagerAgent.ActiveAgents.size();
				for (NeutralAgent a : ManagerAgent.ActiveAgents) {
					a.MarkedForDeath = true;
				}
			} else {
			}
		}
	}

}
