package org.andengine.limbo.utils.positioner;

import org.andengine.entity.IEntity;
/**
 * (c) 2013 Michal Stawinski (nazgee)
 *
 * @author Michal Stawinski
 * @since 20:35:05 08 - 08.05.2013
 */
public class PositionerNormal extends BasePositioner {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private static PositionerNormal INSTANCE;

	static float[] TMP_ENTITY_IMMOVABLE = new float[2];
	static float[] TMP_ENTITY_MOVABLE = new float[2];
	// ===========================================================
	// Constructors
	// ===========================================================
	private PositionerNormal() {

	}

	public static PositionerNormal getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PositionerNormal();
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
				pAnchorsPair.anchorA.Y * pImmovable.getHeight(), TMP_ENTITY_IMMOVABLE);

		pMovable.convertLocalCoordinatesToSceneCoordinates(
				pAnchorsPair.anchorB.X * pMovable.getWidth(),
				pAnchorsPair.anchorB.Y * pMovable.getHeight(), TMP_ENTITY_MOVABLE);

		final float dX = !pMoveHorizontally ? 0 : TMP_ENTITY_MOVABLE[0] - TMP_ENTITY_IMMOVABLE[0];
		final float dY = !pMoveVertically ? 0 : TMP_ENTITY_MOVABLE[1] - TMP_ENTITY_IMMOVABLE[1];

		pMovable.setPosition(pMovable.getX() - dX + tX, pMovable.getY() - dY + tY);
	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
