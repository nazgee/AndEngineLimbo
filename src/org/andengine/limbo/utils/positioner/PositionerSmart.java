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
		pImmovable.convertLocalCoordinatesToSceneCoordinates(
				pAnchorsPair.anchorA.X * pImmovable.getWidth(),
				pAnchorsPair.anchorA.Y * pImmovable.getHeight(), TMP_ENTITY_ANCHORED);

		TMP_ENTITY_MOVABLE[0] = pAnchorsPair.anchorB.X;
		TMP_ENTITY_MOVABLE[1] = pAnchorsPair.anchorB.Y;
		final float correctiveRotation = 360 - pMovable.getRotation();
		MathUtils.rotateAroundCenter(TMP_ENTITY_MOVABLE, -correctiveRotation, 
				pMovable.getRotationCenterX(), pMovable.getRotationCenterY());

		pMovable.convertLocalCoordinatesToSceneCoordinates(
				TMP_ENTITY_MOVABLE[0] * pMovable.getWidth(),
				TMP_ENTITY_MOVABLE[1] * pMovable.getHeight(), TMP_ENTITY_MOVABLE);

		final float dX = !pMoveHorizontally ? 0 : TMP_ENTITY_MOVABLE[0] - TMP_ENTITY_ANCHORED[0];
		final float dY = !pMoveVertically ? 0 : TMP_ENTITY_MOVABLE[1] - TMP_ENTITY_ANCHORED[1];

		pMovable.setPosition(pMovable.getX() - dX + tX, pMovable.getY() - dY + tY);
	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
