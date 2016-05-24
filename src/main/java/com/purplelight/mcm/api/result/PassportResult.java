package com.purplelight.mcm.api.result;

import java.util.ArrayList;
import java.util.List;

import com.purplelight.mcm.api.bean.Passport;

public class PassportResult extends Result {
	private List<Passport> passports = new ArrayList<>();

	public List<Passport> getPassports() {
		return passports;
	}

	public void setPassports(List<Passport> passports) {
		this.passports = passports;
	}
}
