package goooey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

class Tester {
	static int InitialFiles1 = 0;
	static int InitialNAgents1 = 0;
	static int InitialUAgents1 = 0;
	static int InitialDAgents1 = 0;
	static int InitialPAgents1 = 0;
	
	static int InitialFiles2 = 0;
	static int InitialNAgents2 = 0;
	static int InitialUAgents2 = 0;
	static int InitialDAgents2 = 0;
	static int InitialPAgents2 = 0;
	
	static int Old_InitialFiles = InitialFiles1;
	static int Old_InitialNAgents = InitialNAgents1;
	static int Old_InitialUAgents = InitialUAgents1;
	static int Old_InitialDAgents = InitialDAgents1;
	static int Old_InitialPAgents = InitialPAgents1;
	
	static int Old_RequestsSent = ManagerAgent.RequestsSent;
	static int Old_RequestsAccepted = ManagerAgent.RequestsAccepted;
	static int Old_RequestsRejected = ManagerAgent.RequestsRejected;
	static int Old_AgentsLeft= ManagerAgent.AgentsLeft;
	
	static int Old_UploadersGenerated = ManagerAgent.UploadersGenerated;
	static int Old_FilesAdded = ManagerAgent.FilesAdded;
	
	static int Old_PoliceGenerated = ManagerAgent.PoliceGenerated;
	static int Old_WarningsIssued = ManagerAgent.WarningsIssued;
	static int Old_TakedownsIssued = ManagerAgent.TakedownsIssued;
	static int Old_PoliceAgentsDiscovered = ManagerAgent.PoliceAgentsDiscovered;
	
	static int Old_DownloadersGenerated = ManagerAgent.DownloadersGenerated;
	static int Old_NewAgentsAdded = ManagerAgent.NewAgentsAdded;
	
	static int Old_TotalRemainingAgents;
	//int Old_TotalRemainingAgents = ManagerAgent.TotalRemainingAgents;
	static double Old_SucessRate;
	static double Old_SystemGrowth;
        
        static double SucessRate;
        static double SystemGrowth;
	
	// PASS INFORMATION FROM LOGIN SCREEN
	public static void Login(int Files1, int NAgents1, int UAgents1, int DAgents1, int PAgents1, int Files2, int NAgents2, int UAgents2, int DAgents2, int PAgents2) {
		InitialFiles1 = Files1;
                System.out.println(InitialFiles1);
		InitialNAgents1 = NAgents1;
		InitialUAgents1 = UAgents1;
		InitialDAgents1 = DAgents1;
		InitialPAgents1 = PAgents1;
		
		InitialFiles2 = Files2;
		InitialNAgents2 = NAgents2;
		InitialUAgents2 = UAgents2;
		InitialDAgents2 = DAgents2;
		InitialPAgents2 = PAgents2;
	}
	
