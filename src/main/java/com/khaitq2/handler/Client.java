package com.khaitq2.handler;

import com.khaitq2.songservice.*;
import com.google.gson.Gson;
import com.khaitq2.songservice.Error;
import org.apache.thrift.TException;
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
    public Client(){
        try {
            transport = new TFramedTransport(new TSocket("localhost", 9090));
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
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
        ListSongResult list = new ListSongResult();
        try{
            list = client.getListSongOfSinger(nameOfSinger);
        } catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(_getListSong(list));
    }

    public String performGetTopStream(int topX){
        ListSongResult list = null;
        try{
            list = client.getTopStream(topX);
        } catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(_getListSong(list));
    }

    public String performGetTopLike(int topX){
        ListSongResult list = null;
        try{
            list = client.getTopLike(topX);
        } catch (Exception e){
            e.printStackTrace();
        }
        return gson.toJson(_getListSong(list));
    }

    public String performLikeSong(int id){
        Error res = null;
        try{
            res = client.like(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res.toString();
    }

    public String performUnlikeSong(int id){
        Error res = null;
        try{
            res = client.unlike(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res.toString();
    }

    public String performStreamSong(int id){
        Error res = null;
        try{
            res = client.stream(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res.toString();
    }

    public String performPutSong(String name, List<String> singers){
        Error res = null;
        try{
            res = client.put(name, singers);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res.toString();
    }

    public String performRemoveSong(int id){
        Error res = null;
        try{
            res = client.remove(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res.toString();
    }

    // private
    private List<SongStruct> _getListSong(ListSongResult list){
        if(!list.error.equals(Error.SUCCESS)) return null;
        List<SongStruct> res = new ArrayList<>();
        for(int i : list.listSong){
            try {
                res.add(client.get(i).song);
            } catch (TException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
