/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Model.Student;

/**
 *
 * @author lts
 */
public enum Gender {

	MALE("ذكر"), FEMALE("انثي");

	private final String string;

	private Gender(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}
}
