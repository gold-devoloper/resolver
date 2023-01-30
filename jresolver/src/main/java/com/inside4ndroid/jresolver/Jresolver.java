package com.inside4ndroid.jresolver;

import android.content.Context;

import androidx.annotation.NonNull;

import com.androidnetworking.AndroidNetworking;
import com.inside4ndroid.jresolver.Sites.Amazon;
import com.inside4ndroid.jresolver.Sites.Aparat;
import com.inside4ndroid.jresolver.Sites.Archive;
import com.inside4ndroid.jresolver.Sites.BitChute;
import com.inside4ndroid.jresolver.Sites.BitTube_dead;
import com.inside4ndroid.jresolver.Sites.Brighteon;
import com.inside4ndroid.jresolver.Sites.CloudVideo;
import com.inside4ndroid.jresolver.Sites.DMotion;
import com.inside4ndroid.jresolver.Sites.DeadlyBlogger;
import com.inside4ndroid.jresolver.Sites.Dood;
import com.inside4ndroid.jresolver.Sites.EplayVid;
import com.inside4ndroid.jresolver.Sites.EvoLoad;
import com.inside4ndroid.jresolver.Sites.FShared;
import com.inside4ndroid.jresolver.Sites.FanSubs;
import com.inside4ndroid.jresolver.Sites.Diasfem;
import com.inside4ndroid.jresolver.Sites.GDStream;
import com.inside4ndroid.jresolver.Sites.GUContent;
import com.inside4ndroid.jresolver.Sites.GoUnlimited;
import com.inside4ndroid.jresolver.Sites.HDVid;
import com.inside4ndroid.jresolver.Sites.Midian;
import com.inside4ndroid.jresolver.Sites.MixDrop;
import com.inside4ndroid.jresolver.Sites.StreamLare;
import com.inside4ndroid.jresolver.Sites.StreamSB;
import com.inside4ndroid.jresolver.Sites.StreamTape;
import com.inside4ndroid.jresolver.Sites.StreamVid;
import com.inside4ndroid.jresolver.Sites.Upstream;
import com.inside4ndroid.jresolver.Sites.VidMoly;
import com.inside4ndroid.jresolver.Sites.VideoBIN;
import com.inside4ndroid.jresolver.Sites.VideoBM;
import com.inside4ndroid.jresolver.Sites.VoeSX;
import com.inside4ndroid.jresolver.Sites.Voxzer_dead;
import com.inside4ndroid.jresolver.Sites.Vudeo;
import com.inside4ndroid.jresolver.Sites.YT;
import com.inside4ndroid.jresolver.Sites.YodBox;
import com.inside4ndroid.jresolver.Utils.DailyMotionUtils;
import com.inside4ndroid.jresolver.Model.Jmodel;
import com.inside4ndroid.jresolver.Sites.FB;
import com.inside4ndroid.jresolver.Sites.FEmbed;
import com.inside4ndroid.jresolver.Sites.FileRIO;
import com.inside4ndroid.jresolver.Sites.GPhotos;
import com.inside4ndroid.jresolver.Sites.MFire;
import com.inside4ndroid.jresolver.Sites.MP4Upload;
import com.inside4ndroid.jresolver.Sites.OKRU;
import com.inside4ndroid.jresolver.Sites.SendVid;
import com.inside4ndroid.jresolver.Sites.SolidFiles;
import com.inside4ndroid.jresolver.Sites.TW;
import com.inside4ndroid.jresolver.Sites.VK;
import com.inside4ndroid.jresolver.Sites.Vidoza;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.inside4ndroid.jresolver.Utils.FacebookUtils.check_fb_video;

public class Jresolver {

