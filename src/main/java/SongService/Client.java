package SongService;

import com.google.gson.Gson;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private TTransport transport;
    private TProtocol protocol;
    private SongService.Client client;
    
    private Gson gson = new Gson();
    public Client() throws TTransportException {
        transport = new TFramedTransport(new TSocket("localhost", 9090));
        transport.open();
        protocol = new TBinaryProtocol(transport);
        client = new SongService.Client(protocol);
    }
    public String performGetSong(int id){
        SongResult res = null;
        try{
            res = client.get(id);

        }catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(res);
    }

    public String performGetListSongOfArtist(String nameOfSinger){
        List<SongStruct> res = new ArrayList<>();
        try{
            ListSongResult list = client.getListSongOfSinger(nameOfSinger);
            for(int i : list.listSong){
                res.add(client.get(i).song);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(res);
    }

    public String performGetTopStream(int topX){
        List<SongStruct> res = new ArrayList<>();
        try{
            ListSongResult list = client.getTopStream(topX);
            for(int i : list.listSong){
                res.add(client.get(i).song);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(res);
    }

    public String performGetTopLike(int topX){
        List<SongStruct> res = new ArrayList<>();
        try{
            ListSongResult list = client.getTopLike(topX);
            for(int i : list.listSong){
                res.add(client.get(i).song);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(res);
    }

    public String performLikeSong(int id){
        Error res = null;
        try{
            res = client.like(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(res);
    }

    public String performUnlikeSong(int id){
        Error res = null;
        try{
            res = client.unlike(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(res);
    }

    public String performStreamSong(int id){
        Error res = null;
        try{
            res = client.stream(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(res);
    }

    public String performPutSong(String name, List<String> singers){
        Error res = null;
        try{
            res = client.put(name, singers);
        }catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(res);
    }

    public String performRemoveSong(int id){
        Error res = null;
        try{
            res = client.remove(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(res);
    }
}
