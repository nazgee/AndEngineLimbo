package org.andengine.limbo.utils.positioner;

import org.andengine.entity.IEntity;
import org.andengine.util.math.MathUtils;
/**
 * (c) 2013 Michal Stawinski (nazgee)
 *
 * @author Michal Stawinski
 * @since 20:35:05 08 - 08.05.2013
 */
public class PositionerSmart extends BasePositioner {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private static PositionerSmart INSTANCE;

	static float[] TMP_ENTITY_ANCHORED = new float[2];
	static float[] TMP_ENTITY_MOVABLE = new float[2];
	// ===========================================================
	// Constructors
	// ===========================================================
	private PositionerSmart() {

	}

	public static PositionerSmart getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PositionerSmart();
		}
		return INSTANCE;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void place(IEntity pImmovable, IEntity pMovable, AnchorPointsPair pAnchorsPair,
			final float tX, final float tY, boolean pMoveVertically, boolean pMoveHorizontally) {
		
//		pImmovable.convertLocalCoordinatesToSceneCoordinates(
//				pAnchorsPair.anchorA.X * pImmovable.getWidth(),
//				pAnchorsPair.anchorA.Y * pImmovable.getHeight(), TMP_ENTITY_ANCHORED);

		translateRotation(pImmovable, pAnchorsPair.anchorA, TMP_ENTITY_ANCHORED);
		translateRotation(pMovable, pAnchorsPair.anchorB, TMP_ENTITY_MOVABLE);

		final float dX = !pMoveHorizontally ? 0 : TMP_ENTITY_MOVABLE[0] - TMP_ENTITY_ANCHORED[0];
		final float dY = !pMoveVertically ? 0 : TMP_ENTITY_MOVABLE[1] - TMP_ENTITY_ANCHORED[1];

		pMovable.setPosition(pMovable.getX() - dX + tX, pMovable.getY() - dY + tY);
	}
	// ===========================================================
	// Methods
	// ===========================================================

	private void translateRotation(IEntity pTargetEntity, AnchorPoint pAnchorsPoint, final float[] pReuse) {
		pReuse[0] = pAnchorsPoint.X;
		pReuse[1] = pAnchorsPoint.Y;
		final float correctiveRotation = 360 - pTargetEntity.getRotation();
		MathUtils.rotateAroundCenter(pReuse, -correctiveRotation, 
				pTargetEntity.getRotationCenterX(), pTargetEntity.getRotationCenterY());

		pTargetEntity.convertLocalCoordinatesToSceneCoordinates(
				pReuse[0] * pTargetEntity.getWidth(),
				pReuse[1] * pTargetEntity.getHeight(), pReuse);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}