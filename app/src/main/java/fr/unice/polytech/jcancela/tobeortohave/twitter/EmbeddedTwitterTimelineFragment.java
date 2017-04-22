package fr.unice.polytech.jcancela.tobeortohave.twitter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import fr.unice.polytech.jcancela.tobeortohave.R;

/**
 * Created by Joel CANCELA VAZ on 22/04/2017.
 */

public class EmbeddedTwitterTimelineFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twitter_feed, container, false);
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
