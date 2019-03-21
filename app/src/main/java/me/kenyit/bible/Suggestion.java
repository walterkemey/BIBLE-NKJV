/*
 * vim: set sta sw=4 et:
 *
 * Copyright (C) 2013 Liu DongMiao <thom@piebridge.me>
 *
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 *
 */

package me.kenyit.bible;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class Suggestion extends ContentProvider {

    private static final String TAG = "me.kenyit.bible$Suggestion";
    private Bible bible = null;

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "oncreate");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (bible == null) {
            bible = Bible.getBible(getContext());
            bible.checkBibleData(true, null);
        }
        Log.d(TAG , "uri: " + uri);
        String query = uri.getLastPathSegment();
        try {
            String[] columnNames = { BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1, SearchManager.SUGGEST_COLUMN_INTENT_DATA };
            MatrixCursor cursor = new MatrixCursor(columnNames);
            LinkedHashMap<String, String> suggestions = bible.getOsiss(query, 66);
            int i = 0;
            for (Entry<String, String> entry: suggestions.entrySet()) {
                String[] row = { String.valueOf(i), entry.getKey(), "bible://passage?search=" + entry.getKey() };
                cursor.addRow(row);
                i++;
            }
            if (!SearchManager.SUGGEST_URI_PATH_QUERY.equals(query)) {
                String[] row = {String.valueOf(i), getContext().getString(R.string.search, new Object[] {query}), "bible://passage?search=" + query };
                cursor.addRow(row);
            }
            return cursor;
        } catch (Exception e) {
            Log.e(TAG, "query", e);
            return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

}