    private Context context;
    private OnTaskCompleted onComplete;
    public static final String agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";
    private final String mp4upload = "https?:\\/\\/(www\\.)?(mp4upload)\\.[^\\/,^\\.]{2,}\\/.+";
    private final String filerio = "https?:\\/\\/(www\\.)?(filerio)\\.[^\\/,^\\.]{2,}\\/.+";
    private final String sendvid = "https?:\\/\\/(www\\.)?(sendvid)\\.[^\\/,^\\.]{2,}\\/.+";
    private final String gphoto = "https?:\\/\\/(photos.google.com)\\/(u)?\\/?(\\d)?\\/?(share)\\/.+(key=).+";
    private final String mediafire = "https?:\\/\\/(www\\.)?(mediafire)\\.[^\\/,^\\.]{2,}\\/(file)\\/.+";
    private final String okru = "https?:\\/\\/(www.|m.)?(ok)\\.[^\\/,^\\.]{2,}\\/(video|videoembed)\\/.+";
    private final String vk = "https?:\\/\\/(www\\.)?vk\\.[^\\/,^\\.]{2,}\\/video\\-.+";
    private final String twitter = "http(?:s)?:\\/\\/(?:www\\.)?twitter\\.com\\/([a-zA-Z0-9_]+)";
    private final String youtube = "^((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?$";
    private final String solidfiles = "https?:\\/\\/(www\\.)?(solidfiles)\\.[^\\/,^\\.]{2,}\\/(v)\\/.+";
    private final String vidoza = "https?:\\/\\/(www\\.)?(vidoza)\\.[^\\/,^\\.]{2,}.+";
    private final String fembed = "https?:\\/\\/(www\\.)?(feurl|femax20|24hd|anime789|[fv]cdn|sharinglink|streamm4u|votrefil|femoload|asianclub|dailyplanet|[jf]player|mrdhan|there|sexhd|gcloud|mediashore|xstreamcdn|vcdnplay|vidohd|vidsource|viplayer|zidiplay|embedsito|dutrag|youvideos|moviepl|vidcloud|diasfem|moviemaniac|albavido|ncdnstm|superplayxyz|cinegrabber|ndrama|fembed|vcdn|fcdn|lajkema|fembed-hd|suzihaza|streamm4u|vanfem)\\.[^\\/,^\\.]{2,}\\/(v|f)\\/.+";
    private final String vidbm = "https?:\\/\\/(vidbam)\\.[^\\/,^\\.]{2,}\\/.+";
    private final String bitTube = "https?:\\/\\/(www\\.)?(bittube\\.video\\/videos)\\/(watch|embed)\\/.+";
    private final String videoBIN = "https?:\\/\\/(videobin\\.co)\\/.+";
    private final String fourShared = "https?:\\/\\/(www\\.)?(4shared\\.com)\\/(video|web\\/embed)\\/.+";
    private final String streamtape = "https?:\\/\\/(www\\.)?(streamtape\\.com|shavetape\\.cash|streamtape\\.to|strtape\\.cloud|streamtape\\.net|streamta\\.pe|streamtape\\.site|strcloud\\.link|strtpe\\.link|streamtape\\.cc|scloud\\.online|stape\\.fun|streamadblockplus\\.com|shavetape\\.cash)\\/(v|e)\\/.+";
    private final String vudeo = "https?:\\/\\/(www\\.)?(vudeo\\.net)\\/.+";
    private final String amazon = "https?:\\/\\/(www\\.)?(amazon\\.com)\\/?(clouddrive)\\/+";
    private final String doodstream = "(?://|\\.)(dood(?:stream)?\\.(?:com|watch|to|so|la|ws|sh|pm|re|wf))/(?:d|e)/([0-9a-zA-Z]+)";
    private final String streamsb = ".+(sblongvu|sbspeed|sbembed|sbembed1|sbplay.org|sbvideo|streamsb|sbchill|streamsss|sblanh|sblongvu|sbplay|cloudemb|playersb|tubesb|sbplay1|embedsb|watchsb|sbplay2|japopav|viewsb|sbplay2|sbfast|sbfull|javplaya|ssbstream|p1ayerjavseen|sbthe)\\.(com|net|one|org|tv|xyz)/.+";
    private final String mixdrop = ".+(mixdrop)\\.(co|to|sx|bz)\\/.+";
    private final String gounlimited = "https?:\\/\\/(www\\.)?(gounlimited)\\.[^\\/,^\\.]{2,}\\/.+";
    private final String voxzer = "https?:\\/\\/(player\\.)?(voxzer\\.)(?:org).+";
    private final String aparat = ".+(aparat\\.com/v/).+";
    private final String archive = ".+(archive)\\.(org)\\/.+";
    private final String bitchute = ".+(bitchute\\.com)/(?:video|embed).+";
    private final String brighteon = ".+(brighteon\\.com).+";
    private final String deadlyblogger = ".+(deadlyblogger\\.com).+";
    private final String fansubs = "https?:\\/\\/(www\\.)?(fansubs\\.tv)\\/(v|watch)\\/.+";
    private final String diasfem = ".+(diasfem\\.com|suzihaza)/v|f/.+";
    private final String gdstream = ".+(gdstream\\.net)/v|f/.+";
    private final String googleusercontent = ".+(googleusercontent\\.com).+";
    private final String hdvid = ".+(hdvid|vidhdthe)\\.(tv|fun|online)/.+";
    private final String mediashore = ".+(mediashore\\.org)/v|f/.+";
    private final String voesx = ".+(voe\\.sx|voe-unblock\\.com).+";
    private final String gomoplayer = ".+(gomoplayer\\.com).+";
    private final String eplayvid = ".+(eplayvid)\\.(com|net)/.+";
    private final String vidmoly = ".+(vidmoly)\\.(me)/.+";
    private final String midian =  ".+(midian\\.appboxes)\\.(co)/.+";
    private final String upstream = ".+(upstream)\\.(to)/.+";
    private final String yodbox = ".+(yodbox)\\.(com)/.+";
    private final String evoload = ".+(evoload)\\.(io)/.+";
    private final String cloudvideo = ".+(cloudvideo)\\.(tv)/.+";
    private final String streamlare = "(?://|\\.)((?:streamlare|sl(?:maxed|tube|watch))\\.(?:com?|org))/(?:e|v)/([0-9A-Za-z]+)";
    private final String streamvid = "(?://|\\.)(streamvid\\.net)/(?:embed-)?([0-9a-zA-Z]+)";

