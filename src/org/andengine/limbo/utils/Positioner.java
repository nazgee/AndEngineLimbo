package org.andengine.limbo.utils;

import org.andengine.entity.IEntity;
/**
 * (c) 2013 Michal Stawinski (nazgee)
 *
 * @author Michal Stawinski
 * @since 20:35:05 08 - 08.05.2013
 */
public class Positioner {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	static float[] TMP_ENTITY_ANCHORED = new float[2];
	static float[] TMP_ENTITY_MOVABLE = new float[2];
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
	static public void placeTopOfAndCenter(IEntity pImmovable, IEntity pMovable) {
		placeTopOfAndCenter(pImmovable, pMovable, 0, 0);
	}
	static public void placeTopOfAndCenter(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.VER_TOP_C.relation, tx, ty);
	}

	static public void placeBelowOfAndCenter(IEntity pImmovable, IEntity pMovable) {
		placeBelowOfAndCenter(pImmovable, pMovable, 0, 0);
	}
	static public void placeBelowOfAndCenter(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.VER_BTM_C.relation, tx, ty);
	}

	static public void placeLeftOfAndCenter(IEntity pImmovable, IEntity pMovable) {
		placeLeftOfAndCenter(pImmovable, pMovable, 0, 0);
	}
	static public void placeLeftOfAndCenter(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.HOR_L_CNT.relation, tx, ty);
	}

	static public void placeRightOfAndCenter(IEntity pImmovable, IEntity pMovable) {
		placeRightOfAndCenter(pImmovable, pMovable, 0, 0);
	}
	static public void placeRightOfAndCenter(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.HOR_R_CNT.relation, tx, ty);
	}

	static public void centerOnHorizontalAxes(IEntity pImmovable, IEntity pMovable) {
		centerOnHorizontalAxes(pImmovable, pMovable, 0, 0);
	}
	static public void centerOnHorizontalAxes(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		placeVertical(pImmovable, pMovable, eAnchorPointsPair.IN_C_CNT.relation, tx, ty);
	}

	static public void centerOnVerticalAxes(IEntity pImmovable, IEntity pMovable) {
		centerOnVerticalAxes(pImmovable, pMovable, 0, 0);
	}
	static public void centerOnVerticalAxes(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		placeHorizontal(pImmovable, pMovable, eAnchorPointsPair.IN_C_CNT.relation, tx, ty);
	}

	static public void center(IEntity pImmovable, IEntity pMovable) {
		center(pImmovable, pMovable, 0, 0);
	}
	static public void center(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.IN_C_CNT.relation, tx, ty);
	}

	static public void alignLeftEdges(IEntity pImmovable, IEntity pMovable) {
		alignLeftEdges(pImmovable, pMovable, 0, 0);
	}
	static public void alignLeftEdges(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		placeHorizontal(pImmovable, pMovable, eAnchorPointsPair.IN_L_CNT.relation, tx, ty);
	}

	static public void alignLeftEdgesAndCenter(IEntity pImmovable, IEntity pMovable) {
		alignLeftEdgesAndCenter(pImmovable, pMovable, 0, 0);
	}
	static public void alignLeftEdgesAndCenter(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.IN_L_CNT.relation, tx, ty);
	}

	static public void alignRightEdges(IEntity pImmovable, IEntity pMovable) {
		alignRightEdges(pImmovable, pMovable, 0, 0);
	}
	static public void alignRightEdges(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		placeHorizontal(pImmovable, pMovable, eAnchorPointsPair.IN_R_CNT.relation, tx, ty);
	}

	static public void alignRightEdgesAndCenter(IEntity pImmovable, IEntity pMovable) {
		alignRightEdgesAndCenter(pImmovable, pMovable, 0, 0);
	}
	static public void alignRightEdgesAndCenter(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.IN_R_CNT.relation, tx, ty);
	}

	static public void alignTopEdges(IEntity pImmovable, IEntity pMovable) {
		alignTopEdges(pImmovable, pMovable, 0, 0);
	}
	static public void alignTopEdges(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		placeVertical(pImmovable, pMovable, eAnchorPointsPair.IN_C_TOP.relation, tx, ty);
	}

	static public void alignTopEdgesAndCenter(IEntity pImmovable, IEntity pMovable) {
		alignTopEdgesAndCenter(pImmovable, pMovable, 0, 0);
	}
	static public void alignTopEdgesAndCenter(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.IN_C_TOP.relation, tx, ty);
	}

	static public void alignTopRightEdges(IEntity pImmovable, IEntity pMovable) {
		alignTopRightEdges(pImmovable, pMovable, 0, 0);
	}
	static public void alignTopRightEdges(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.IN_R_TOP.relation, tx, ty);
	}

	static public void alignTopLeftEdges(IEntity pImmovable, IEntity pMovable) {
		alignTopLeftEdges(pImmovable, pMovable, 0, 0);
	}
	static public void alignTopLeftEdges(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.IN_L_TOP.relation, tx, ty);
	}

	static public void alignBottomEdges(IEntity pImmovable, IEntity pMovable) {
		alignTopEdges(pImmovable, pMovable, 0, 0);
	}
	static public void alignBottomEdges(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		placeVertical(pImmovable, pMovable, eAnchorPointsPair.IN_C_BTM.relation, tx, ty);
	}

	static public void alignBottomEdgesAndCenter(IEntity pImmovable, IEntity pMovable) {
		alignBottomEdgesAndCenter(pImmovable, pMovable, 0, 0);
	}
	static public void alignBottomEdgesAndCenter(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.IN_C_BTM.relation, tx, ty);
	}

	static public void alignBottomRightEdges(IEntity pImmovable, IEntity pMovable) {
		alignBottomRightEdges(pImmovable, pMovable, 0, 0);
	}
	static public void alignBottomRightEdges(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.IN_R_BTM.relation, tx, ty);
	}

	static public void alignBottomLeftEdges(IEntity pImmovable, IEntity pMovable) {
		alignBottomLeftEdges(pImmovable, pMovable, 0, 0);
	}
	static public void alignBottomLeftEdges(IEntity pImmovable, IEntity pMovable, final float tx, final float ty) {
		place(pImmovable, pMovable, eAnchorPointsPair.IN_L_BTM.relation, tx, ty);
	}

	static public void place(IEntity pImmovable, IEntity pMovable, AnchorPointsPair pAnchorsPair) {
		place(pImmovable, pMovable, pAnchorsPair, 0, 0);
	}

	static public void place(IEntity pImmovable, IEntity pMovable, AnchorPointsPair pAnchorsPair, final float tX, final float tY) {
		place(pImmovable, pMovable, pAnchorsPair, tX, tY, true, true, false);
	}

	static public void placeVertical(IEntity pImmovable, IEntity pMovable, AnchorPointsPair pAnchorsPair, final float tX, final float tY) {
		place(pImmovable, pMovable, pAnchorsPair, tX, tY, true, false, false);
	}

	static public void placeHorizontal(IEntity pImmovable, IEntity pMovable, AnchorPointsPair pAnchorsPair, final float tX, final float tY) {
		place(pImmovable, pMovable, pAnchorsPair, tX, tY, false, true, false);
	}

	static public void place(IEntity pImmovable, IEntity pMovable, AnchorPointsPair pAnchorsPair,
			final float tX, final float tY, boolean pMoveVertically, boolean pMoveHorizontally, boolean pSwapMovableSize) {
		pImmovable.convertLocalCoordinatesToSceneCoordinates(
				pAnchorsPair.anchorA.X * pImmovable.getWidth(),
				pAnchorsPair.anchorA.Y * pImmovable.getHeight(), TMP_ENTITY_ANCHORED);
		if (!pSwapMovableSize) {
			pMovable.convertLocalCoordinatesToSceneCoordinates(
					pAnchorsPair.anchorB.X * pMovable.getWidth(),
					pAnchorsPair.anchorB.Y * pMovable.getHeight(), TMP_ENTITY_MOVABLE);
		} else {
			pMovable.convertLocalCoordinatesToSceneCoordinates(
					pAnchorsPair.anchorB.X * pMovable.getHeight(),
					pAnchorsPair.anchorB.Y * pMovable.getWidth(), TMP_ENTITY_MOVABLE);
		}

		final float dX = !pMoveHorizontally ? 0 : TMP_ENTITY_MOVABLE[0] - TMP_ENTITY_ANCHORED[0];
		final float dY = !pMoveVertically ? 0 : TMP_ENTITY_MOVABLE[1] - TMP_ENTITY_ANCHORED[1];

		pMovable.setPosition(pMovable.getX() - dX + tX, pMovable.getY() - dY + tY);
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static class AnchorPoint {
		public final float X;
		public final float Y;

		private AnchorPoint(final float pX, final float pY) {
			this.X = pX;
			this.Y = pY;
		}
	}

	public static class AnchorPointsPair {
		public final AnchorPoint anchorA;
		public final AnchorPoint anchorB;

		private AnchorPointsPair(final AnchorPoint pAnchorA, final AnchorPoint pAnchorB) {
			this.anchorA = pAnchorA;
			this.anchorB = pAnchorB;
		}
	}

	public static enum eAnchorPoint {
		L_TOP(0.0f,1.0f),C_TOP(0.5f,1.0f),R_TOP(1.0f,1.0f),
		L_CNT(0.0f,0.5f),C_CNT(0.5f,0.5f),R_CNT(1.0f,0.5f),
		L_BTM(0.0f,0.0f),C_BTM(0.5f,0.0f),R_BTM(1.0f,0.0f);

		public final AnchorPoint anchor;

		private eAnchorPoint(final float pX, final float pY) {
			anchor = new AnchorPoint(pX, pY);
		}
	}

	public static enum eAnchorPointsPair {
		HOR_L_TOP(eAnchorPoint.L_TOP.anchor, eAnchorPoint.R_TOP.anchor),
		HOR_L_CNT(eAnchorPoint.L_CNT.anchor, eAnchorPoint.R_CNT.anchor),
		HOR_L_BTM(eAnchorPoint.L_BTM.anchor, eAnchorPoint.R_BTM.anchor),

		HOR_R_TOP(eAnchorPoint.R_TOP.anchor, eAnchorPoint.L_TOP.anchor),
		HOR_R_CNT(eAnchorPoint.R_CNT.anchor, eAnchorPoint.L_CNT.anchor),
		HOR_R_BTM(eAnchorPoint.R_BTM.anchor, eAnchorPoint.L_BTM.anchor),

		VER_TOP_L(eAnchorPoint.L_TOP.anchor, eAnchorPoint.L_BTM.anchor),
		VER_TOP_C(eAnchorPoint.C_TOP.anchor, eAnchorPoint.C_BTM.anchor),
		VER_TOP_R(eAnchorPoint.R_TOP.anchor, eAnchorPoint.R_BTM.anchor),

		VER_BTM_L(eAnchorPoint.L_BTM.anchor, eAnchorPoint.L_TOP.anchor),
		VER_BTM_C(eAnchorPoint.C_BTM.anchor, eAnchorPoint.C_TOP.anchor),
		VER_BTM_R(eAnchorPoint.R_BTM.anchor, eAnchorPoint.R_TOP.anchor),

		IN_L_TOP(eAnchorPoint.L_TOP.anchor),
		IN_L_CNT(eAnchorPoint.L_CNT.anchor),
		IN_L_BTM(eAnchorPoint.L_BTM.anchor),

		IN_R_TOP(eAnchorPoint.R_TOP.anchor),
		IN_R_CNT(eAnchorPoint.R_CNT.anchor),
		IN_R_BTM(eAnchorPoint.R_BTM.anchor),

		IN_C_TOP(eAnchorPoint.C_TOP.anchor),
		IN_C_CNT(eAnchorPoint.C_CNT.anchor),
		IN_C_BTM(eAnchorPoint.C_BTM.anchor);

		public final AnchorPointsPair relation;

		private eAnchorPointsPair(final AnchorPoint pAnchorA, final AnchorPoint pAnchorB) {
			relation = new AnchorPointsPair(pAnchorA, pAnchorB);
		}

		private eAnchorPointsPair(final AnchorPoint pAnchor) {
			relation = new AnchorPointsPair(pAnchor, pAnchor);
		}
	}
}
