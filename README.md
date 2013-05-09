AndEngineLimbo
==============

Collection of arbitrarily chosen features that are generic enough to be AndEngine extension, but not good enough to make it to the core
Only GLES2-AnchorCenter branch of AE is supported!

# Positioner

Allows relational positioning of two entities. First one is used as an anchor (immovable), and the other one will be posiioned (movable).
- positioning one Entity inside of another (most of common cases)
- positioning one Entity outside of another (most of common cases)
- positioning with translation
- supports rotated Entities (both movable, and immovable) but it works best for right angles (0,90,180,270)
- Enitites do NOT need to be children of same parent (can be attached to other Entites)
- Enitites MUST share the same Scene

Example usage:
	PositionerSmart.getInstance().center(this, rectWhite);
	PositionerSmart.getInstance().center(rectWhite, rectRed);
	PositionerSmart.getInstance().placeLeftOfAndCenter(rectWhite, rectGreen);
	PositionerSmart.getInstance().alignTopEdgesAndCenter(rectGreen, rectYellow);
	PositionerSmart.getInstance().placeBelowOfRightsAligned(rectWhite, rectBlue);
	PositionerSmart.getInstance().placeRightOfAndCenter(rectWhite, rectCyan);
	PositionerSmart.getInstance().placeBelowOfRightsAligned(rectCyan, rectPink);
	PositionerSmart.getInstance().alignBottomLeftEdges(rectWhite, rectBlack, 5, 5)

origin: http://www.andengine.org/forums/features/positioner-t11930.html

# GradientFonts

Whole infrastructure to simplify creation of gradient-coloured fonts (stroked and regular)

origin: http://www.andengine.org/forums/gles2/gradient-to-font-t7948.html#p34667
