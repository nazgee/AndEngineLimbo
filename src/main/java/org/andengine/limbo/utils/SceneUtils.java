package org.andengine.limbo.utils;

import org.andengine.entity.scene.Scene;

public class SceneUtils {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * Traverses top through the scene's children stack to find the top-most
	 * scene
	 * 
	 * @return top Scene on the children stack
	 * @note this Scene's instance will be returned, if scene has no child scene
	 */
	static public Scene getTopLevel(Scene pScene) {
		Scene child = pScene;
		while (child.getChildScene() != null) {
			child = child.getChildScene();
		}
		return child;
	}

	/**
	 * Traverses top through the scene's children stack to find the scene that is 
	 * previous to the top-most scene
	 * 
	 * @return top Scene on the children stack
	 * @note this Scene's instance will be returned, if scene has no child scene
	 */
	static public Scene getTopLevelPrevious(Scene pScene) {
		Scene previous = pScene;
		while ((previous.getChildScene() != null) && (previous.getChildScene().getChildScene() != null)){
			previous = previous.getChildScene().getChildScene();
		}
		return previous;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