    public Jresolver(@NonNull Context context){
        this.context=context;
        AndroidNetworking.initialize(context);
    }

    public void find(String url){
        if (check(mp4upload, url)) {
            MP4Upload.fetch(url,onComplete);
        } else if (check(vidmoly,url)){
            VidMoly.fetch(url,onComplete);
        } else if (check(streamvid,url)){
            StreamVid.fetch(url,onComplete);
        } else if (check(streamlare,url)){
            StreamLare.fetch(url,onComplete);
        } else if (check(evoload,url)){
            EvoLoad.fetch(url,onComplete);
        } else if (check(cloudvideo,url)){
            CloudVideo.fetch(url,onComplete);
        } else if (check(yodbox,url)){
            YodBox.fetch(url,onComplete);
        } else if (check(upstream,url)){
            Upstream.fetch(url,onComplete);
        } else if (check(midian,url)){
            Midian.fetch(url,onComplete);
        } else if (check(eplayvid,url)){
            EplayVid.fetch(url,onComplete);
        }  else if (check(voesx, url)) {
            VoeSX.fetch(url, onComplete);
        } else if (check(hdvid, url)) {
            if(url.contains("embed")){
                HDVid.fetch(url,onComplete);
            } else {
                String[] splits = url.split("/");
                String ID = splits[splits.length-1];
                String new_url = "https://hdvid.fun/embed-"+ID;
                HDVid.fetch(new_url,onComplete);
            }
        }else if (check(googleusercontent, url)) {
            GUContent.fetch(url,onComplete);
        } else if (check(sendvid, url)) {
            SendVid.fetch(url,onComplete);
        } else if (check(gdstream, url)) {
            GDStream.fetch(url,onComplete);
        } else if (check(diasfem, url)) {
            Diasfem.fetch(url,onComplete);
        } else if (check(fansubs, url)) {
            FanSubs.fetch(url,onComplete);
        } else if (check(deadlyblogger, url)) {
            DeadlyBlogger.fetch(url,onComplete);
        } else if (check(brighteon, url)) {
            Brighteon.fetch(url,onComplete);
        } else if (check(bitchute, url)) {
            BitChute.fetch(url,onComplete);
        } else if (check(archive, url)) {
            Archive.fetch(url,onComplete);
        } else if (check(aparat, url)) {
            Aparat.fetch(url,onComplete);
        } else if (check(voxzer, url)) {
            Voxzer_dead.fetch(url,onComplete);
        } else if (check(mixdrop, url)) {
            MixDrop.fetch(url,onComplete);
        } else if (check(streamsb, url)) {
            StreamSB.fetch(url,onComplete);
        } else if (check(doodstream, url)) {
            Dood.fetch(url,onComplete);
        } else if (check(amazon, url)) {
            Amazon.fetch(url,onComplete);
        } else if (check(gphoto, url)) {
            GPhotos.fetch(url,onComplete);
        } else if (check_fb_video(url)) {
            FB.fetch(url,onComplete);
        } else if (check(mediafire, url)) {
            MFire.fetch(context,url,onComplete);
        } else if (check(okru,url)){
            OKRU.fetch(url,onComplete);
        } else if (check(vk,url)){
            VK.fetch(url,onComplete);
        } else if (check(twitter,url)){
            TW.fetch(url,onComplete);
        } else if (check(solidfiles,url)){
            SolidFiles.fetch(url,onComplete);
        } else if (check(vidoza,url)){
            Vidoza.fetch(url,onComplete);
        } else if (check(fembed,url)){
            FEmbed.fetch(url,onComplete);
        } else if (check(filerio,url)){
            FileRIO.fetch(url,onComplete);
        } else if (DailyMotionUtils.getDailyMotionID(url)!=null){
            DMotion.fetch(url,onComplete);
        } else if (check(vidbm,url)){
            VideoBM.fetch(url,onComplete);
        } else if (check(bitTube,url)){
            BitTube_dead.fetch(url,onComplete);
        } else if (check(videoBIN,url)){
            VideoBIN.fetch(url,onComplete);
        } else if (check(fourShared,url)){
            FShared.fetch(url,onComplete);
        }else if (check(streamtape,url)){
            if(url.contains("shavetape.cash")){
                url = url.replace("shavetape.cash", "streamtape.to");
                StreamTape.fetch(url,onComplete);
            } else {
                StreamTape.fetch(url,onComplete);
            }

        } else if (check(vudeo,url)) {
            Vudeo.fetch(url, onComplete);
        } else  if (check(gounlimited,url)){
            GoUnlimited.fetch(url,onComplete);
        } else if (check(youtube,url)) {
            YT.fetch(context,url, onComplete);
        } else onComplete.onError();
    }

    public interface OnTaskCompleted {
        void onTaskCompleted(ArrayList<Jmodel> vidURL, boolean multiple_quality);
        void onError();
    }

    public void onFinish(OnTaskCompleted onComplete) {
        this.onComplete = onComplete;
    }

    private boolean check(String regex, String string) {
        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}
