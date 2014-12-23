package org.andengine.limbo.physics;

public class Actions {
	public enum BodyAction {
		DEACTIVATE,
		ACTIVATE,
		AWAKE,
		SLEEP,
		DESTROY
	}

	public enum JointAction {
		DESTROY
	}

	public enum FixtureAction {
		SENSORIZE,
		DESENSORIZE
	}
}