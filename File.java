package goooey;
class File {
	// The File object contains basic descriptive information for clarity
	private String FileName;
	private String Type;
	
	public String getFileName()
	{
		return this.FileName;
	}
	
	public void setFileName(String newFileName)
	{
		this.FileName = newFileName;
	}
	public String getType() {
		return this.Type;
	}
	public void setType(String newType) {
		this.Type = newType;
	}
}