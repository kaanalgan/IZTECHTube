package iztechtube.updatehandler;

import iztechtube.storage.FileStorage;
import iztechtube.dataformatter.JSONFormatter;
import iztechtube.video.Video;

import java.util.*;

public class VideoDataHandler extends AbstractDataHandler<Integer, Video> implements Observer {

    public VideoDataHandler(){
        super(new JSONFormatter(), new FileStorage("videos.json"));
    }

    @Override
    public Video get(Integer identifier) {
        Video video = super.get(identifier);
        if(video !=null){
            video.addObserver(this);
        }
        return video;
    }

    public List<Integer> getVideoIds() { return new ArrayList<>(getDataMap().keySet()); }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Video){
            Video video = (Video) o;
            modify(video.getId(), video);
        }
    }
}
