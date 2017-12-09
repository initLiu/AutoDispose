package com.uber.autodispose.sample;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.uber.autodispose.BlackMirror;

/**
 * Content provider, to auto-init in consuming apps before onCreate.
 *
 * <p>Based on https://medium.com/@andretietz/auto-initialize-your-android-library-2349daf06920
 */
public final class InitProvider extends ContentProvider {

  public InitProvider() {}

  @SuppressWarnings("ConstantConditions") @Override public boolean onCreate() {
    try {
      Log.d("BLAH", "InitProvider.static initializer - ");
      BlackMirror.install(getContext());
      Log.d("BLAH", "InitProvider.static initializer - Success");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return true;
  }

  @Override public void attachInfo(Context context, ProviderInfo providerInfo) {
    if (providerInfo == null) {
      throw new NullPointerException(
          InitProvider.class.getSimpleName() + " ProviderInfo cannot be null.");
    }
    // So if the authorities equal the library internal ones, the developer forgot to set their
    // applicationId
    //if (InitProvider.class.getName().equals(providerInfo.authority)) {
    //  throw new IllegalStateException(
    //      "Incorrect provider authority in manifest. Most likely due to a "
    //          + "missing applicationId variable in application\'s build.gradle.");
    //}
    super.attachInfo(context, providerInfo);
  }

  @Nullable @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    return null;
  }

  @Nullable @Override public String getType(Uri uri) {
    return null;
  }

  @Nullable @Override public Uri insert(Uri uri, ContentValues values) {
    return null;
  }

  @Override public int delete(Uri uri, String selection, String[] selectionArgs) {
    return 0;
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    return 0;
  }
}
