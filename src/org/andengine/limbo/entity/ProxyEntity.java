package org.andengine.limbo.entity;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.limbo.utils.positioner.PositionerSceneRelative;

public class ProxyEntity extends Entity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private IEntity mTrackingTarget;
//	private TrackingPolicy mTrackingPolicy;
	// ===========================================================
	// Constructors
	// ===========================================================
	public ProxyEntity(IEntity pTrackingTarget/*, TrackingPolicy pTrackingPolicy*/) {
		this.mTrackingTarget = pTrackingTarget;
//		this.mTrackingPolicy = pTrackingPolicy;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
//	public TrackingPolicy getDefaultTrackingPolicy() {
//		return mTrackingPolicy;
//	}
//
//	public void setDefaultTrackingPolicy(TrackingPolicy mDefaultTrackingPolicy) {
//		this.mTrackingPolicy = mDefaultTrackingPolicy;
//	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
//		if (mTrackingPolicy.isAbsolute()) {
//			Transformation targetToScene = mTrackingTarget.getLocalToSceneTransformation();
//			if (mTrackingPolicy.isTrackingEverything()) {
//				
//			} else {
//				
//			}
//		} else {
//			if (mTrackingPolicy.isTrackingEverything()) {
//				setPosition(mTrackingTarget);
//				setOffsetCenter(mTrackingTarget.getOffsetCenterX(), mTrackingTarget.getOffsetCenterY());
//
//				setRotation(mTrackingTarget.getRotation());
//				setRotationCenter(mTrackingTarget.getRotationCenterX(), mTrackingTarget.getRotationCenterY());
//
//				setScale(mTrackingTarget.getScaleX(), mTrackingTarget.getScaleY());
//				setScaleCenter(mTrackingTarget.getScaleCenterX(), mTrackingTarget.getScaleCenterY());
//			} else {
//				if (mTrackingPolicy.isTrackingPosition()) {
//					setPosition(mTrackingTarget);
//					setOffsetCenter(mTrackingTarget.getOffsetCenterX(), mTrackingTarget.getOffsetCenterY());
//				}
//				if (mTrackingPolicy.isTrackingRotation()) {
//					setRotation(mTrackingTarget.getRotation());
//					setRotationCenter(mTrackingTarget.getRotationCenterX(), mTrackingTarget.getRotationCenterY());
//				}
//				if (mTrackingPolicy.isTrackingScale()) {
//					setScale(mTrackingTarget.getScaleX(), mTrpEntityackingTarget.getScaleY());
//					setScaleCenter(mTrackingTarget.getScaleCenterX(), mTrackingTarget.getScaleCenterY());
//				}
//			}
//		}
		// regular update
		super.onManagedUpdate(pSecondsElapsed);

		// position
		 PositionerSceneRelative.getInstance().center(mTrackingTarget, this);
//		float tagetCenterInScene[] = new float[2];
//		tagetCenterInScene = mTrackingTarget.convertLocalCoordinatesToParentCoordinates(0, 0, tagetCenterInScene);
//		//Log.d("log", "tagetCenterInScene=" + tagetCenterInScene[0] + ","+ tagetCenterInScene[1]);
////		Transformation sceneToLocalTransformation = getLocalToSceneTransformation();
////		sceneToLocalTransformation.transform(tagetCenterInScene);
//		setPosition(tagetCenterInScene[0], tagetCenterInScene[1]);

		// rotation
		float targetSceneToLocalRotation = mTrackingTarget.getSceneToLocalTransformation().getRotation();
		float parentToLocalRotation = this.getParent().getSceneToLocalTransformation().getRotation();
		setRotation(parentToLocalRotation-targetSceneToLocalRotation);

	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

//	public static class TrackingPolicy {
//		// ===========================================================
//		// Constants
//		// ===========================================================
//
//		// ===========================================================
//		// Fields
//		// ===========================================================
//		private boolean mTrackRotation;
//		private boolean mTrackScale;
//		private boolean mTrackPosition;
//		private boolean mAbsolute;
//		// ===========================================================
//		// Constructors
//		// ===========================================================
//		public TrackingPolicy(boolean mTrackRotation, boolean mTrackScale, boolean mTrackPosition, boolean mAbsolute) {
//			this.mTrackRotation = mTrackRotation;
//			this.mTrackScale = mTrackScale;
//			this.mTrackPosition = mTrackPosition;
//			this.setAbsolute(mAbsolute);
//		}
//		// ===========================================================
//		// Getter & Setter
//		// ===========================================================
//		public boolean isTrackingEverything() {
//			return mTrackPosition && mTrackRotation && mTrackScale;
//		}
//
//		public boolean isTrackingRotation() {
//			return mTrackRotation;
//		}
//
//		public void setTrackRotation(boolean mTrackRotation) {
//			this.mTrackRotation = mTrackRotation;
//		}
//
//		public boolean isTrackingScale() {
//			return mTrackScale;
//		}
//
//		public void setTrackingScale(boolean mTrackScale) {
//			this.mTrackScale = mTrackScale;
//		}
//
//		public boolean isTrackingPosition() {
//			return mTrackPosition;
//		}
//
//		public void setTrackTranslation(boolean mTrackPosition) {
//			this.mTrackPosition = mTrackPosition;
//		}
//
//		public boolean isAbsolute() {
//			return mAbsolute;
//		}
//
//		public void setAbsolute(boolean mAbsolute) {
//			this.mAbsolute = mAbsolute;
//		}
//
//		// ===========================================================
//		// Methods for/from SuperClass/Interfaces
//		// ===========================================================
//
//		// ===========================================================
//		// Methods
//		// ===========================================================
//
//		// ===========================================================
//		// Inner and Anonymous Classes
//		// ===========================================================
//	}
}
