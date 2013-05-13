package org.andengine.limbo.widgets;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.IEntityComparator;
import org.andengine.entity.IEntityMatcher;
import org.andengine.entity.IEntityParameterCallable;

/**
 * (c) 2013 Michal Stawinski (nazgee)
 *
 * @author Michal Stawinski
 * @since 20:31:01 - 13.05.2013
 */
public class ClippingWindowContainer extends ClippingWindow {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private final Entity mContainer;
	// ===========================================================
	// Constructors
	// ===========================================================
	public ClippingWindowContainer(final float pWidth, final float pHeight) {
		super(pWidth, pHeight);
		mContainer = new Entity(pWidth/2, pHeight/2, pWidth, pHeight);

		getWindow().attachChild(mContainer);
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public Entity getContainer() {
		return mContainer;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public int getChildCount() {
		return mContainer.getChildCount();
	}
	@Override
	public IEntity getChildByTag(int pTag) {
		return mContainer.getChildByTag(pTag);
	}
	@Override
	public IEntity getChildByIndex(int pIndex) {
		return mContainer.getChildByIndex(pIndex);
	}
	@Override
	public IEntity getChildByMatcher(IEntityMatcher pEntityMatcher) {
		return mContainer.getChildByMatcher(pEntityMatcher);
	}
	@Override
	public ArrayList<IEntity> query(IEntityMatcher pEntityMatcher) {
		return mContainer.query(pEntityMatcher);
	}
	@Override
	public IEntity queryFirst(IEntityMatcher pEntityMatcher) {
		return mContainer.queryFirst(pEntityMatcher);
	}
	@Override
	public <S extends IEntity> S queryFirstForSubclass(IEntityMatcher pEntityMatcher) {
		return mContainer.queryFirstForSubclass(pEntityMatcher);
	}
	@Override
	public <L extends List<IEntity>> L query(IEntityMatcher pEntityMatcher, L pResult) {
		return mContainer.query(pEntityMatcher, pResult);
	}
	@Override
	public <S extends IEntity> ArrayList<S> queryForSubclass(IEntityMatcher pEntityMatcher) throws ClassCastException {
		return mContainer.queryForSubclass(pEntityMatcher);
	}
	@Override
	public <L extends List<S>, S extends IEntity> L queryForSubclass(IEntityMatcher pEntityMatcher, L pResult) throws ClassCastException {
		return mContainer.queryForSubclass(pEntityMatcher, pResult);
	}
	@Override
	public void detachChildren() {
		mContainer.detachChildren();
	}
	@Override
	public void attachChild(IEntity pEntity) throws IllegalStateException {
		mContainer.attachChild(pEntity);
	}
	@Override
	public void sortChildren() {
		mContainer.sortChildren();
	}
	@Override
	public void sortChildren(boolean pImmediate) {
		mContainer.sortChildren(pImmediate);
	}
	@Override
	public void sortChildren(IEntityComparator pEntityComparator) {
		mContainer.sortChildren(pEntityComparator);
	}
	@Override
	public boolean detachChild(IEntity pEntity) {
		return mContainer.detachChild(pEntity);
	}
	@Override
	public IEntity detachChild(int pTag) {
		return mContainer.detachChild(pTag);
	}
	@Override
	public IEntity detachChild(IEntityMatcher pEntityMatcher) {
		return mContainer.detachChild(pEntityMatcher);
	}
	@Override
	public boolean detachChildren(IEntityMatcher pEntityMatcher) {
		return mContainer.detachChildren(pEntityMatcher);
	}
	@Override
	public void callOnChildren(IEntityParameterCallable pEntityParameterCallable) {
		mContainer.callOnChildren(pEntityParameterCallable);
	}
	@Override
	public void callOnChildren(IEntityParameterCallable pEntityParameterCallable, IEntityMatcher pEntityMatcher) {
		mContainer.callOnChildren(pEntityParameterCallable, pEntityMatcher);
	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
