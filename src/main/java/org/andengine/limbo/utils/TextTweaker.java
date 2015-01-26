package org.andengine.limbo.utils;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.vbo.HighPerformanceTextVertexBufferObject;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.font.Letter;
import org.andengine.util.adt.color.Color;
import org.andengine.util.exception.AndEngineRuntimeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextTweaker {
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
	public static void setCharColor(final Text pText, final Color pColor, int pCharPosition) {
		setCharColor(pColor.getABGRPackedFloat(), pCharPosition, pText);
		pText.getVertexBufferObject().setDirtyOnHardware();
	}

	public static void setCharsColor(final Color pColor, int pCharPosition, int pCharsNumber, final Text pText) {
		int i = 0;
		while ((pCharPosition + i) < (pCharPosition + pCharsNumber)) {
			setCharColor(pColor.getABGRPackedFloat(), pCharPosition + i++, pText);
		}
		pText.getVertexBufferObject().setDirtyOnHardware();
	}

	public static void setCharsColor(final Color pColor, final String pRegex, final Text pText) {
		if (pText.getText() == null) {
			return;
		}

		Pattern pattern = Pattern.compile(pRegex);
		Matcher matcher = pattern.matcher(pText.getText());

		while (matcher.find()) {
			setCharsColor(pColor, matcher.start(), matcher.end() - matcher.start(), pText);
		}
	}

	private static void setCharColor(final float pARGBPackedColor, int pCharPosition, final Text pText) {
		final IFont font = pText.getFont();
		final CharSequence text = pText.getText();

		if (text == null || font.getLetter(text.charAt(pCharPosition)).isWhitespace()) {
			return;
		}

		int bufferPosition = pCharPosition;
		for (int i = 0; i < pCharPosition; i++) {
			final Letter letter = font.getLetter(text.charAt(i));
			if (letter.isWhitespace()) {
				bufferPosition--;
			}
		}

		bufferPosition *= Text.LETTER_SIZE;

		if (pText.getVertexBufferObject() instanceof HighPerformanceTextVertexBufferObject) {
			float[] bufferData = ((HighPerformanceTextVertexBufferObject)pText.getVertexBufferObject()).getBufferData();

			bufferData[bufferPosition + 0 * Text.VERTEX_SIZE + Text.COLOR_INDEX] = pARGBPackedColor;
			bufferData[bufferPosition + 1 * Text.VERTEX_SIZE + Text.COLOR_INDEX] = pARGBPackedColor;
			bufferData[bufferPosition + 2 * Text.VERTEX_SIZE + Text.COLOR_INDEX] = pARGBPackedColor;
			bufferData[bufferPosition + 3 * Text.VERTEX_SIZE + Text.COLOR_INDEX] = pARGBPackedColor;
			bufferData[bufferPosition + 4 * Text.VERTEX_SIZE + Text.COLOR_INDEX] = pARGBPackedColor;
			bufferData[bufferPosition + 5 * Text.VERTEX_SIZE + Text.COLOR_INDEX] = pARGBPackedColor;
		} else {
			throw new AndEngineRuntimeException("to be implemented");
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
