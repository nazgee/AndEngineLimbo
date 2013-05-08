package org.andengine.limbo.math.circularfloat;

import org.andengine.limbo.math.circularfloat.BoundedFifoBuffer.BoundedFifoBufferIterator;

public class CircularFifoUtils {
	private static final BoundedFifoBufferIterator sIterator = new BoundedFifoBufferIterator(null);

	public static final float avgFloat(CircularFifoBuffer pBuffer) {
		return sumFloat(pBuffer) / pBuffer.size();
	}

	public static final float sumFloat(CircularFifoBuffer pBuffer) {
		float sum = 0;

		for (sIterator.reinitialize(pBuffer); sIterator.hasNext();) {
			final Float val = (Float) sIterator.next();
			sum += val;
		}
		return sum;
	}
}
