package com.nookdevs.twook.services;

import java.util.ArrayList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import android.util.Log;

import com.nookdevs.twook.activities.Settings;
import com.nookdevs.twook.activities.Tweet;
import com.nookdevs.twook.utilities.Utilities;

public class HomeTimelineDownloaderService extends MessagesDownloaderService {
    private static final String TAG = HomeTimelineDownloaderService.class
	    .getName();

    @Override
    protected ArrayList<Tweet> getTweets() {
		Settings settings = Settings.getSettings(this);
		Twitter twitter = settings.getConnection();

		ResponseList<Status> statuses;
		try {
		    statuses = twitter.getHomeTimeline();
		    return Utilities.statusToTweets(statuses);
		} catch (TwitterException e) {
		    Log.e(TAG, e.getMessage());
		    return new ArrayList<Tweet>();
		}

    }
}
