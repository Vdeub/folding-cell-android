package com.ramotion.foldingcell.examples.service;

import com.ramotion.foldingcell.examples.data.GetSeriesDetailParser;
import com.ramotion.foldingcell.examples.data.GetSeriesParser;
import com.ramotion.foldingcell.examples.model.TvShow;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class DetailsService extends ServiceBase {

    private static final String URL = "http://thetvdb.com/api/" + API_KEY + "/series/"; // <seriesid>/all/en.xml";

    public TvShow getSeriesDetails(String seriesId) {
        try {
            InputStream stream = null;
            TvShow show;
            try {
                stream = downloadUrl(URL + seriesId + "/all/en.xml");

                GetSeriesDetailParser parser = new GetSeriesDetailParser();
                show = parser.parse(stream);
            } finally {
                if (stream != null) {
                    stream.close();
                }
            }
            return show;
        } catch (IOException e) {
            return new TvShow();
        } catch (XmlPullParserException e) {
            return new TvShow();
        }
    }
}
