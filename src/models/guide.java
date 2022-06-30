package models;

public class guide {

	private int guide_id;
	private String guide_name;
	
	public guide() {
		
	}

	public guide(int guide_id, String guide_name) {
		super();
		this.guide_id = guide_id;
		this.guide_name =guide_name;
	}

	public int getGuide_id() {
		return guide_id;
	}

	public void setGuide_id(int guide_id) {
		this.guide_id = guide_id;
	}

	public String getGuideName() {
		return guide_name;
	}

	public void setGuideName(String guideName) {
		this.guide_name = guideName;
	}
	
	
}
