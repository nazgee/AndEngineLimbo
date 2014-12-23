package org.andengine.limbo.ui.dialog;

import org.andengine.util.call.Callback;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * (c) 2013 Michal Stawinski (nazgee)
 *
 * @author Nicolas Gramlich
 * @since 09:35:55 - 14.12.2009
 */
public class DecissionDialogBuilder {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final Callback<Boolean> mDecissionCallback;
	protected final int mTitleResID;
	protected final int mMessageResID;
	protected final int mIconResID;
	protected final Context mContext;
	private final int mPositiveResID;
	private final int mNegativeResID;

	// ===========================================================
	// Constructors
	// ===========================================================


	public DecissionDialogBuilder(final Context pContext, final int pTitleResID, final int pMessageResID, final int pIconResID,
			final int pPositiveResID, final int pNegativeResID, final Callback<Boolean> pDecissionCallback) {
		this.mContext = pContext;
		this.mTitleResID = pTitleResID;
		this.mMessageResID = pMessageResID;
		this.mIconResID = pIconResID;
		this.mPositiveResID = pPositiveResID;
		this.mNegativeResID = pNegativeResID;
		this.mDecissionCallback = pDecissionCallback;
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

	public Dialog create() {
		final AlertDialog.Builder ab = new AlertDialog.Builder(this.mContext);
		if (this.mTitleResID != 0) {
			ab.setTitle(this.mTitleResID);
		}
		if (this.mMessageResID != 0) {
			ab.setMessage(this.mMessageResID);
		}
		if (this.mIconResID != 0) {
			ab.setIcon(this.mIconResID);
		}

		ab.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				DecissionDialogBuilder.this.mDecissionCallback.onCallback(false);
			}
		})
		.setPositiveButton(mPositiveResID, new OnClickListener() {
			@Override
			public void onClick(final DialogInterface pDialog, final int pWhich) {
				DecissionDialogBuilder.this.mDecissionCallback.onCallback(true);
				pDialog.dismiss();
			}
		})
		.setNegativeButton(mNegativeResID, new OnClickListener() {
			@Override
			public void onClick(final DialogInterface pDialog, final int pWhich) {
				DecissionDialogBuilder.this.mDecissionCallback.onCallback(false);
				pDialog.dismiss();
			}
		});

		return ab.create();
	}


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
