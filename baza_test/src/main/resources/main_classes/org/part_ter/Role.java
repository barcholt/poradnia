package org.part_ter;

public class Role {
	// role = 0 => unauthenticated; 1 => admin; 2=>therapist;
	
	private int role=0;
	
	Role () {
	}
	Role (int role) {
		this.role = role;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
		
}
