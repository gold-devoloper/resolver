package com.inside4ndroid.jresolver.Sites;

import com.inside4ndroid.jresolver.Jresolver;
import com.inside4ndroid.jresolver.Model.Jmodel;
import java.util.ArrayList;

public class Midian {
    public static void fetch(String url, final Jresolver.OnTaskCompleted onComplete){
            ArrayList<Jmodel> jModels = new ArrayList<>();
            Jmodel jModel = new Jmodel();
            jModel.setUrl(url);
            jModel.setQuality("Normal");
            jModels.add(jModel);
            onComplete.onTaskCompleted(jModels,false);
    }
}
