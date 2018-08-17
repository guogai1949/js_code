package com.js.worker.code.builder;

public class Director {
	
	public Human createHumanByDeretor(IbuildHuman human) {
		human.buildHead();
		human.buildbody();
		human.buildHand();
		human.buildFoot();
		return human.buildHuman();
	}

}
