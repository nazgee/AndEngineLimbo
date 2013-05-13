package org.andengine.limbo.utils.positioner;

import org.andengine.entity.IEntity;
import org.andengine.util.math.MathUtils;
/**
 * (c) 2013 Michal Stawinski (nazgee)
 *
 * @author Michal Stawinski
 * @since 20:35:05 08 - 08.05.2013
 */
public class PositionerSceneRelative extends BasePositioner {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private static PositionerSceneRelative INSTANCE;

	static float[] TMP_ENTITY_IMMOVABLE = new float[2];
	static float[] TMP_ENTITY_MOVABLE = new float[2];
	// ===========================================================
	// Constructors
	// ===========================================================
	private PositionerSceneRelative() {

	}

	public static PositionerSceneRelative getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PositionerSceneRelative();
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

		calculateAnchorpoint(pImmovable, pAnchorsPair.anchorA, TMP_ENTITY_IMMOVABLE);
		calculateAnchorpoint(pMovable, pAnchorsPair.anchorB, TMP_ENTITY_MOVABLE);

		final float dX = !pMoveHorizontally ? 0 : TMP_ENTITY_MOVABLE[0] - TMP_ENTITY_IMMOVABLE[0];
		final float dY = !pMoveVertically ? 0 : TMP_ENTITY_MOVABLE[1] - TMP_ENTITY_IMMOVABLE[1];

		pMovable.setPosition(pMovable.getX() - dX + tX, pMovable.getY() - dY + tY);
	}
	// ===========================================================
	// Methods
	// ===========================================================

	private void calculateAnchorpoint(IEntity pTargetEntity, AnchorPoint pAnchorsPoint, final float[] pReuse) {

		final float rotation = -calculateRotation(pTargetEntity.getLocalToSceneTransformation(), pReuse);

		// rotate anchorpoint
		pReuse[0] = pAnchorsPoint.X;
		pReuse[1] = pAnchorsPoint.Y;
		MathUtils.rotateAroundCenter(pReuse, rotation,
				pTargetEntity.getRotationCenterX(), pTargetEntity.getRotationCenterY());

		// calculate anchorpoint in scene coordinates
		pTargetEntity.convertLocalCoordinatesToSceneCoordinates(
				pReuse[0] * pTargetEntity.getWidth(),
				pReuse[1] * pTargetEntity.getHeight(), pReuse);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
