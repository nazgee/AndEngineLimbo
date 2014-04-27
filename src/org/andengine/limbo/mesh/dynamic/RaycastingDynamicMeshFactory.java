package org.andengine.limbo.mesh.dynamic;

import org.andengine.entity.primitive.DrawMode;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.andengine.limbo.mesh.dynamic.textured.DynamicTexturedMesh;
import org.andengine.limbo.mesh.uv.DynamicUVMapperCutout;
import org.andengine.limbo.mesh.xy.DynamicMeshXYProviderProxyScaling;
import org.andengine.limbo.mesh.xy.DynamicXYProviderFanRaycasting;
import org.andengine.limbo.mesh.xy.IDynamicMeshXYProvider;
import org.andengine.limbo.mesh.xy.IDynamicXYProvider;
import org.andengine.limbo.physics.raycast.RaycastListener;
import org.andengine.limbo.physics.raycast.initializer.RadialRaysInitializer;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.math.MathUtils;


public class RaycastingDynamicMeshFactory {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public static DynamicTexturedMesh createPointLight(ITextureRegion pTexture, float pRadius, int pRaysStep, int pRaysCount, float pPixelToMeter, final PhysicsWorld pWorld, VertexBufferObjectManager vbo) {
		final float scaleU = pRadius / (pTexture.getWidth() / 2);
		final float scaleV = pRadius / (pTexture.getHeight() / 2);
		return createPointLight(pTexture, pRadius, scaleU, scaleV, pRaysStep, pRaysCount, pPixelToMeter, pWorld, vbo);
	}

	public static DynamicTexturedMesh createPointLight(ITextureRegion pTexture, float pRadius, float pScaleU, float pScaleV, int pRaysStep, int pRaysCount, float pPixelToMeter, final PhysicsWorld pWorld, VertexBufferObjectManager vbo) {

		final RadialRaysInitializer raysInitializer = new RadialRaysInitializer(pRadius/pPixelToMeter, pRaysStep, pRaysCount);
		final DynamicXYProviderFanRaycasting xyProvider = new DynamicXYProviderFanRaycasting(raysInitializer, new RaycastListener());
		final IDynamicMeshXYProvider xyProviderMesh = new DynamicMeshXYProviderProxyScaling(xyProvider, PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		final DynamicUVMapperCutout uvMapper = new DynamicUVMapperCutout(pTexture, xyProviderMesh, pScaleU, pScaleV, 0.5f, 0.5f);

		xyProvider.setPhysicsWorld(pWorld);
		DynamicTexturedMesh mesh = new DynamicTexturedMesh(0, 0, xyProviderMesh, uvMapper, DrawMode.TRIANGLE_FAN,
				pTexture, vbo, DrawType.STREAM);
		return mesh;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
//	private static final class ShiningUVMapper extends DynamicUVMapperCutout {
//		float oldScaleU;
//		float oldScaleV;
//		float rayScalesU[];
//		float rayScalesV[];
//		{
//			rayScalesU = new float[mVertexProvider.getVertexCount()];
//			rayScalesV = new float[mVertexProvider.getVertexCount()];
//			oldScaleU = mTextureScaleU;
//			oldScaleV = mTextureScaleV;
//			for (int i = 0; i < rayScalesU.length; i++) {
//				rayScalesU[i] = mTextureScaleU;
//				rayScalesV[i] = mTextureScaleV;
//			}
//		}
//
//		private ShiningUVMapper(ITextureRegion pTextureRegion, IDynamicXYProvider pVertexProviderFan, float pTextureScaleU,
//				float pTextureScaleV, float pAnchorU, float pAnchorV) {
//			super(pTextureRegion, pVertexProviderFan, pTextureScaleU, pTextureScaleV, pAnchorU, pAnchorV);
//		}
//
//		private float randomizeScales(final float min, final float max, int vertexNumber, float rayArray[], float origScale) {
//			if (MathUtils.random(100) > 5) {
//				return rayArray[vertexNumber];
//			}
//			return rayArray[vertexNumber] = origScale * MathUtils.random(min, max);
//		}
//
//		private void restoreScales(int vertexNumber) {
//			if (MathUtils.random(100) > 5) {
//				return;
//			}
//			rayScalesU[vertexNumber] = oldScaleU;
//			rayScalesV[vertexNumber] = oldScaleV;
//			mTextureScaleU = oldScaleU;
//			mTextureScaleV = oldScaleV;
//		}
//
//		@Override
//		public float getV(int i) {
//			mTextureScaleU = randomizeScales(0.99f, 1.1f, i, rayScalesU, oldScaleU);
//			float v =  super.getV(i);
//			restoreScales(i);
//			return v;
//		}
//
//		@Override
//		public float getU(int i) {
//			mTextureScaleV = randomizeScales(0.99f, 1.1f, i, rayScalesV, oldScaleV);
//			float u =  super.getU(i);
//			restoreScales(i);
//			return u;
//		}
//	}

}