	// RUN FIRST TEST
	public static void RunTest1 () {
		boolean terminated = false;
		String[] Filetypes = { "Movie", "Game", "MP3", "Text" };
		int TotalRemainingAgents;
		
		int TotalFiles = InitialFiles1;
		// Randomly assigns all File variables
		for (int i = 0; i < InitialFiles1; i++) {
			File file = new File();
			int rand = (int) (Math.random() * Filetypes.length);
			file.setType(Filetypes[rand]);
			file.setFileName(file.getType() + " " + i);
			// Adds all Files to an ArrayList in the system
			ManagerAgent.ExistingFiles.add(file);
		}
		
		int TotalAgents = InitialNAgents1;
		for (int i = 0; i < InitialNAgents1; i++) {
			NeutralAgent agent = new NeutralAgent();
			agent.setAgentName("Agent" + i);
			// List of existing files are divided between owned and not owned
			for (File f : ManagerAgent.ExistingFiles) {
				Random random = new Random();
				int value = random.nextInt(11);
				// More likely Agent owns a file than doesn't own it
				if (value > 4) {
					agent.Own.add(f);
				} else {
					agent.Target.add(f);
				}
			}
			ManagerAgent.ActiveAgents.add(agent);
		}
		
		int TotalUploaders = InitialUAgents1;
		for (int i = 0; i < InitialUAgents1; i++) {
			UploaderAgent uAgent = new UploaderAgent();
			uAgent.setAgentName("UploaderAgent" + i);
			// Uploaders have all files on the system and add new ones
			for (File f : ManagerAgent.ExistingFiles) {
				uAgent.Own.add(f);
			}
			ManagerAgent.ActiveAgents.add(uAgent);

		}
		
		int TotalDownloaders = InitialDAgents1;
		for (int i = 0; i < InitialDAgents1; i++) {
			DownloaderAgent dAgent = new DownloaderAgent();
			dAgent.setAgentName("DownloaderAgent" + i);
			// Downloaders are only interested in downloading files
			for (File f : ManagerAgent.ExistingFiles) {
				dAgent.Target.add(f);
			}
			ManagerAgent.ActiveAgents.add(dAgent);

		}
		
		int TotalPolice = InitialPAgents1;
		for (int i = 0; i < InitialPAgents1; i++) {
			PoliceAgent pAgent = new PoliceAgent();
			pAgent.setAgentName("PoliceAgent" + i);
			// Police Agents are only interested in seeking files
			for (File f : ManagerAgent.ExistingFiles) {
				pAgent.Target.add(f);
			}
			ManagerAgent.ActiveAgents.add(pAgent);

		}
		
		TotalRemainingAgents = TotalAgents + TotalUploaders + TotalDownloaders + TotalPolice;
		Old_TotalRemainingAgents = TotalRemainingAgents;
		
		double delay = 0;
		ManagerAgent.dly = (int) (delay);
		
		int turns = 100;
		ManagerAgent.TotalActions = turns;
		
		terminated = false;
		while (terminated == false) {
			while (ManagerAgent.ActiveAgents.size() >= 2) {
				for (NeutralAgent a : ManagerAgent.ActiveAgents) {
					if ((ManagerAgent.ActiveAgents.size() >= 2)) {
						a.SendRequest(ManagerAgent.ActiveAgents);
					} else {
						break;
					}

				}
				// The elements of ActiveAgents can't be changed during the above "for" loop
				if (ManagerAgent.ActiveAgents.size() > 0) {
					ArrayList<UploaderAgent> tmpUpldr = new ArrayList();
					ArrayList<PoliceAgent> tmpPlc = new ArrayList();
					ArrayList<DownloaderAgent> tmpDldr = new ArrayList();
					ArrayList<NeutralAgent> tmpAgnt = new ArrayList();
					Iterator<NeutralAgent> agnt = ManagerAgent.ActiveAgents.iterator();
					// The iterator checks what conditions the Agents have been marked for termination
					while (agnt.hasNext()) {
						NeutralAgent thisagnt = agnt.next();
						
						// This "if" just removes the Agent
						if (thisagnt.MarkedForDeath == true) {
							agnt.remove();
						}
						
						// This "if" makes the Agent an UploaderAgent
						else if (thisagnt.FutureUploaderAgent == true) {
							UploaderAgent uploaderAgent = new UploaderAgent(thisagnt);
							tmpUpldr.add(uploaderAgent);
							agnt.remove();
						}

						// This "if" makes the Agent a PoliceAgent
						else if (thisagnt.FuturePoliceAgent == true) {
							PoliceAgent policeAgent = new PoliceAgent(thisagnt);
							tmpPlc.add(policeAgent);
							agnt.remove();
						}
						// This "if" makes the Agent a DownloaderAgent
						else if (thisagnt.FutureDownloaderAgent == true) {
							DownloaderAgent downloaderAgent = new DownloaderAgent(thisagnt);
							tmpDldr.add(downloaderAgent);
							agnt.remove();
						}
					} // end of iterator
					
					 // This is where the DownloaderAgents generate other Agents
					for (NeutralAgent a : ManagerAgent.ActiveAgents) {
						if(a instanceof DownloaderAgent) {
							if(((DownloaderAgent) a).CreateNewAgent == true){
								NeutralAgent agent = new NeutralAgent();
								agent.setAgentName("Agent" + TotalAgents);
								// List of existing files are divided between owned and not owned
								for (File f : ManagerAgent.ExistingFiles) {
									Random random = new Random();
									int value = random.nextInt(11);
									// More likely Agent owns a file than doesn't own it
									if (value > 4) {
										agent.Own.add(f);
									} else {
										agent.Target.add(f);
									}
								}
								tmpAgnt.add(agent);
								((DownloaderAgent) a).CreateNewAgent = false;
							}
							// There is where the new PoliceAgent is generated by the DownloaderAgent
							else if(((DownloaderAgent) a).CreateNewPoliceAgent == true) {
								PoliceAgent policeAgent = new PoliceAgent();
								tmpPlc.add(policeAgent);
								((DownloaderAgent) a).CreateNewPoliceAgent = false;
							}
						}
					} // end of Downloader section
					
					// The below "for" loops add the newly generated Agents to the system
					for (UploaderAgent u : tmpUpldr) {
						ManagerAgent.ActiveAgents.add(u);
						++TotalUploaders;
					}
					tmpUpldr.clear();

					for (PoliceAgent p : tmpPlc) {
						ManagerAgent.ActiveAgents.add(p);
						++TotalPolice;
					}
					tmpPlc.clear();
					
					for (DownloaderAgent d : tmpDldr) {
						ManagerAgent.ActiveAgents.add(d);
						++TotalDownloaders;
					}
					tmpDldr.clear();
					
					for(NeutralAgent a : tmpAgnt){
						ManagerAgent.ActiveAgents.add(a);
						++TotalAgents;
					}
					tmpAgnt.clear();
				}
			} // end system "while" loop
			System.out
					.println("All Agents have left the system. Shutting Down...");
			terminated = true;
			break;
		}
		
		// The values of the first test are stored for reference later
					Old_InitialFiles = InitialFiles1;
					Old_InitialNAgents = InitialNAgents1;
					Old_InitialUAgents = InitialUAgents1;
					Old_InitialDAgents = InitialDAgents1;
					Old_InitialPAgents = InitialPAgents1;
					
					Old_RequestsSent = ManagerAgent.RequestsSent;
					Old_RequestsAccepted = ManagerAgent.RequestsAccepted;
					Old_RequestsRejected = ManagerAgent.RequestsRejected;
					Old_AgentsLeft= ManagerAgent.AgentsLeft;
					
					Old_UploadersGenerated = ManagerAgent.UploadersGenerated;
					Old_FilesAdded = ManagerAgent.FilesAdded;
					
					Old_PoliceGenerated = ManagerAgent.PoliceGenerated;
					Old_WarningsIssued = ManagerAgent.WarningsIssued;
					Old_TakedownsIssued = ManagerAgent.TakedownsIssued;
					Old_PoliceAgentsDiscovered = ManagerAgent.PoliceAgentsDiscovered;
					
					Old_DownloadersGenerated = ManagerAgent.DownloadersGenerated;
					Old_NewAgentsAdded = ManagerAgent.NewAgentsAdded;
					
					//int Old_TotalRemainingAgents = ManagerAgent.TotalRemainingAgents;
					Old_SucessRate = ((ManagerAgent.RequestsAccepted*100)/(ManagerAgent.RequestsSent));;
					Old_SystemGrowth = (((Old_TotalRemainingAgents*100/(InitialNAgents1+InitialDAgents1+InitialUAgents1+InitialPAgents1)))-100);
				
					ManagerAgent.RequestsSent = 0;
					ManagerAgent.RequestsAccepted = 0;
					ManagerAgent.RequestsRejected = 0;
					ManagerAgent.AgentsLeft = 0;
					
					ManagerAgent.UploadersGenerated = 0;
					ManagerAgent.FilesAdded = 0;
					
					ManagerAgent.PoliceGenerated = 0;
					ManagerAgent.WarningsIssued = 0;
					ManagerAgent.TakedownsIssued = 0;
					ManagerAgent.PoliceAgentsDiscovered = 0;
					
					ManagerAgent.DownloadersGenerated = 0;
					ManagerAgent.NewAgentsAdded = 0;
					
					ManagerAgent.TotalRemainingAgents = 0;
					
					ManagerAgent.ActiveAgents.clear();
					ManagerAgent.ExistingFiles.clear();
					ManagerAgent.CapNotification = false;
					ManagerAgent.PoliceVictory = false;
					ManagerAgent.TotalNumFiles = 0;
                                        System.out.println("");
                                        System.out.println("");
	}
	
