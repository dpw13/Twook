/***********************************************
This file is part of the Twook project http://github.com/unchiujar/Twook

    Twook is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    Twook is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with Twook.  If not, see <http://www.gnu.org/licenses/>.

 **********************************************/
package com.nookdevs.twook.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.nookdevs.twook.services.DirectMessagesDownloaderService;

/**
 * Activity that displays the retweets of the user's messages.
 * 
 * @author Vasile Jureschi <vasile.jureschi@gmail.com>
 * @version 0.0.2
 * @since 0.0.2
 * @see TimelineActivity
 */

public class DirectMessagesTimelineActivity extends TimelineActivity {
	private static final String TAG = DirectMessagesDownloaderService.class
			.getName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set the title at the top of the eink screen
		final Resources res = getResources();
		NAME = res.getText(R.string.app_name).toString()
				+ res.getText(R.string.title_separator).toString()
				+ res.getText(R.string.direct_messages).toString();
	}

	@Override
	protected void createListeners() {
		btn_direct_messages_timeline.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				service.doDownload();
				Log.d(TAG, "Direct messages timeline button clicked");

			}
		});

	}

	@Override
	protected void setDownloadService() {
		service = new DirectMessagesDownloaderService();
		intent = new Intent(this, DirectMessagesDownloaderService.class);
		startService(intent);
		service.setMainActivity(this);
		service.startDownload();
	}
}