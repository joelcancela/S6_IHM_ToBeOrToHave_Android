package fr.unice.polytech.jcancela.tobeortohave.fragment.twitter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import fr.unice.polytech.jcancela.tobeortohave.R;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Joel CANCELA VAZ on 22/04/2017.
 */

public class EmbeddedTwitterTimelineFragment extends android.support.v4.app.Fragment {

    private static final String TWITTER_KEY = "OTeJHn5fLGFAhAZ0GlQnVZad2";
    private static final String TWITTER_SECRET = "vvcUQuMZpHdB1NI7bxP3rOW0hblGt18CRWr3fJmUUwthBOEt0T";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twitter, container, false);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this.getContext(), new Twitter(authConfig));

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("_tobeortohave")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this.getContext())
                .setTimeline(userTimeline)
                .build();
        ListView tweetsList = (ListView) view.findViewById(R.id.tweets_list);
        tweetsList.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