	// RUN TEST 2
	public static void RunTest2() {
		boolean terminated = false;
		String[] Filetypes = { "Movie", "Game", "MP3", "Text" };
		int TotalRemainingAgents;
		
		int TotalFiles = InitialFiles2;
		// Randomly assigns all File variables
		for (int i = 0; i < InitialFiles2; i++) {
			File file = new File();
			int rand = (int) (Math.random() * Filetypes.length);
			file.setType(Filetypes[rand]);
			file.setFileName(file.getType() + " " + i);
			// Adds all Files to an ArrayList in the system
			ManagerAgent.ExistingFiles.add(file);
		}
		
		int TotalAgents = InitialNAgents2;
		for (int i = 0; i < InitialNAgents2; i++) {
			NeutralAgent agent = new NeutralAgent();
			agent.setAgentName("Agent" + i);
			// List of existing files are divided between owned and not owned
			for (File f : ManagerAgent.ExistingFiles) {
				Random random = new Random();
				int value = random.nextInt(11);
				// More likely Agent owns a file than doesn't own it
				if (value > 4) {
					agent.Own.add(f);
				} else {
					agent.Target.add(f);
				}
			}
			ManagerAgent.ActiveAgents.add(agent);
		}
		
		int TotalUploaders = InitialUAgents2;
		for (int i = 0; i < InitialUAgents2; i++) {
			UploaderAgent uAgent = new UploaderAgent();
			uAgent.setAgentName("UploaderAgent" + i);
			// Uploaders have all files on the system and add new ones
			for (File f : ManagerAgent.ExistingFiles) {
				uAgent.Own.add(f);
			}
			ManagerAgent.ActiveAgents.add(uAgent);

		}
		
		int TotalDownloaders = InitialDAgents2;
		for (int i = 0; i < InitialDAgents2; i++) {
			DownloaderAgent dAgent = new DownloaderAgent();
			dAgent.setAgentName("DownloaderAgent" + i);
			// Downloaders are only interested in downloading files
			for (File f : ManagerAgent.ExistingFiles) {
				dAgent.Target.add(f);
			}
			ManagerAgent.ActiveAgents.add(dAgent);

		}
		
		int TotalPolice = InitialPAgents2;
		for (int i = 0; i < InitialPAgents2; i++) {
			PoliceAgent pAgent = new PoliceAgent();
			pAgent.setAgentName("PoliceAgent" + i);
			// Police Agents are only interested in seeking files
			for (File f : ManagerAgent.ExistingFiles) {
				pAgent.Target.add(f);
			}
			ManagerAgent.ActiveAgents.add(pAgent);

		}
		
		TotalRemainingAgents = TotalAgents + TotalUploaders + TotalDownloaders + TotalPolice;
		
		double delay = 0;
		ManagerAgent.dly = (int) (delay);
		
		int turns = 100;
		ManagerAgent.TotalActions = turns;
		
		terminated = false;
		while (terminated == false) {
			while (ManagerAgent.ActiveAgents.size() >= 2) {
				for (NeutralAgent a : ManagerAgent.ActiveAgents) {
					if ((ManagerAgent.ActiveAgents.size() >= 2)) {
						a.SendRequest(ManagerAgent.ActiveAgents);
					} else {
						break;
					}

				}
				// The elements of ActiveAgents can't be changed during the above "for" loop
				if (ManagerAgent.ActiveAgents.size() > 0) {
					ArrayList<UploaderAgent> tmpUpldr = new ArrayList();
					ArrayList<PoliceAgent> tmpPlc = new ArrayList();
					ArrayList<DownloaderAgent> tmpDldr = new ArrayList();
					ArrayList<NeutralAgent> tmpAgnt = new ArrayList();
					Iterator<NeutralAgent> agnt = ManagerAgent.ActiveAgents.iterator();
					// The iterator checks what conditions the Agents have been marked for termination
					while (agnt.hasNext()) {
						NeutralAgent thisagnt = agnt.next();
						
						// This "if" just removes the Agent
						if (thisagnt.MarkedForDeath == true) {
							agnt.remove();
						}
						
						// This "if" makes the Agent an UploaderAgent
						else if (thisagnt.FutureUploaderAgent == true) {
							UploaderAgent uploaderAgent = new UploaderAgent(thisagnt);
							tmpUpldr.add(uploaderAgent);
							agnt.remove();
						}

						// This "if" makes the Agent a PoliceAgent
						else if (thisagnt.FuturePoliceAgent == true) {
							PoliceAgent policeAgent = new PoliceAgent(thisagnt);
							tmpPlc.add(policeAgent);
							agnt.remove();
						}
						// This "if" makes the Agent a DownloaderAgent
						else if (thisagnt.FutureDownloaderAgent == true) {
							DownloaderAgent downloaderAgent = new DownloaderAgent(thisagnt);
							tmpDldr.add(downloaderAgent);
							agnt.remove();
						}
					} // end of iterator
					
					 // This is where the DownloaderAgents generate other Agents
					for (NeutralAgent a : ManagerAgent.ActiveAgents) {
						if(a instanceof DownloaderAgent) {
							if(((DownloaderAgent) a).CreateNewAgent == true){
								NeutralAgent agent = new NeutralAgent();
								agent.setAgentName("Agent" + TotalAgents);
								// List of existing files are divided between owned and not owned
								for (File f : ManagerAgent.ExistingFiles) {
									Random random = new Random();
									int value = random.nextInt(11);
									// More likely Agent owns a file than doesn't own it
									if (value > 4) {
										agent.Own.add(f);
									} else {
										agent.Target.add(f);
									}
								}
								tmpAgnt.add(agent);
								((DownloaderAgent) a).CreateNewAgent = false;
							}
							// There is where the new PoliceAgent is generated by the DownloaderAgent
							else if(((DownloaderAgent) a).CreateNewPoliceAgent == true) {
								PoliceAgent policeAgent = new PoliceAgent();
								tmpPlc.add(policeAgent);
								((DownloaderAgent) a).CreateNewPoliceAgent = false;
							}
						}
					} // end of Downloader section
					
					// The below "for" loops add the newly generated Agents to the system
					for (UploaderAgent u : tmpUpldr) {
						ManagerAgent.ActiveAgents.add(u);
						++TotalUploaders;
					}
					tmpUpldr.clear();

					for (PoliceAgent p : tmpPlc) {
						ManagerAgent.ActiveAgents.add(p);
						++TotalPolice;
					}
					tmpPlc.clear();
					
					for (DownloaderAgent d : tmpDldr) {
						ManagerAgent.ActiveAgents.add(d);
						++TotalDownloaders;
					}
					tmpDldr.clear();
					
					for(NeutralAgent a : tmpAgnt){
						ManagerAgent.ActiveAgents.add(a);
						++TotalAgents;
					}
					tmpAgnt.clear();
				}
			} // end system "while" loop
			System.out
					.println("All Agents have left the system. Shutting Down...");
			terminated = true;
			break;
		}
	}
	
	// SEND VARIABLES TO RESULTS SCREEN
	public static void Results() {
		
		SucessRate = ((ManagerAgent.RequestsAccepted*100)/(ManagerAgent.RequestsSent));
		SystemGrowth = (((ManagerAgent.TotalRemainingAgents*100/(InitialNAgents2+InitialDAgents2+InitialUAgents2+InitialPAgents2)))-100);
		
	}
}