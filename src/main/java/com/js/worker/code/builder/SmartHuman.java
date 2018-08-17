package com.js.worker.code.builder;

public class SmartHuman implements IbuildHuman {
	
	Human human = new Human();

	@Override
	public void buildHead() {
		human.setHead("智商180");

	}

	@Override
	public void buildbody() {
		human.setBody("新的身体");

	}

	@Override
	public void buildHand() {
		human.setHand("新的手");

	}

	@Override
	public void buildFoot() {
		human.setFoot("新的脚");

	}

	@Override
	public Human buildHuman() {
		return human;
	}

}
